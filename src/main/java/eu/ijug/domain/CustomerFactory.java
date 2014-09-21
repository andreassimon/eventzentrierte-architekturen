package eu.ijug.domain;

import java.util.Date;

import eu.ijug.domain.eventsourcing_only.Customer;
import eu.ijug.domain.eventsourcing_only.CustomerWasWon;
import eu.ijug.framework.AggregateFactory;
import eu.ijug.framework.EventStore;

public class CustomerFactory {

	private AggregateFactory<Customer, String> aggregateFactory;
	private EventStore eventStore;

	public CustomerFactory(AggregateFactory<Customer, String> aggregateFactory, EventStore eventStore) {
		this.aggregateFactory = aggregateFactory;
		this.eventStore = eventStore;
	}

	public Customer newCustomerWonBySalesRepresentative(String customerId, String salesRepresentativeId) {
		eventStore.storeEvent(new CustomerWasWon(customerId, salesRepresentativeId, new Date()));
		
		return aggregateFactory.loadInstance(customerId);
	}

}
