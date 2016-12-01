/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package morphiatest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity(value = "task_temp")
public class CWTask implements Serializable {
	private static final long serialVersionUID = -757387460290985847L;
	public String name;

	@Id public ObjectId id;

	public List<Key<CWUser>> assignees = new ArrayList<>();

	public CWTask() {
		super();
	}
	
	public CWTask(String name) {
		super();
		this.name = name;
	}
}