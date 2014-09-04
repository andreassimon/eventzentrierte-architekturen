package eu.ijug;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class CommandGateway {
	Set<CommandHandler<?>> commandHandlers = new HashSet<CommandHandler<?>>();
	
	
	public void send(Command cmd) {
		for (CommandHandler<?> commandHandler : commandHandlers) {
			for (Method method : commandHandler.getClass().getMethods()) {
				Class<?>[] parameters = method.getParameterTypes();
				if(parameters.length == 0)
					continue;
				Class<?> class1 = parameters[0];
				if(cmd.getClass().isAssignableFrom(class1)) {
					try {
						method.invoke(commandHandler, cmd);
					} catch (Exception e) {
					} 
				}
			}
		}
	}


	public void registerCommandHandler(
			CommandHandler<? extends Command> commandHandler) {
		commandHandlers.add(commandHandler);
	}
}
