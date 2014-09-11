package eu.ijug.framework;

public interface AggregateAwareEvent<T> extends Event {
	T getAggregateIdentifier();
}
