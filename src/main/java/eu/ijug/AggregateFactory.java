package eu.ijug;

public class AggregateFactory<AggregateType extends Aggregate<IdType>, IdType> {
	Class<AggregateType> clazz;
	
	public AggregateFactory(Class<AggregateType> clazz) {
		this.clazz = clazz;
	}



	public AggregateType createInstance(IdType id) {
		AggregateType newInstance;
		try {
			newInstance = clazz.newInstance();
			newInstance.setId(id);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return newInstance;
	}



	public AggregateType loadInstance(String string) {
		return null;
	}
	
}
