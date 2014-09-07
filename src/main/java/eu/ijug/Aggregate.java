package eu.ijug;

public interface Aggregate<IdType> {
	public void setId(IdType id);
	public IdType getId();
}
