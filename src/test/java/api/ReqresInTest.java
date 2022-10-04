package api;

import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;

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

  @Test
  public void registrationTest() {
 Specification.installSpecification(Specification.requestSpec(URL),Specification.responseSpeOK200());
 Integer id = 4;
 String token = "QpwL5tke4Pnpja7X4";
 Register user = new Register("eve.holt@reqres.in","pistol");
 SuccesRegister succesRegister = given()
         .body(user)
         //.contentType(ContentType.JSON)   можно не писать если есть спецификации
         .when()
         .post("/api/register")
         .then().log().all()
         .extract().as(SuccesRegister.class);
 Assert.assertNotNull(succesRegister.getId());
 Assert.assertNotNull(succesRegister.getToken());
 Assert.assertEquals(id,succesRegister.getId());
 Assert.assertEquals(token,succesRegister.getToken());
  }

@Test
  public void unsuccessfulRegistrationTest() {
   UnsuccRegistration unsuccRegistration = new UnsuccRegistration("sydney@fife");
   ResponseError responseError = given()
           .body(unsuccRegistration)
           .contentType(ContentType.JSON)
           .post("https://reqres.in/api/register")
           .then().log().all()
           .extract().as(ResponseError.class);
   Assert.assertNotNull(responseError.getError());
   Assert.assertEquals("Missing password",responseError.getError());
}
@Test
  public void colorsDataTest() {
  Specification.responseStatus(200);
    List<ColorsData> colorsData = given()
            .contentType(ContentType.JSON)
            .get("https://reqres.in/api/unknown")
            .then().log().all()
            .extract().body().jsonPath().getList("data", ColorsData.class);
    List<Integer> years = colorsData.stream().map(ColorsData::getYear).collect(Collectors.toList()); // создаем новый список и берем из него данные years
    List<Integer> sortYears = years.stream().sorted().collect(Collectors.toList()); // создаем новый список и сортируем его по возрастанию
    Assert.assertEquals(sortYears, years);

}
@Test
  public void deleteUserTest() {
    Specification.responseStatus(204);
    given().delete("https://reqres.in/api/users/2")
            .then().log().all();

}
@Test
  public void updateUserTest() {
    Specification.responseStatus(200);
    UpdateUser updateUser = new UpdateUser("morpheus","zion resident");
    ResponseUpdateUser responseUpdateUser =
            given()
                    .body(updateUser)
                    .contentType(ContentType.JSON)
                    .put("https://reqres.in/api/users/2")
                    .then().log().all()
                    .extract().as(ResponseUpdateUser.class);
    Assert.assertEquals(updateUser.getName(),responseUpdateUser.getName());
    Assert.assertEquals(updateUser.getJob(),responseUpdateUser.getJob());

    String regex = "(.{7})$";
    String times = Clock.systemUTC().instant().toString().replaceAll(regex,"");
    Assert.assertEquals(times,responseUpdateUser.getUpdatedAt().replaceAll(regex,""));

}



}
