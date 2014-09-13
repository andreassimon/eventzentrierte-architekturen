package eu.ijug.domain.threetier;

public class CustomerService {

  CustomerRepository customerRepository;

  public Customer get(CustomerId id) {
    return customerRepository.get(id);
  }

}
