package eu.ijug.framework;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AggregateFactoryTest {

	public class AggregateAwareTestEvent implements Event,
			AggregateAwareEvent<String> {

		private String aggregateId;

		public AggregateAwareTestEvent(String aggregateId) {
			this.aggregateId = aggregateId;
		}

		@Override
		public String getAggregateIdentifier() {
			return this.aggregateId;
		}

	}

	static class TestAggregate extends Aggregate<String> {

		boolean eventHandlerCalled;

		public void on(AggregateAwareTestEvent event) {
			this.eventHandlerCalled = true;
		}

	}

	@Test
	public void itShouldReturnAnAggregateOfType() {
		// Given
		AggregateFactory<TestAggregate, String> aggregateFactory = new AggregateFactory<TestAggregate, String>(
				TestAggregate.class, new EventStore(new EventBus()));

		// When
		TestAggregate test = aggregateFactory.createInstance("Foobar");

		// Expect
		assertThat(test.id, equalTo("Foobar"));
	}

	@Test
	public void itShouldReplayEventsForAnAggregate() {
		// Given
		EventStore store = new EventStore(new EventBus());
		store.storeEvent(new AggregateAwareTestEvent("Foobar"));

		AggregateFactory<TestAggregate, String> aggregateFactory = new AggregateFactory<TestAggregate, String>(
				TestAggregate.class, store);

		// When
		TestAggregate aggregate = aggregateFactory.loadInstance("Foobar");

		// Expect
		assertTrue(aggregate.eventHandlerCalled);
	}
}
