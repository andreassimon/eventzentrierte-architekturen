package eu.ijug;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventBus {
	Map<Class<?>, Set<WrappedMethod>> eventHandlers = new HashMap<>();

	public void publish(Event event) {
		Class<?> classOfEvent = event.getClass();
		if(!eventHandlers.containsKey(classOfEvent))
			return;
		
		for(WrappedMethod eventHandlingMethod : eventHandlers.get(classOfEvent)) {
			eventHandlingMethod.call(event);
		}
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
