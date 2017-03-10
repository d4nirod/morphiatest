package morphiatest;

import static org.junit.Assert.assertNotNull;
import static morphiatest.Tester.configure;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query; 

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBList;


public class ValidateEntityWithDBListIT {
	
	static Logger log = Logger.getLogger(Tester.class.getName());
	protected Datastore ds;

    private BasicDAO<TestEntity, ObjectId> dao;

    private ObjectMapper jsonMapper = new ObjectMapper();

    public static final String DATA = "[{ \"type\" : \"text\", \"data\" : { \"text\" : \"sometext\" } },  { \"data\" : { \"id\" : \"123\" }, \"type\" : \"image\"  }]";

    /**
     * 
     */
    @Before
    public void setUp() {
    	this.ds = configure();
        this.dao = new BasicDAO<>(TestEntity.class, ds);
    }

    /**
     * 
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testCreateEntityWithBasicDBList() throws Exception {

        TestEntity entity = new TestEntity();

        List<Map<String, Object>> data = this.jsonMapper.readValue(DATA, List.class);

        entity.setData(data);
        this.dao.save(entity);

        this.dao.get(entity.getId());

        Query<TestEntity> query = this.dao.createQuery();
        query.disableValidation();
        query.criteria("data.data.id").equal("123");

        TestEntity foundEntity = query.get();
        assertNotNull(foundEntity);
    }

    /**
     *
     */
    @Entity("CreateEntityWithDBListIT-TestEntity")
    public static class TestEntity {

        @Id
        private ObjectId id;

        public ObjectId getId() {
            return id;
        }

        private BasicDBList data;

        public BasicDBList getData() {
            return data;
        }

        public void setData(List<?> data) {
            Objects.requireNonNull(data, "data");
            this.data = new BasicDBList();
            this.data.addAll(data);
        }
    }

}
