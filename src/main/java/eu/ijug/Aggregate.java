package eu.ijug;

public interface Aggregate<IdType> extends EventHandler {
	public void setId(IdType id);
	public IdType getId();
}
