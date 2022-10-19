package part3;

import api.Specification;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

  public class TestApi {

    @Test
    public void authTest() {
      Map<String,String> login = new HashMap<>();
      login.put("username" ,"admin");
      login.put("password" ,"password123");
      Response response = given()
              .body(login)
              .when()
              .contentType(ContentType.JSON)
              .post("https://restful-booker.herokuapp.com/auth")
              .then().log().all()
              .statusCode(200)
              .extract().response();
      JsonPath jsonPath = response.jsonPath();
      String token = jsonPath.get("token");
      System.out.println(token);
    }
    @Test
    public void getResponseTest() {
      Response response = given()
              .when()
              .contentType(ContentType.JSON)
              .get("http://httpbin.org/get")
              .then().log().all()
              .statusCode(200)
              .statusLine("HTTP/1.1 200 OK")
              .extract().response();
      JsonPath jsonPath = response.jsonPath();
      String origin = jsonPath.get("origin").toString();
      String contentType = jsonPath.get("headers.Host");
      String header = response.header("Content-Type"); //так  мы получаем  заголовок
      String acces = response.getHeader("Access-Control-Allow-Credentials");
      String url = jsonPath.get("url");
      Assert.assertEquals("85.143.144.145",origin);
      Assert.assertEquals("httpbin.org",contentType);
      Assert.assertEquals("application/json",response.header("Content-Type"));
      Assert.assertEquals("true",acces);
      Assert.assertEquals("http://httpbin.org/get", jsonPath.get("url"));
      System.out.println(url);
    }
    @Test
    public void deleteResponseTest(){
      Response response = given()
              .when()
              .contentType(ContentType.JSON)
              .delete("http://httpbin.org/delete")
              .then().log().all()
              .statusCode(200)
              .statusLine("HTTP/1.1 200 OK")
              .contentType("application/json")
              .header("Content-Length","432")
              .header("Server","gunicorn/19.9.0")
              .extract().response();
      JsonPath jsonPath = response.jsonPath();
      Assert.assertEquals("gzip,deflate",jsonPath.get("headers.Accept-Encoding"));
      Assert.assertEquals("85.143.144.145",jsonPath.get("origin"));
    }
    @Test
    public void postResponseTest() {
      Map<String,String> user = new HashMap<>();
      user.put("username","admin");
      user.put("password","password123");
      Response response = given()
              .body(user)
              .when()
              .contentType(ContentType.JSON)
              .post("https://restful-booker.herokuapp.com/auth ")
              .then().log().all()
              .statusCode(200)
              .statusLine("HTTP/1.1 200 OK")
              .header("Server","Cowboy")
              .contentType("application/json; charset=utf-8")
              .header("Content-Length","27")
              .extract().response();
      JsonPath jsonPath = response.jsonPath();
      // Assert.assertEquals("b8895cc7850ac77",jsonPath.get("token"));
    }
    @Test
    public void bookGetTest() {
      Response response = given()
              .when()
              .contentType(ContentType.JSON)
              .get("https://restful-booker.herokuapp.com/booking")
              .then().log().all()
              .statusCode(200)
              .statusLine("HTTP/1.1 200 OK")
              .contentType("application/json; charset=utf-8")
              .header("Via","1.1 vegur")
              .header("Server","Cowboy")
              .extract().response();
      JsonPath jsonPath = response.jsonPath();
      List<Integer> books = jsonPath.getList("bookingid");
      int firstBook = books.get(0);
      int finalBook = books.get(books.size()-1);
     // Assert.assertEquals(6383,firstBook);
      //Assert.assertEquals(2562,finalBook);

    }
    @Test
    public void createBookApiTest() {
    CreateBooking createBook = new CreateBooking("Jim","Brown",111,true,
            new Bookingdates("2018-01-01", "2019-01-01"),"Breakfast");
    Response response =  given()
            .body(createBook)
            .when()
            .contentType(ContentType.JSON)
            .post("https://restful-booker.herokuapp.com/booking")
            .then().log().all()
            .statusCode(200)
            .statusLine("HTTP/1.1 200 OK")
            .contentType("application/json; charset=utf-8")
            .header("Content-Length", "197")
            .extract().response();
    JsonPath jsonPath = response.jsonPath();
    int bookId = jsonPath.get("bookingid");
    String name = jsonPath.get("booking.firstname");
    String data = jsonPath.get("booking.bookingdates.checkout");
    String add = jsonPath.get("booking.additionalneeds");
   // Assert.assertEquals(9941,bookId); постоянно изменяется
    Assert.assertEquals("Jim",name);
    Assert.assertEquals("2019-01-01",data);
    Assert.assertEquals("Breakfast",add);

    }
    @Test
    public void delayedResponseTest() {
      Response response = given()
              .queryParam("id",1)
              .when()
              .contentType(ContentType.JSON)
              .get("https://reqres.in/api/users?delay=3")
              .then().log().all()
              .statusCode(200)
              .statusLine("HTTP/1.1 200 OK")
              .extract().response();
      JsonPath jsonPath = response.jsonPath();
      int perPage = jsonPath.get("per_page");
      List<String> name = jsonPath.get("data.first_name");
      String nameFirst = name.get(0);
      Assert.assertEquals(6,perPage);
      Assert.assertEquals("George",nameFirst);
    }
    @Test
    public void createUser() {
      Map< String,String> create = new HashMap<>();
      create.put("name","morpheus");
      create.put("job","leader");
      Response response = given()
              .body(create)
              .when()
              .contentType(ContentType.JSON)
              .post("https://reqres.in/api/users")
              .then().log().all()
              .statusCode(201)
              .statusLine("HTTP/1.1 201 Created")
              .extract().response();
      JsonPath jsonPath = response.jsonPath();
      Assert.assertEquals("morpheus",jsonPath.get("name"));
      Assert.assertEquals("leader",jsonPath.get("job"));
    }

  }

