package eu.ijug.domain.eventsourcing_only;

import java.util.Date;

import eu.ijug.framework.Aggregate;

public class Customer extends Aggregate<String> {
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
	
	private void on(CustomerWasWon event) {
		this.assignedSalesRepresentative = event.salesRepresentativeId;
	}
	
	private void on(SalesRepresentativeChanged event) {
		this.assignedSalesRepresentative = event.newSalesRepresentativeId;
	}

	public String getAssignedSalesRepresentative() {
		return assignedSalesRepresentative;
	}
	
	private void setAssignedSalesRepresentative(String assignedSalesRepresentative) {
		this.assignedSalesRepresentative = assignedSalesRepresentative;
	}

	public void wonByRepresentative(String salesRepresentativeId) {
		apply(new CustomerWasWon(this.getId(), salesRepresentativeId, new Date()));
	}
	
}
