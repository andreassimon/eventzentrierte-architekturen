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
		emit(new AddressWasCorrected(this.getId(), newAddress, new Date()));
	}

	public void noteRelocationToNewAddress(String newAddress) {
		emit(new CustomerRelocatedToNewAddress(this.getId(), newAddress, new Date()));
	}

	public void wonByRepresentative(String salesRepresentativeId) {
		emit(new CustomerWasWon(this.getId(), salesRepresentativeId,
				new Date()));
	}

	private void apply(AddressWasCorrected event) {
		setAddress(event.getNewAddress());
	}

	private void apply(CustomerRelocatedToNewAddress event) {
		setAddress(event.getNewAddress());
	}

	private void apply(CustomerWasWon event) {
		setAssignedSalesRepresentative(event.salesRepresentativeId);
	}
	
	private void apply(SalesRepresentativeChanged event) {
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
