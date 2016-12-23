package morphiatest;

import java.io.IOException;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.osgi.framework.Constants;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class Tester {
	static Logger log = Logger.getLogger(Tester.class.getName());
	protected static Datastore ds;



	public static void configure() {
		try {
			String dbURI = "mongodb://127.0.0.1:27017";
			String dbName = "cloudwife";
			log.info("Using Java version: " + System.getProperty("java.version"));
			MongoClient mongo = new MongoClient(new MongoClientURI(dbURI));
			Morphia morphia = new Morphia();
			ds = morphia.createDatastore(mongo, dbName);
			log.info("Using MongoDB version: " + ds.getDB().command("buildInfo").getString("version"));
			printLibraryVersions(mongo, morphia);
			log.info("Configured MongoDB to URI: '" + dbURI + "', DB name: '" + dbName);
			
		} catch (Throwable exc) {
			log.error("Could not configure MongoDB: " + exc);
			throw new RuntimeException(exc);
		}
	}

	private static JarFile printLibraryVersions(MongoClient mongo, Morphia morphia) throws IOException {
		JarFile jarFile = null;
		try {
			URL jarLocation = mongo.getClass().getProtectionDomain().getCodeSource().getLocation();
			String jarPath = jarLocation.getPath();
			jarFile = new JarFile(jarPath);
			Manifest manifest = jarFile.getManifest();
			Attributes mainAttributes = manifest.getMainAttributes();			
			String bundleName = (String) mainAttributes.getValue(Constants.BUNDLE_NAME);
			String bundleVersion = (String) mainAttributes.getValue(Constants.BUNDLE_VERSION);
			log.info("Using: "  + bundleName + " version: " + bundleVersion);
	
			jarLocation = morphia.getClass().getProtectionDomain().getCodeSource().getLocation();
			jarPath = jarLocation.getPath();
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (IOException e) { }
			}
			jarFile = new JarFile(jarPath);
			manifest = jarFile.getManifest();
			mainAttributes = manifest.getMainAttributes();			
			bundleName = (String) mainAttributes.getValue(Constants.BUNDLE_NAME);
			bundleVersion = (String) mainAttributes.getValue(Constants.BUNDLE_VERSION);
			log.info("Using: "  + bundleName + " version: " + bundleVersion);
		return jarFile;
		} finally {
			if (jarFile != null) {
				try { jarFile.close(); } catch (IOException e) { }
			}
		}
	}

}
