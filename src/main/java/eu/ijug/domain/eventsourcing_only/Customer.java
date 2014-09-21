package eu.ijug.domain.eventsourcing_only;

import java.util.Date;

import eu.ijug.framework.Aggregate;

@SuppressWarnings("unused")
public class Customer extends Aggregate<String> {
	private String assignedSalesRepresentative;
	private String address;

	public String getAssignedSalesRepresentative() {
		return assignedSalesRepresentative;
	}

	public String getAddress() {
		return address;
	}

	public void wonByRepresentative(String salesRepresentativeId) {
		apply(new CustomerWasWon(this.getId(), salesRepresentativeId,
				new Date()));
	}

	public void noteRelocationToNewAddress(String newAddress) {
		apply(new CustomerRelocatedToNewAddress(this.getId(), newAddress,
				new Date()));
	}

	private void on(CustomerWasWon event) {
		setAssignedSalesRepresentative(event.salesRepresentativeId);
	}

	private void on(SalesRepresentativeChanged event) {
		setAssignedSalesRepresentative(event.newSalesRepresentativeId);
	}

	private void on(CustomerRelocatedToNewAddress event) {
		setAddress(event.getNewAddress());
	}

	private void setAddress(String newAddress) {
		this.address = newAddress;
	}

	private void setAssignedSalesRepresentative(
			String assignedSalesRepresentative) {
		this.assignedSalesRepresentative = assignedSalesRepresentative;
	}
}
