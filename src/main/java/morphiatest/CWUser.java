/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package morphiatest;

import java.io.Serializable;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "user_temp")
public class CWUser implements Serializable {
	private static final long serialVersionUID = 5840360636842534337L;
	@Id public ObjectId id;
	public String name;

	public CWUser(String name) {
		super();
		this.name = name;
	}
	public CWUser() {
		super();
	}
}