package eu.ijug.domain;

import eu.ijug.domain.eventsourcing_only.Customer;
import eu.ijug.framework.AggregateFactory;

public class CustomerFactory {

	private AggregateFactory<Customer, String> aggregateFactory;

	public CustomerFactory(AggregateFactory<Customer, String> aggregateFactory) {
		this.aggregateFactory = aggregateFactory;
	}

	public Customer newCustomerWonBySalesRepresentative(String customerId, String salesRepresentativeId) {
		Customer customer = aggregateFactory.createInstance(customerId);
		customer.wonByRepresentative(salesRepresentativeId);
		return customer;
	}

}
