package eu.ijug.framework;

public interface Aggregate<IdType> extends EventHandler {
	public void setId(IdType id);
	public IdType getId();
}
