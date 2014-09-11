package eu.ijug.domain.eventsourcing_only;

import java.util.Date;

import eu.ijug.framework.Aggregate;
import eu.ijug.framework.EventStore;

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
	
	public void on(CustomerWasWon event) {
		this.assignedSalesRepresentative = event.salesRepresentativeId;
	}
	
	public void on(SalesRepresentativeChanged event) {
		this.assignedSalesRepresentative = event.newSalesRepresentativeId;
	}

	public String getAssignedSalesRepresentative() {
		return assignedSalesRepresentative;
	}
	
	public void setAssignedSalesRepresentative(String assignedSalesRepresentative) {
		this.assignedSalesRepresentative = assignedSalesRepresentative;
	}

	public void wonByRepresentative(String salesRepresentativeId) {
		apply(new CustomerWasWon(this.getId(), salesRepresentativeId, new Date()));
	}
	
}
