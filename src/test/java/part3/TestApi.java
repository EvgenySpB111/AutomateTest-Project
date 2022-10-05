package part3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

  public class TestApi {
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

  }

