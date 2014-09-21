package eu.ijug.domain.eventsourcing_only;

import java.util.Date;

import eu.ijug.framework.AggregateAwareEvent;

public class AddressWasCorrected implements AggregateAwareEvent<String> {

	private String aggregateIdentifier;
	private Date date;
	private String newAddress;

	public AddressWasCorrected(String aggregateIdentifier, String newAddress,
			Date date) {
		this.aggregateIdentifier = aggregateIdentifier;
		this.newAddress = newAddress;
		this.date = date;
	}

	@Override
	public String getAggregateIdentifier() {
		return aggregateIdentifier;
	}

	public Date getDate() {
		return date;
	}

	public String getNewAddress() {
		return newAddress;
	}

	public void setAggregateIdentifier(String aggregateIdentifier) {
		this.aggregateIdentifier = aggregateIdentifier;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}

}
