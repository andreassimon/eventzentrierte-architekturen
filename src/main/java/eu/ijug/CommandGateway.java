package eu.ijug;

import java.util.HashMap;

public class CommandGateway {
	MethodInspector methodInspector = new MethodInspector();

	public void send(Command cmd) {
		if(!methodInspector.commandHandlers.containsKey(cmd.getClass()))
			throw new IllegalArgumentException("No subscribed commandhandler");
		methodInspector.commandHandlers.get(cmd.getClass()).call(cmd);
	}

	public void registerCommandHandler(CommandHandler commandHandler) {
		methodInspector.registerCommandHandler(commandHandler);
	}
}
