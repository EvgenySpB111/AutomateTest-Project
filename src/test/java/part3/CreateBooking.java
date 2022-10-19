package part3;

public class CreateBooking {
  public String firstname;
  public String lastname;
  public int totalprice;
  public boolean depositpaid;
  public Bookingdates bookingdates;
  public String additionalneeds;

  public CreateBooking() {
  }

  public CreateBooking(String firstname, String lastname, int totalprice, boolean depositpaid, Bookingdates bookingdates, String additionalneeds) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.totalprice = totalprice;
    this.depositpaid = depositpaid;
    this.bookingdates = bookingdates;
    this.additionalneeds = additionalneeds;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public int getTotalprice() {
    return totalprice;
  }

  public void setTotalprice(int totalprice) {
    this.totalprice = totalprice;
  }

  public boolean isDepositpaid() {
    return depositpaid;
  }

  public void setDepositpaid(boolean depositpaid) {
    this.depositpaid = depositpaid;
  }

  public Bookingdates getBookingdates() {
    return bookingdates;
  }

  public void setBookingdates(Bookingdates bookingdates) {
    this.bookingdates = bookingdates;
  }

  public String getAdditionalneeds() {
    return additionalneeds;
  }

  public void setAdditionalneeds(String additionalneeds) {
    this.additionalneeds = additionalneeds;
  }
}
