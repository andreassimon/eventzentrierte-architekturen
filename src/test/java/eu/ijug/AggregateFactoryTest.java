package eu.ijug;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class AggregateFactoryTest {

	static class TestAggregate implements EventHandler, Aggregate<String> {

		private String id;

		@Override
		public void setId(String id) {
			this.id = id;
		}

		@Override
		public String getId() {
			return this.id;
		}
	}

	@Test
	public void itShouldReturnAnAggregateOfType() {
		// Given 
		AggregateFactory<TestAggregate, String> aggregateFactory = new AggregateFactory<TestAggregate, String>(TestAggregate.class);
		
		// When
		TestAggregate test = aggregateFactory.createInstance("Foobar");
		
		// Expect
		assertThat(test.id, equalTo("Foobar"));
	}
}
