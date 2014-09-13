package eu.ijug.framework;

import java.util.ArrayList;
import java.util.List;

public class EventStore {
	List<Event> events = new ArrayList<>();
	EventBus eventBus;
	
	public EventStore(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	public void storeEvent(Event event) {
		events.add(event);
		eventBus.publish(event);
	}

	public Iterable<Event> getAllEvents() {
		return events;
	}

	public void replayEvents(EventBus customEventBus) {
		for(Event e : events) {
			customEventBus.publish(e);
		}
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	public void registerUpcaster(EventUpcaster eventUpcaster) {
		
	}

	
}
