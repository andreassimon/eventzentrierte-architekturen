package eu.ijug;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MethodInspector {
	public Map<Class<?>, WrappedMethod> commandHandlers;

	public MethodInspector() {
		this.commandHandlers = new HashMap<>();
	}

	void addCommandHandlerToClass(Class<?> classOfCommand, WrappedMethod method) {
		if (commandHandlers.containsKey(classOfCommand))
			throw new IllegalStateException(
					"Tried to register another command handler for the same command");
		commandHandlers.put(classOfCommand, method);
	}

	public void registerCommandHandler(CommandHandler commandHandler) {
		for (Method method : commandHandler.getClass().getDeclaredMethods()) {
			Class<?>[] parameters = method.getParameterTypes();
			if (parameters.length != 1)
				continue;
			Class<?> classOfCommand = parameters[0];
			if (Command.class.equals(classOfCommand)
					|| !Command.class.isAssignableFrom(classOfCommand))
				continue;

			addCommandHandlerToClass(classOfCommand, new WrappedMethod(
					commandHandler, method));
		}
	}
}