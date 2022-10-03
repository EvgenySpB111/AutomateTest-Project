package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Register {
  public String email;
  public String password;

  public Register() {
  }

  public Register(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
