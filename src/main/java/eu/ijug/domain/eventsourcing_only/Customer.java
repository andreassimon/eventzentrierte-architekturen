package eu.ijug.domain.eventsourcing_only;

import java.util.Date;

import eu.ijug.framework.Aggregate;

@SuppressWarnings("unused")
public class Customer extends Aggregate<String> {
	private String assignedSalesRepresentative;
	
	public String getAssignedSalesRepresentative() {
		return assignedSalesRepresentative;
	}

	public void wonByRepresentative(String salesRepresentativeId) {
		apply(new CustomerWasWon(this.getId(), salesRepresentativeId, new Date()));
	}

	private void on(CustomerWasWon event) {
		setAssignedSalesRepresentative(event.salesRepresentativeId);
	}
	
	private void on(SalesRepresentativeChanged event) {
		setAssignedSalesRepresentative(event.newSalesRepresentativeId);
	}

	private void setAssignedSalesRepresentative(String assignedSalesRepresentative) {
		this.assignedSalesRepresentative = assignedSalesRepresentative;
	}
}
