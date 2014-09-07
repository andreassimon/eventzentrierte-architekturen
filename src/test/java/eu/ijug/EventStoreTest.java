package eu.ijug;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Before;
import org.junit.Test;

public class EventStoreTest {
	private EventStore eventStore;
	private EventBus eventBus;
	
	@Before
	public void setupEventStore() {
		eventBus = new EventBus();
		eventStore = new EventStore(eventBus);
		
	}

	static class TestEvent implements Event {
		int id;
		
		public TestEvent(int id) {
			this.id = id;
		}

		public boolean equals(Object o) {
			if(!(o instanceof TestEvent))
				return false;
			return id == ((TestEvent) o).id;
		}
	}
	
	@Test
	public void itShouldStoreAnEventAndReturnIt() {
		// Given
		TestEvent event = new TestEvent(27);
		
		// When
		eventStore.storeEvent(event);
		
		// Expect
		assertThat(eventStore.getAllEvents(), contains((Event)event));
	}
	
	@Test
	public void itShouldPublishAnEventOnTheGlobalEventBus() {
		// Given
		TestEvent event = new TestEvent(27);
		final AtomicBoolean called = new AtomicBoolean(false);
		eventBus.registerEventHandler(new EventHandler() {
			@SuppressWarnings("unused")
			void on(TestEvent event) {
				called.set(true);
			}
		});
		
		// When
		eventStore.storeEvent(event);
		
		// Expect
		assertTrue(called.get());
	}
}
