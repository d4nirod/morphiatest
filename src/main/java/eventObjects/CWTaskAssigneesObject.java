/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package eventObjects;

import java.util.ArrayList;
import java.util.List;


public class CWTaskAssigneesObject extends CWFeedTaskBaseObject {
	private static final long serialVersionUID = -4021003136656297325L;

	private List<CWTaskAssigneeObject> assignees = new ArrayList<>();
	private String doneBehavior = "ANY";
	
	public List<CWTaskAssigneeObject> getAssignees() {
		return assignees;
	}
	
	public void setAssignees(List<CWTaskAssigneeObject> assignees) {
		this.assignees = assignees;
	}
	
	public void addAssignee(CWTaskAssigneeObject assignee) {
		this.assignees.add(assignee);
	}
	
	public String getDoneBehavior() {
		return doneBehavior;
	}
	
	public void setDoneBehavior(String doneBehavior) {
		this.doneBehavior = doneBehavior;
	}
}
