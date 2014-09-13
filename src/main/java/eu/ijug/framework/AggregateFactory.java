package eu.ijug.framework;

public class AggregateFactory<AggregateType extends Aggregate<IdType>, IdType> {
	Class<AggregateType> clazz;
	EventStore eventStore;

	public AggregateFactory(Class<AggregateType> clazz, EventStore store) {
		this.clazz = clazz;
		this.eventStore = store;
	}

	public AggregateType createInstance(IdType id) {
		AggregateType newInstance;
		try {
			newInstance = clazz.newInstance();
			newInstance.setId(id);
			newInstance.setEventStore(eventStore);
			
			AggregateFilteredEventBus<IdType> eventBus = new AggregateFilteredEventBus<IdType>(id);
			eventBus.registerEventHandler(newInstance);
			eventStore.getEventBus().registerSecondaryEventBus(eventBus);
			
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return newInstance;
	}

	public AggregateType loadInstance(IdType id) {
		AggregateType instance = createInstance(id);
		AggregateFilteredEventBus<IdType> eventBus = new AggregateFilteredEventBus<IdType>(
				id);
		eventBus.registerEventHandler(instance);
		eventStore.replayEvents(eventBus);
		return instance;
	}
}
