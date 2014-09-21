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

	@SuppressWarnings("unused")
	private void setId(IdType id) {
		this.id = id;
	}
	
	protected void emit(Event event) {
		eventStore.storeEvent(event);
	}
}
