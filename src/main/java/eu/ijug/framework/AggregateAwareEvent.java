package eu.ijug.framework;

public interface AggregateAwareEvent<T> {
	T getAggregateIdentifier();
}
