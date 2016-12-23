/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package feeds;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Polymorphic;


@Polymorphic
@Embedded
public abstract class CWFeedObject implements Serializable {
	private static final long serialVersionUID = 5912003998767038341L;

}
