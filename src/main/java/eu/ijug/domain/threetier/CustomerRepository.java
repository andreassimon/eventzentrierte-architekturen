package eu.ijug.domain.threetier;

import java.util.Set;

public interface CustomerRepository {

  Customer get(CustomerId id);

  void save(Customer customer);

  Set<Customer> findAllByName(String name);
  // Weitere Query-Methoden
}
