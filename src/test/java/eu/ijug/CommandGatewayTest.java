package eu.ijug;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;

public class CommandGatewayTest {
	public class AnotherTestCommand extends Command {

	}

	private CommandGateway gateway = new CommandGateway();

	public static class TestCommand extends Command {

	}

	@Test
	public void itShouldCallTheCommandHandler() {
		final AtomicBoolean called = new AtomicBoolean(false);

		gateway.registerCommandHandler(new CommandHandler<TestCommand>() {

			public void on(TestCommand command) {
				called.set(true);
			}
		});
		gateway.send(new TestCommand());

		assertTrue(called.get());
	}
	
	@Test
	public void itShouldLetMeRegisterMoreThanOneDifferentCommandHandlers() {
		final AtomicBoolean firstCalled = new AtomicBoolean(false);
		final AtomicBoolean secondCalled = new AtomicBoolean(false);
		gateway.registerCommandHandler(new CommandHandler<TestCommand>() {

			@Override
			public void on(TestCommand command) {
				firstCalled.set(true);
			}
		});
		gateway.registerCommandHandler(new CommandHandler<AnotherTestCommand>() {

			@Override
			public void on(AnotherTestCommand command) {
				secondCalled.set(true);
			}
			
		});
		
		gateway.send(new AnotherTestCommand());
		assertFalse(firstCalled.get());
		assertTrue(secondCalled.get());
	}
}
