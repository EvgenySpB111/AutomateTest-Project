package api;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ReqresInTest {
private final static String URL ="https://reqres.in/";


  @Test
  public void nameAndAvatarTest() {
    List<UserData> users =
             given()
            .when()
            .contentType(ContentType.JSON)
            .get("https://reqres.in/api/users?page=2")
            .then().log().all()
            .extract().body().jsonPath().getList("data",UserData.class);
     users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
     users.forEach(x -> Assert.assertTrue(x.getEmail().contains("reqres.in")));
     Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("reqres.in")));
  }
  @Test
  public void nameAndAvatarTest2() {
    Specification.installSpecification(Specification.requestSpec(URL),Specification.responseSpeOK200());
    List<UserData> users =
            given()
                    .when()
                    .get("/api/users?page=2")
                    .then().log().all()
                    .extract().body().jsonPath().getList("data",UserData.class);
    users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
    users.forEach(x -> Assert.assertTrue(x.getEmail().contains("reqres.in")));
    Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("reqres.in")));
  }
}
