package part3;

public class Bookingdates {
  public String checkin;
  public String checkout;

  public Bookingdates(){}

  public Bookingdates(String checkin, String checkout) {
    this.checkin = checkin;
    this.checkout = checkout;
  }

  public String getCheckin() {
    return checkin;
  }

  public String getCheckout() {
    return checkout;
  }
}
