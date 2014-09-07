package eu.ijug;

public abstract class FilteredEventBus extends EventBus {
	public void publish(Event event) {
		if(isRelevant(event)) {
			super.publish(event);
		}
	}
	
	abstract boolean isRelevant(Event event);
}
