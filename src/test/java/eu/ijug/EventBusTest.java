package eu.ijug;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;

public class EventBusTest {
	public class TestEvent implements Event {

	}

	EventBus eventBus = new EventBus();

	@Test
	public void itShouldPublishAnEventToRegisteredEventHandlers() {
		// Given
		TestEvent event = new TestEvent();

		final AtomicBoolean eventHandlerCalled = new AtomicBoolean(false);
		eventBus.registerEventHandler(new EventHandler() {
			@SuppressWarnings("unused")
			public void on(TestEvent event) {
				eventHandlerCalled.set(true);
			}
		});

		// When
		eventBus.publish(event);

		// Expect
		assertTrue(eventHandlerCalled.get());
	}

	@Test 
	public void itShouldSupportMoreThanOneEventHandlerPerEventType() {
		// Given
		TestEvent event = new TestEvent();
		
		final AtomicBoolean firstEventHandlerCalled = new AtomicBoolean(false);
		final AtomicBoolean secondEventHandlerCalled = new AtomicBoolean(false);
		
		eventBus.registerEventHandler(new EventHandler() {
			public void on(TestEvent event) {
				firstEventHandlerCalled.set(true);
			}
		});
		eventBus.registerEventHandler(new EventHandler() {
			public void on(TestEvent event) {
				secondEventHandlerCalled.set(true);
			}
		});
		
		// When
		eventBus.publish(event);
		
		// Expect
		assertTrue(firstEventHandlerCalled.get());
		assertTrue(secondEventHandlerCalled.get());
	}
}
