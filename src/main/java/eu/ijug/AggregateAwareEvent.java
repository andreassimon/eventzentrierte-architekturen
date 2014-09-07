package eu.ijug;

public interface AggregateAwareEvent<T> {
	T getAggregateIdentifier();
}
