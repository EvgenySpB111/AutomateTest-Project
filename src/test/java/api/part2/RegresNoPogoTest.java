package api.part2;

import api.Specification;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RegresNoPogoTest {


  @Test
  public void checkAvatarNoPogoTest() {
    Specification.responseStatus(200);
    io.restassured.response.Response response = given()
            .when()
            .contentType(ContentType.JSON)
            .get("https://reqres.in/api/users?page=2")
            .then().log().all()
            .body("total", equalTo(12))
            .body("data.id", notNullValue())
            .body("data.email",notNullValue())
            .body("data.first_name",notNullValue())
            .body("data.last_name",notNullValue())
            .body("data.avatar",notNullValue())
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    List<Integer> ids = jsonPath.get("data.id");
    List<String> emailUsers = jsonPath.get("data.email");
    List<String> avatars = jsonPath.get("data.avatar");

    for( int x = 0; x < avatars.size(); x ++){
      Assert.assertTrue(avatars.get(x).contains(ids.get(x).toString()));
    }

    for( int x = 0 ; x < emailUsers.size(); x ++){
      Assert.assertTrue(emailUsers.get(x).endsWith("reqres.in"));
    }
   // Assert.assertTrue(emailUsers.stream().allMatch(x -> x.endsWith("reqres.in")));
  }
@Test
  public void registerNoPojoTest() {
    Specification.responseStatus(200);
    Map<String,String> user = new HashMap<>();

    user.put("email","eve.holt@reqres.in");
    user.put("password","pistol");
    given()
            .body(user)
            .when()
            .contentType(ContentType.JSON)
            .post("https://reqres.in/api/register")
            .then().log().all()
            .body("id",equalTo(4))
            .body("token",equalTo("QpwL5tke4Pnpja7X4"));
}
  @Test
  public void registerNoPojoResponseTest() {
    Specification.responseStatus(200);
    Map<String,String> user = new HashMap<>();
    user.put("email","eve.holt@reqres.in");
    user.put("password","pistol");

    io.restassured.response.Response response =
    given()
            .body(user)
            .when()
            .contentType(ContentType.JSON)
            .post("https://reqres.in/api/register")
            .then().log().all()
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    int id = jsonPath.get("id");
    String token = jsonPath.get("token");
    Assert.assertEquals(4,id);
    Assert.assertEquals("QpwL5tke4Pnpja7X4",token);
  }
  @Test
  public void errorRegister() {
    Specification.responseStatus(400);
    Map<String,String> register = new HashMap<>();
    register.put("email","sydney@fife");
    given()
            .body(register)
            .when()
            .contentType(ContentType.JSON)
            .post("https://reqres.in/api/register")
            .then().log().all()
            .body("error",equalTo("Missing password"));
  }
  @Test
  public void errorRegisterResponse() {
    Specification.responseStatus(400);
    Map<String,String> register = new HashMap<>();
    register.put("email","sydney@fife");
    Response response =
    given()
            .body(register)
            .when()
            .contentType(ContentType.JSON)
            .post("https://reqres.in/api/register")
            .then().log().all()
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    String error = jsonPath.get("error");
    Assert.assertEquals("Missing password",error);

  }




}