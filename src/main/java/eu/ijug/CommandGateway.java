package eu.ijug;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CommandGateway {
	Map<Class<?>, WrappedMethod> commandHandlers = new HashMap<>();

	public void send(Command cmd) {
		if(!commandHandlers.containsKey(cmd.getClass()))
			throw new IllegalArgumentException("No subscribed commandhandler");
		commandHandlers.get(cmd.getClass()).call(cmd);
	}

	public void registerCommandHandler(Object commandHandler) {
		for (Method method : commandHandler.getClass().getDeclaredMethods()) {
			Class<?>[] parameters = method.getParameterTypes();
			if (parameters.length != 1)
				continue;
			Class<?> classOfCommand = parameters[0];
			if (Command.class.equals(classOfCommand) || !Command.class.isAssignableFrom(classOfCommand))
				continue;

			this.addCommandHandlerToClass(classOfCommand, new WrappedMethod(
					commandHandler, method));
		}
	}

	private void addCommandHandlerToClass(Class<?> classOfCommand,
			WrappedMethod method) {
		if(commandHandlers.containsKey(classOfCommand))
			throw new IllegalStateException("Tried to register another command handler for the same command");
		commandHandlers.put(classOfCommand, method);
	}
}
