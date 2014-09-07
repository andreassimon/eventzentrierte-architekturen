package eu.ijug;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

		gateway.registerCommandHandler(new CommandHandler() {
			@SuppressWarnings("unused")
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
		gateway.registerCommandHandler(new CommandHandler() {
			@SuppressWarnings("unused")
			public void on(TestCommand command) {
				firstCalled.set(true);
			}
		});
		gateway.registerCommandHandler(new CommandHandler() {
			@SuppressWarnings("unused")
			public void on(AnotherTestCommand command) {
				secondCalled.set(true);
			}
			
		});
		
		gateway.send(new AnotherTestCommand());
		assertFalse(firstCalled.get());
		assertTrue(secondCalled.get());
	}
	
	@Test
	public void itShouldSupportMoreThanOneHandlerMethodInOneClass() {
		final AtomicBoolean firstCalled = new AtomicBoolean(false);
		final AtomicBoolean secondCalled = new AtomicBoolean(false);
		
		gateway.registerCommandHandler(new CommandHandler() {
			public void on(TestCommand cmd) {
				firstCalled.set(true);
			}
			public void on(AnotherTestCommand cmd) {
				secondCalled.set(true);
			}
		});
		
		gateway.send(new TestCommand());
		assertTrue(firstCalled.get());
		assertFalse(secondCalled.get());
	}
}
