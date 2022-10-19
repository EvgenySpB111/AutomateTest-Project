package part4_Kucoin;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class PartDummyApi {
 public final String BASE_URL = "https://dummyapi.io/data/v1/";


  @Test
  public void userList() {
    Response response = given()
            .header("app-id","634ea704fba8af9c5c72c2a3")
            .contentType(ContentType.JSON)
            .when()
            .get(BASE_URL + "/user")
            .then().log().all()
            .statusLine("HTTP/1.1 200 OK")
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    List<String> names = jsonPath.getList("data.firstName");
    Assert.assertEquals("Sara", names.get(0));

  }
  @Test
  public void invalidGetRequest() {
    Response response = given()
            .contentType(ContentType.JSON)
            .when()
            .get(BASE_URL + "/user")
            .then().log().all()
            .statusCode(403)
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    Assert.assertEquals("APP_ID_MISSING",jsonPath.get("error"));

  }
  @Test
  public void limitTest10() {
    Response response = given()
            .contentType(ContentType.JSON)
            .header("app-id","634ea704fba8af9c5c72c2a3")
            .when()
            .get("https://dummyapi.io/data/v1/user?page=1&limit=10")
            .then().log().all()
            .statusCode(200)
            .header("Content-Length","1564")
            .extract().response();

    JsonPath jsonPath = response.jsonPath();
    List<String> numbers = jsonPath.getList("data.id");
    int page = jsonPath.get("page");
    Assert.assertEquals(10,numbers.size());
    Assert.assertEquals(1,page);
  }
  @Test
  public void createUser() {
    Map<String,String> request = new HashMap<>();
    request.put("firstName","Jeka");
    request.put("lastName","Smorj");
    request.put("email","lora278a@mail.ru");
    Response response = given()
            .body(request)
            .contentType(ContentType.JSON)
            .header("app-id","634ea704fba8af9c5c72c2a3")
            .when()
            .post(BASE_URL + "user/create")
            .then().log().all()
            .statusCode(200)
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    String name = jsonPath.get("firstName");
    String lastName = jsonPath.get("lastName");
    Assert.assertEquals(request.get("firstName"),name);
    Assert.assertEquals(request.get("lastName"),lastName);
  }

  @Test
  public void updateUserTest() {
    Map<String,String> updateUser = new HashMap<>();
    updateUser.put("firstName","Jeka-krasava1");
    updateUser.put("phone","89967784588");
    Response response = given()
            .body(updateUser)
            .header("app-id","634ea704fba8af9c5c72c2a3")
            .contentType(ContentType.JSON)
            .when()
            .put("https://dummyapi.io/data/v1/user/634eb926241900485a156952")
            .then().log().all()
            .statusCode(200)
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    String name = jsonPath.get("firstName");
    Assert.assertEquals("Jeka-krasava1", name);
    Assert.assertEquals("89967784588",jsonPath.get("phone"));
  }
  @Test
  public void  checkUserTest() {
    Response response = given()
            .header("app-id","634ea704fba8af9c5c72c2a3")
            .contentType(ContentType.JSON)
            .when()
            .get("https://dummyapi.io/data/v1/user/634eb926241900485a156952")
            .then().log().all()
            .statusCode(200)
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    Assert.assertEquals("634eb926241900485a156952", jsonPath.get("id")); //634eb926241900485a156952
  }
  @Test
  public void checkPost() {
    Response response = given()
            .header("app-id","634ea704fba8af9c5c72c2a3")
            .contentType(ContentType.JSON)
            .when()
            .get("https://dummyapi.io/data/v1/post")
            .then().log().all()
            .statusCode(200)
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    List<String> text = jsonPath.getList("data.text");
    Assert.assertEquals("Hello World",text.get(0));
  }
  @Test
  public  void createPost() {
    Map<String,String> dataPost = new HashMap<>();
    dataPost.put("text", "Hello World");
    dataPost.put("image","https://randomuser.me/api/portraits/women/58.jpg");
    dataPost.put("likes", "25") ;
    dataPost.put("tags", "Java");
    dataPost.put ("owner", "634eb926241900485a156952");
      Response response = given()
              .header("app-id","634ea704fba8af9c5c72c2a3")
              .body(dataPost)
              .contentType(ContentType.JSON)
              .when()
              .post("https://dummyapi.io/data/v1/post/create")
              .then().log().all()
              .statusCode(200)
              .extract().response();
      JsonPath jsonPath = response.jsonPath();
      Assert.assertEquals("Hello World", jsonPath.get("text"));
      Assert.assertEquals("Jeka-krasava1", jsonPath.get("owner.firstName"));
  }
  @Test
  public void checkPostTest() {
    String idPost = "634ff7f19e9e09206b1d4361";
    Response response = given()
            .header("app-id","634ea704fba8af9c5c72c2a3")
            .contentType(ContentType.JSON)
            .when()
            .get(BASE_URL + "post/" + idPost)
            .then().log().all()
            .statusCode(200)
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    Assert.assertEquals(idPost, jsonPath.get("id"));
  }
  @Test
  public void postUserCheckTest() {
    String idUser = "634eb926241900485a156952";
    Response response = given()
            .header("app-id","634ea704fba8af9c5c72c2a3")
            .contentType(ContentType.JSON)
            .when()
            .get(BASE_URL + "user/"+ idUser + "/post")
            .then().log().all()
            .statusCode(200)
            .extract().response();
   JsonPath jsonPath = response.jsonPath();
   List<String> text = new ArrayList<>();
   text = jsonPath.getList("data.text");
   Assert.assertEquals("Hello World",text.get(0));
  }
 @Test
  public void createCommentTest() {
    Map<String,String> commentMap = new HashMap<>();
     commentMap.put("message", "Этот комментарий сделан в IdeA") ;
     commentMap.put("owner", "634eb926241900485a156952");
     commentMap.put("post", "635004889e9e09b4b81d4d0a");
     Response response = given()
             .body(commentMap)
             .header("app-id","634ea704fba8af9c5c72c2a3")
             .contentType(ContentType.JSON)
             .when()
             .post(BASE_URL + "comment/create")
             .then().log().all()
             .statusCode(200)
             .extract().response();
     JsonPath jsonPath = response.jsonPath();
     Assert.assertEquals("Этот комментарий сделан в IdeA",jsonPath.get("message"));
 }
 @Test
  public void deleteUser() {
    String idUser = "635046a596d1c710cd0cd58a";
    Response response = given()
            .header("app-id","634ea704fba8af9c5c72c2a3")
            .contentType(ContentType.JSON)
            .when()
            .delete(BASE_URL + "user/" + idUser)
            .then().log().all()
            .statusCode(200)
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    Assert.assertEquals(idUser,jsonPath.get("id"));
 }
 @Test
  public void checkDeleteUser() {
    Response response = given()
            .header("app-id","634ea704fba8af9c5c72c2a3")
            .contentType(ContentType.JSON)
            .when()
            .get( "https://dummyapi.io/data/v1/user/635046a596d1c710cd0cd58a ")
            .then().log().all()
            .statusCode(404)
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    Assert.assertEquals("RESOURCE_NOT_FOUND", jsonPath.get("error"));
 }
}
