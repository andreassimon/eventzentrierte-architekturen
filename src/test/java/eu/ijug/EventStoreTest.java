package eu.ijug;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import org.junit.Test;

public class EventStoreTest {
	private EventStore eventStore = new EventStore();

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
}
