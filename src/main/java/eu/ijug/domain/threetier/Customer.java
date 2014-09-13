package eu.ijug.domain.threetier;

public class Customer {

  private CustomerId id;

  private SalesRepresentative salesRepresentative;

  public SalesRepresentative getSalesRepresentative() {
    return salesRepresentative;
  }

  public void setSalesRepresentative(SalesRepresentative salesRepresentative) {
    this.salesRepresentative = salesRepresentative;
  }

}
