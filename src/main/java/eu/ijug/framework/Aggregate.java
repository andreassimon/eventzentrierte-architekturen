package eu.ijug.framework;

public abstract class Aggregate<IdType> implements EventHandler {
	EventStore eventStore;
	IdType id;

	public EventStore getEventStore() {
		return eventStore;
	}

	public void setEventStore(EventStore eventStore) {
		this.eventStore = eventStore;
	}

	public IdType getId() {
		return id;
	}

	public void setId(IdType id) {
		this.id = id;
	}
	
	protected void apply(Event event) {
		eventStore.storeEvent(event);
	}
}
