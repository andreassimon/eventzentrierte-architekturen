package eu.ijug;

import java.util.ArrayList;
import java.util.List;

public class EventStore {
	List<Event> events = new ArrayList<>();
	private EventBus eventBus;
	
	public EventStore(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	public void storeEvent(Event event) {
		events.add(event);
	}

	public Iterable<Event> getAllEvents() {
		return events;
	}
	
}
