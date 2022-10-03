package api;

import java.util.Date;

public class ResponseUpdateUser {
  public String name;
  public String job;
  public String updatedAt;

  public ResponseUpdateUser() {
  }
  public ResponseUpdateUser(String name, String job, String updatedAt) {
    this.name = name;
    this.job = job;
    this.updatedAt = updatedAt;
  }

  public String getName() {
    return name;
  }

  public String getJob() {
    return job;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }
}
