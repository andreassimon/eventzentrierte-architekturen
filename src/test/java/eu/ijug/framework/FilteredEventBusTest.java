package eu.ijug.framework;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

import eu.ijug.framework.Event;
import eu.ijug.framework.EventHandler;
import eu.ijug.framework.FilteredEventBus;
import org.junit.Test;

public class FilteredEventBusTest {

	static class FirstTestEvent implements Event {
		
	}
	
	static class SecondTestEvent implements Event {
		
	}
	
	@Test
	public void itShouldFilterAnEventStreamGivenAnAbstractClause() {
		// Given
		FilteredEventBus eventBus = new FilteredEventBus() {
			public boolean isRelevant(Event event) {
				return event instanceof FirstTestEvent;
			}
		};
		
		final AtomicBoolean firstCalled = new AtomicBoolean(false);
		final AtomicBoolean secondCalled = new AtomicBoolean(false);
		eventBus.registerEventHandler(new EventHandler() {
			@SuppressWarnings("unused")
			void on(FirstTestEvent e) {
				firstCalled.set(true);
			}
			@SuppressWarnings("unused")
			void on(SecondTestEvent e) {
				secondCalled.set(true);
			}
		});
		
		// When
		eventBus.publish(new FirstTestEvent());
		eventBus.publish(new SecondTestEvent());
		
		// Expect
		assertTrue(firstCalled.get());
		assertFalse(secondCalled.get());
		
	}
}
