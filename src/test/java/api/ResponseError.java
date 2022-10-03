package api;

public class ResponseError {
  public String error;

  public ResponseError() {
  }
  public ResponseError(String error) {
    this.error = error;
  }

  public String getError() {
    return error;
  }
}
