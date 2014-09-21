package eu.ijug.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import eu.ijug.domain.eventsourcing_only.Customer;
import eu.ijug.domain.eventsourcing_only.CustomerWasWon;
import eu.ijug.framework.AggregateFactory;
import eu.ijug.framework.EventBus;
import eu.ijug.framework.EventStore;

public class CustomerAddressTest {
	private EventStore eventStore;
	private EventBus eventBus;
	private AggregateFactory<Customer, String> aggregateFactory;
	private CustomerFactory customerFactory;
	private Customer customer;

	String customerId = "someCustomer";

	@Before
	public void setupEventStore() {
		eventBus = new EventBus();
		eventStore = new EventStore(eventBus);
		aggregateFactory = new AggregateFactory<>(Customer.class, eventStore);
		customerFactory = new CustomerFactory(aggregateFactory);

		eventStore.storeEvent(new CustomerWasWon(customerId, "someRepresentative", new Date(2014, 5, 1)));
		customer = aggregateFactory.loadInstance(customerId);
	}
	
	@Test
	public void itShouldNoteTheAddressChangeOfACustomerIfHeMoves() {
		// Given
		String newAddress = "Dorfstrasse 1, 11111 Musterstadt";
		
		// When
		customer.noteRelocationToNewAddress(newAddress);
		
		// Expect
		assertThat(customer, hasProperty("address", equalTo(newAddress)));
	}
}
