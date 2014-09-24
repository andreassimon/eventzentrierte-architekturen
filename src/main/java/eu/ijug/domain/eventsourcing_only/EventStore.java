public interface EventStore {
  void appendEvent(DomainEvent e);
  List<DomainEvent> getEventsOf(AggregatId id);
}
