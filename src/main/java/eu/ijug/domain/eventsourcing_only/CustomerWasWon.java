package eu.ijug.domain.eventsourcing_only;

import java.util.Date;

import eu.ijug.framework.AggregateAwareEvent;
import eu.ijug.framework.Event;

public class CustomerWasWon implements AggregateAwareEvent<String> {

	String customerId;
	String salesRepresentativeId;
	Date timeStamp;

	public CustomerWasWon(String customerId, String salesRepresentativeId,
			Date timeStamp) {
		this.customerId = customerId;
		this.salesRepresentativeId = salesRepresentativeId;
		this.timeStamp = timeStamp;
	}

	@Override
	public String getAggregateIdentifier() {
		return customerId;
	}

}
