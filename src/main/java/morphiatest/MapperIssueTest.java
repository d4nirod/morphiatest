/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package morphiatest;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.osgi.framework.Constants;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MapperIssueTest {
	
	private static Datastore ds;

	public static void main(String[] args) {
		configure();
		String aDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
		
		Object userId = ds.save(new CWUser("John " + aDate )).getId();
		CWUser user = ds.get(CWUser.class, userId);
		System.out.println("'" + user.name + "' saved with ID: " + user.id);
		
		Object taskId = ds.save(new CWTask("Task testing Mapper " + aDate)).getId();
		CWTask task = ds.get(CWTask.class, taskId);
		System.out.println("'" + task.name + "' saved with ID: " + task.id);
				
		Key<CWUser> key = new Key<>(CWUser.class, "user_temp", user.id);
		task.assignees = new ArrayList<Key<CWUser>>();
		task.assignees.add(key);
		
		Query<CWTask> q = ds.createQuery(CWTask.class).field("_id").equal(task.id);
        ds.update(q, ds.createUpdateOperations(CWTask.class).set("assignees", task.assignees));
	}
	
	public static void configure() {
		try {
			String dbURI = "mongodb://127.0.0.1:27017";
			String dbName = "cloudwife";
			MongoClient mongo = new MongoClient(new MongoClientURI(dbURI));
			Morphia morphia = new Morphia();
			ds = morphia.createDatastore(mongo, dbName);
//			ds.ensureIndexes();
//			ds.ensureCaps();

			System.out.println("Using MongoDB version: " + ds.getDB().command("buildInfo").getString("version"));
//			System.out.println("Using MongoDB Java Driver version: " + ds.getMongo().getVersion());
			printLibraryVersions(mongo, morphia);
			System.out.println("Configured MongoDB to URI: '" + dbURI + "', DB name: '" + dbName);
			
		} catch (Throwable exc) {
			System.out.println("Could not configure MongoDB: " + exc);
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
			System.out.println("Using: "  + bundleName + " version: " + bundleVersion);
	
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
			System.out.println("Using: "  + bundleName + " version: " + bundleVersion);
		return jarFile;
		} finally {
			if (jarFile != null) {
				try { jarFile.close(); } catch (IOException e) { }
			}
		}
	}

}
