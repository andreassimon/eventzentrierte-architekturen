package eu.ijug.framework;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventStore {
	List<Event> events = new ArrayList<>();
	Set<EventUpcaster> upcasters = new HashSet<>();
	EventBus eventBus;

	public EventStore(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	public void storeEvent(Event event) {
		events.add(event);
		doPublishEvent(this.eventBus, event);
	}

	public Iterable<Event> getAllEvents() {
		return events;
	}

	public void replayEvents(EventBus customEventBus) {
		for (Event e : events) {
			doPublishEvent(customEventBus, e);
		}
	}

	private void doPublishEvent(EventBus eventBus, Event event) {
		boolean upcasterUsed = handleUpcasting(eventBus, event);
		if (!upcasterUsed) {
			eventBus.publish(event);
		}
	}

	private boolean handleUpcasting(EventBus eventBus, Event e) {
		for (EventUpcaster upcaster : upcasters) {
			if (!upcaster.canHandle(e)) {
				continue;
			}
			List<Event> newEvents = upcaster.upcast(e);
			for (Event newEvent : newEvents) {
				doPublishEvent(eventBus, newEvent);
			}
			return true;
		}
		return false;
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	public void registerUpcaster(EventUpcaster eventUpcaster) {
		this.upcasters.add(eventUpcaster);
	}

}
