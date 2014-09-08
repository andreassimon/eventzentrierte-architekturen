package eu.ijug.framework;

public class AggregateFactory<AggregateType extends Aggregate<IdType>, IdType> {
	Class<AggregateType> clazz;
	EventStore store;
	
	public AggregateFactory(Class<AggregateType> clazz, EventStore store) {
		this.clazz = clazz;
		this.store = store;
	}



	public AggregateType createInstance(IdType id) {
		AggregateType newInstance;
		try {
			newInstance = clazz.newInstance();
			newInstance.setId(id);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return newInstance;
	}



	public AggregateType loadInstance(IdType id) {
		AggregateType instance = createInstance(id);
		AggregateFilteredEventBus<IdType> eventBus = new AggregateFilteredEventBus<IdType>(id);
		eventBus.registerEventHandler(instance);
		store.replayEvents(eventBus);
		return instance;
	}
}
