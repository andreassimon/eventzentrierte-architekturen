package eu.ijug.framework;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventBus {
	Map<Class<?>, Set<WrappedMethod>> eventHandlers = new HashMap<>();
	Set<EventBus> secondaryEventBusses = new HashSet<>();

	public void publish(Event event) {
		Class<?> classOfEvent = event.getClass();
		
		for (EventBus secondaryEventbus: secondaryEventBusses) {
			secondaryEventbus.publish(event);
		}
		
		if(!eventHandlers.containsKey(classOfEvent))
			return;

		for(WrappedMethod eventHandlingMethod : eventHandlers.get(classOfEvent)) {
			eventHandlingMethod.call(event);
		}
	}
	
	public void registerSecondaryEventBus(EventBus secondaryEventBus) {
		secondaryEventBusses.add(secondaryEventBus);
	}

	public void registerEventHandler(EventHandler eventHandler) {
		for (Method method : eventHandler.getClass().getDeclaredMethods()) {
			Class<?>[] parameters = method.getParameterTypes();
			if (parameters.length != 1)
				continue;
			Class<?> classOfEvent = parameters[0];
			if (Event.class.equals(classOfEvent)
					|| !Event.class.isAssignableFrom(classOfEvent))
				continue;
			if(!method.isAccessible())
				method.setAccessible(true);
			registerEventHandlerMethod(classOfEvent, new WrappedMethod(
					eventHandler, method));
		}
	}

	private void registerEventHandlerMethod(Class<?> classOfEvent,
			WrappedMethod wrappedMethod) {
		assertKeyIsInitialized(classOfEvent);
		eventHandlers.get(classOfEvent).add(wrappedMethod);
	}

	private void assertKeyIsInitialized(Class<?> classOfEvent) {
		if (eventHandlers.containsKey(classOfEvent))
			return;
		eventHandlers.put(classOfEvent, new HashSet<WrappedMethod>());
	}

}
