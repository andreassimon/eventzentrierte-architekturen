package eu.ijug.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
			setId(newInstance, id);
			newInstance.setEventStore(eventStore);
			
			AggregateFilteredEventBus<IdType> eventBus = new AggregateFilteredEventBus<IdType>(id);
			eventBus.registerEventHandler(newInstance);
			eventStore.getEventBus().registerSecondaryEventBus(eventBus);
			
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		return newInstance;
	}

	private void setId(AggregateType newInstance, IdType id)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Method setIdMethod;
		try {
			setIdMethod = clazz.getDeclaredMethod("setId", id.getClass());
		} catch (NoSuchMethodException e) {
			setIdMethod = Aggregate.class.getDeclaredMethod("setId", Object.class);
		}
		setIdMethod.setAccessible(true);
		setIdMethod.invoke(newInstance, id);
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
