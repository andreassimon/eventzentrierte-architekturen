package eu.ijug.domain.eventsourcing_only;

import java.util.Date;

import eu.ijug.framework.Event;

public class CustomerHasMove implements Event {

	private String id;
	private String newAddress;
	private Date date;

	public CustomerHasMove(String id, String newAddress, Date date) {
		this.id = id;
		this.newAddress = newAddress;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNewAddress() {
		return newAddress;
	}

	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
