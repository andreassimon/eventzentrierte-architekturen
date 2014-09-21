package eu.ijug.domain.eventsourcing_only;

import java.util.Date;

import eu.ijug.framework.Aggregate;

@SuppressWarnings("unused")
public class Customer extends Aggregate<String> {
	private String address;
	private String assignedSalesRepresentative;

	public String getAddress() {
		return address;
	}

	public String getAssignedSalesRepresentative() {
		return assignedSalesRepresentative;
	}

	public void correctMistakeInAddress(String newAddress) {
		apply(new AddressWasCorrected(this.getId(), newAddress, new Date()));
		
	}

	public void noteRelocationToNewAddress(String newAddress) {
		apply(new CustomerRelocatedToNewAddress(this.getId(), newAddress,
				new Date()));
	}

	public void wonByRepresentative(String salesRepresentativeId) {
		apply(new CustomerWasWon(this.getId(), salesRepresentativeId,
				new Date()));
	}

	private void on(AddressWasCorrected event) {
		setAddress(event.getNewAddress());
	}

	private void on(CustomerRelocatedToNewAddress event) {
		setAddress(event.getNewAddress());
	}

	private void on(CustomerWasWon event) {
		setAssignedSalesRepresentative(event.salesRepresentativeId);
	}
	
	private void on(SalesRepresentativeChanged event) {
		setAssignedSalesRepresentative(event.newSalesRepresentativeId);
	}

	private void setAddress(String newAddress) {
		this.address = newAddress;
	}

	private void setAssignedSalesRepresentative(
			String assignedSalesRepresentative) {
		this.assignedSalesRepresentative = assignedSalesRepresentative;
	}
}
