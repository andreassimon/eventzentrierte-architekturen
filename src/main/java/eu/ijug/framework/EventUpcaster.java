package eu.ijug.framework;

import java.util.List;

public interface EventUpcaster {
	boolean canHandle(Event e);
	List<Event> upcastEvent(Event e);
}
