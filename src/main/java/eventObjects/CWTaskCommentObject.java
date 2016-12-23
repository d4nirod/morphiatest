/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package eventObjects;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;


public class CWTaskCommentObject extends CWFeedTaskBaseObject {
	

	private static final long serialVersionUID = 4509357846596821617L;

	public static final String CONTEXT_COMMENT = "comment";
	
	private ObjectId commentId;
	private String commentText;
	private boolean isPrivate = false;
	
	private List<CWMentionObject> mentions = new ArrayList<>();

	
	
	public CWTaskCommentObject() {
		super();
	}
	
	public CWTaskCommentObject(ObjectId commentId, String commentText) {
		super();
		this.commentId = commentId;
		this.commentText = commentText;
	}
	
	public String getCommentText() {
		return commentText;
	}
	
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	public List<CWMentionObject> getMentions() {
		return mentions;
	}
	
	public void setMentions(List<CWMentionObject> mentions) {
		this.mentions = mentions;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public ObjectId getCommentId() {
		return commentId;
	}

	public void setCommentId(ObjectId commentId) {
		this.commentId = commentId;
	}
}
