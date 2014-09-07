package eu.ijug;

class AggregateFilteredEventBus<IdType> extends FilteredEventBus {
	IdType id;
	
	public AggregateFilteredEventBus(IdType id) {
		this.id = id;
	}
	@Override
	boolean isRelevant(Event event) {
		if(!(event instanceof AggregateAwareEvent))
			return true;
		
		AggregateAwareEvent<?> awareEvent = (AggregateAwareEvent<?>) event;
		
		return this.id.equals(awareEvent.getAggregateIdentifier());
	}
	
}