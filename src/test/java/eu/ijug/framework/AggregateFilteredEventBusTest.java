package eu.ijug.framework;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class AggregateFilteredEventBusTest {
	@Test
	public void anAggregateShouldOnlyReceiveAggregateAwareEvents() {
		// Given
		EventStore eventStore = new EventStore(new EventBus());
		AggregateFactory<TestAggregate, String> factory = new AggregateFactory<>(TestAggregate.class, eventStore);
		
		// When
		eventStore.storeEvent(new GlobalEvent());
		eventStore.storeEvent(new AggregateEvent("anotherInstance"));
		eventStore.storeEvent(new AggregateEvent("someInstance"));
		
		TestAggregate instance = factory.loadInstance("someInstance");
		
		assertThat(instance.called, is(true));

	}

	static class TestAggregate extends Aggregate<String> {
		private boolean called;

		public void on(GlobalEvent event) {
			throw new RuntimeException("A global event should not be called");
		}
		
		public void on(AggregateEvent event) {
			if(!event.getAggregateIdentifier().equals(this.getId())) {
				throw new RuntimeException("Got event for another aggregate");
			}
			if(called) 
				throw new RuntimeException("on(AggregateEvent) called twice");
			called = true;
		}
	}

	static class GlobalEvent implements Event {

	}

	static class AggregateEvent implements AggregateAwareEvent<String> {
		String aggregateIdentifier;

		public AggregateEvent(String aggregateIdentifier) {
			this.aggregateIdentifier = aggregateIdentifier;
		}

		public String getAggregateIdentifier() {
			return aggregateIdentifier;
		}

		public void setAggregateIdentifier(String aggregateIdentifier) {
			this.aggregateIdentifier = aggregateIdentifier;
		}

	}
}
