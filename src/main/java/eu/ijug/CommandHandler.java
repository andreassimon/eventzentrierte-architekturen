package eu.ijug;

public interface CommandHandler<C extends Command> {
	public void on(C command);
}
