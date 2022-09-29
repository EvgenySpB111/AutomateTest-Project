package hhTest;

public class Resume {
  private String gender;
  private int age;
  private String city;
  private boolean markTelephone;
  private boolean removal;

  public Resume(String gender, int age, String city, boolean markTelephone, boolean removal) {
    this.gender = gender;
    this.age = age;
    this.city = city;
    this.markTelephone = markTelephone;
    this.removal = removal;
  }

  public String getGender() {
    return gender;
  }

  public int getAge() {
    return age;
  }

  public String getCity() {
    return city;
  }

  public boolean isMarkTelephone() {
    return markTelephone;
  }

  public boolean isRemoval() {
    return removal;
  }
}
