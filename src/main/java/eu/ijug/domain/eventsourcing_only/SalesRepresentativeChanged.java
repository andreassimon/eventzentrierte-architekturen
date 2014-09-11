package eu.ijug.domain.eventsourcing_only;

import eu.ijug.framework.AggregateAwareEvent;

public class SalesRepresentativeChanged implements AggregateAwareEvent<String> {
	String customerId;
	String newSalesRepresentativeId;
	

	@Override
	public String getAggregateIdentifier() {
		return this.customerId;
	}

}
