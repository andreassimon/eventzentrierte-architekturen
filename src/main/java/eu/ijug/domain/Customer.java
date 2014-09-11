package eu.ijug.domain;

import eu.ijug.framework.Aggregate;

public class Customer implements Aggregate<String> {
	private String id;
	private String assignedSalesRepresentative;
	
	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}
	
	public void on(CustomerWasWon event) {
		this.assignedSalesRepresentative = event.salesRepresentativeId;
	}

	public String getAssignedSalesRepresentative() {
		return assignedSalesRepresentative;
	}
	
	public void setAssignedSalesRepresentative(String assignedSalesRepresentative) {
		this.assignedSalesRepresentative = assignedSalesRepresentative;
	}
	
}
