package eu.ijug.framework;

import com.google.common.base.Predicate;

public class PredicateFilteredEventBus extends FilteredEventBus {

	private Predicate<Event> predicate;

	public PredicateFilteredEventBus(Predicate<Event> predicate) {
		this.predicate = predicate;
	}

	@Override
	boolean isRelevant(Event event) {
		return predicate.apply(event);
	}

}
