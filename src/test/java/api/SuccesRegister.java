package api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccesRegister {
  public Integer id;
  public String token;

  public SuccesRegister() {
  }

  public SuccesRegister(Integer id,String token) {
    this.id = id;
    this.token = token;
  }

  public Integer getId() {
    return id;
  }

  public String getToken() {
    return token;
  }
}
