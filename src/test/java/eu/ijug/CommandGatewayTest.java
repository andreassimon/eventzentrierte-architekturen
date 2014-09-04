package eu.ijug;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;

public class CommandGatewayTest {
	public static class TestCommand extends Command {

	}

	@Test
	public void itShouldCallTheCommandHandler() {
		CommandGateway gateway = new CommandGateway();
		final AtomicBoolean called = new AtomicBoolean(false);

		gateway.registerCommandHandler(new CommandHandler<TestCommand>() {

			public void on(TestCommand command) {
				called.set(true);
			}
		});
		gateway.send(new TestCommand());

		assertTrue(called.get());
	}
}
