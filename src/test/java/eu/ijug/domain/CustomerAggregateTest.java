package eu.ijug.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.Before;
import org.junit.Test;

import eu.ijug.domain.eventsourcing_only.Customer;
import eu.ijug.framework.AggregateFactory;
import eu.ijug.framework.EventBus;
import eu.ijug.framework.EventStore;

public class CustomerAggregateTest {
	private EventStore eventStore;
	private EventBus eventBus;
	private AggregateFactory<Customer, String> aggregateFactory;
	private CustomerFactory customerFactory;

	@Before
	public void setupEventStore() {
		eventBus = new EventBus();
		eventStore = new EventStore(eventBus);
		aggregateFactory = new AggregateFactory<>(Customer.class, eventStore);
		customerFactory = new CustomerFactory(aggregateFactory);
	}
	
	@Test
	public void theSalesRepresentativeWhoWonTheCustomerIsAssignedToHim() {
		// Given
		String customerId = "myCustomer";
		String salesRepresentativeId = "mySalesRepresentative";
		
		// When
		Customer newCustomer = customerFactory.newCustomerWonBySalesRepresentative(customerId, salesRepresentativeId);
		
		// Expect
		assertThat(newCustomer, hasProperty("assignedSalesRepresentative", equalTo(salesRepresentativeId)));
	}
	
	@Test
	public void theSameThingShouldWorkByCallingAMethodOnTheAggregate() {
		// Given
		String customerId = "myCustomer";
		String salesRepresentativeId = "mySalesRepresentative";

		// When
		Customer customer = aggregateFactory.createInstance(customerId);
		customer.wonByRepresentative(salesRepresentativeId);
		
		// Expect
		Customer loadedCustomer = aggregateFactory.loadInstance(customerId);
		assertThat(loadedCustomer, hasProperty("assignedSalesRepresentative", equalTo(salesRepresentativeId)));
		assertThat(customer, hasProperty("assignedSalesRepresentative", equalTo(salesRepresentativeId)));
	}
}
