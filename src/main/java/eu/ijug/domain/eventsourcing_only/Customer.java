public class Customer extends Aggregate<String> {
  private String address;
  private String assignedSalesRepresentative;

  public void correctMistakeInAddress(String newAddress) {
    emit(new AddressWasCorrected(this.getId(), newAddress, new Date()));
  }

  public void noteRelocationToNewAddress(String newAddress) {
    emit(new CustomerRelocatedToNewAddress(this.getId(), newAddress, new Date()));
  }

  public void wonByRepresentative(String salesRepresentativeId) {
    emit(new CustomerWasWon(this.getId(), salesRepresentativeId,
        new Date()));
  }

  private void apply(AddressWasCorrected event) {
    setAddress(event.getNewAddress());
  }

  private void apply(CustomerRelocatedToNewAddress event) {
    setAddress(event.getNewAddress());
  }

  private void apply(CustomerWasWon event) {
    setAssignedSalesRepresentative(event.salesRepresentativeId);
  }
}
