package eu.ijug.framework;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class EventUpcastingTest {
	@Test
	public void theStoreShouldUpcastAnEventIfAnUpcasterCanHandleIt() {
		// Given
		EventBus eventBus = mock(EventBus.class);
		EventStore store = new EventStore(eventBus);
		
		// When
		store.registerUpcaster(new TestUpcaster());
		store.storeEvent(new EventToBeUpcasted());
		
		// Expect
		verify(eventBus, times(1)).publish(isA(FirstNewEvent.class));
		verify(eventBus, times(1)).publish(isA(SecondNewEvent.class));
	}

	public class FirstNewEvent implements Event {
	
	}

	public class SecondNewEvent implements Event {
	
	}

	private final class TestUpcaster implements EventUpcaster {
		@Override
		public boolean canHandle(Event e) {
			return EventToBeUpcasted.class.equals(e.getClass());
		}
	
		@Override
		public List<Event> upcastEvent(Event e) {
			return Arrays.asList(new FirstNewEvent(), new SecondNewEvent());
		}
	}

	public class EventToBeUpcasted implements Event {
	
	}
}
