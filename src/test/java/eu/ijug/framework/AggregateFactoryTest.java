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

	static class TestAggregate implements EventHandler, Aggregate<String> {

		String id;
		boolean eventHandlerCalled;

		@Override
		public void setId(String id) {
			this.id = id;
		}

		@Override
		public String getId() {
			return this.id;
		}
		
		public void on(AggregateAwareTestEvent event) {
			this.eventHandlerCalled = true;
		}
	
	}

	@Test
	public void itShouldReturnAnAggregateOfType() {
		// Given
		AggregateFactory<TestAggregate, String> aggregateFactory = new AggregateFactory<TestAggregate, String>(
				TestAggregate.class, null);

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
