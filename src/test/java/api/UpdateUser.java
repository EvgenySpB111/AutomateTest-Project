package api;

public class UpdateUser {
  public String name;
  public String job;

  public UpdateUser(){

  }
  public UpdateUser(String name, String job) {
    this.name = name;
    this.job = job;
  }
  public String getName() {
    return name;
  }

  public String getJob() {
    return job;
  }
}
