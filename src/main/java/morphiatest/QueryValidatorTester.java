package morphiatest;

import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;

import eventObjects.CWMentionObject;
import eventObjects.CWTaskCommentObject;
import feeds.CWFeedEvent;

public class QueryValidatorTester extends Tester {
	
	static Logger logger = Logger.getLogger(MapperIssueTest.class.getName());


	public static void main(String[] args) {
		logger.info("********** STARTING " + QueryValidatorTester.class.getName() + " *************");
		configure();		
		CWFeedEvent commentFeedEvent = new CWFeedEvent();
		commentFeedEvent.setEventType("task_comment");
		CWTaskCommentObject comment = new CWTaskCommentObject(new ObjectId("57c840e10348e0d514d6d06b"), "A really boring comment");
		String userEntityId = "577e7f08300488c6676b33f5";
		comment.getMentions().add(new CWMentionObject(userEntityId, "user", "@Simon Test" ));
		commentFeedEvent.setEventObject(comment);
		Key<CWFeedEvent> saved = ds.save(commentFeedEvent);
		
		List<CWFeedEvent> feedEvents = createMentionedUserIdQuery(null, new ObjectId(userEntityId), false).asList();
		
		
	}
	
	private static Query<CWFeedEvent> createMentionedUserIdQuery(List<ObjectId> idsToMatch, ObjectId userId, boolean fetchIdsOnly) {
		Query<CWFeedEvent> q = ds.createQuery(CWFeedEvent.class).disableValidation()
//				.field(Mapper.ID_KEY).in(idsToMatch)
				.field("eventType").equal("task_comment")
				.field("eventObject.mentions.entityId").equal(userId.toHexString());
		
		if (fetchIdsOnly) {
			q.retrievedFields(true, Mapper.ID_KEY);
		}
		return q.enableValidation();
	}
	

}
