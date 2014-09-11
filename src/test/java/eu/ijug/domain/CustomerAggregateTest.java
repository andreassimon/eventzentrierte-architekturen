package eu.ijug.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import eu.ijug.framework.AggregateFactory;
import eu.ijug.framework.EventBus;
import eu.ijug.framework.EventStore;

public class CustomerAggregateTest {
	private EventStore eventStore;
	private EventBus eventBus;
	private AggregateFactory<Customer, String> aggregateFactory;

	@Before
	public void setupEventStore() {
		eventBus = new EventBus();
		eventStore = new EventStore(eventBus);
		aggregateFactory = new AggregateFactory<>(Customer.class, eventStore);
	}
	
	@Test
	public void theSalesRepresentativeWhoWonTheCustomerIsAssignedToHim() {
		// Given
		String customerId = "myCustomer";
		eventStore.storeEvent(new CustomerWasWon(customerId, "mySalesRepresentative", new Date()));
		
		// When
		Customer customer = aggregateFactory.loadInstance(customerId);
		
		// Expect
		assertThat(customer, hasProperty("assignedSalesRepresentative", equalTo("mySalesRepresentative")));
	}
}
