public class CustomerTest {
  /* ... */
  
  @Test
  public void theSalesRepresentativeWhoWonTheCustomerIsAssignedToHim() {
    // Given
    String customerId = "myCustomerId";
    String salesRepresentativeId = "mySalesRepresentativeId";

    // When
    Customer customer = aggregateFactory.createInstance(customerId);
    customer.wonByRepresentative(salesRepresentativeId);
    
    // Expect
    assertThat(customer.getSalesRepresentative(), equalTo(salesRepresentativeId));
  }
  
  @Test
  public void itShouldNoteTheAddressChangeOfACustomerIfHeMoves() {
    // Given
    String newAddress = "Dorfstrasse 1, 11111 Musterstadt";
    
    // When
    customer.noteRelocationToNewAddress(newAddress);
    
    // Expect
    assertThat(customer.getAddress(), equalTo(newAddress));
  }
  
  @Test
  public void itShouldCorrectTheAddressInCaseOfATypo() {
    // Given
    String newAddress = "Dorfstrasse 12, 11111 Musterstadt";
    customer.noteRelocationToNewAddress("Dorfstrasse 1, 11111 Musterstadt");
    
    // When
    customer.correctMistakeInAddress(newAddress);

    // Expect
    assertThat(customer.getAddress(), equalTo(newAddress));
  }
}
