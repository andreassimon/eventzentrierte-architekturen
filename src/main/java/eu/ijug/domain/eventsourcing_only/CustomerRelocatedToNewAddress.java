package eu.ijug.domain.eventsourcing_only;

import java.util.Date;

import eu.ijug.framework.AggregateAwareEvent;
import eu.ijug.framework.Event;

public class CustomerRelocatedToNewAddress implements AggregateAwareEvent<String> {

	private String aggregateIdentifier;
	private Date date;
	private String newAddress;

	public CustomerRelocatedToNewAddress(String aggregateIdentifier, String newAddress, Date date) {
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

	public void setAggregateIdentifier(String id) {
		this.aggregateIdentifier = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}

}
