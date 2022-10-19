package part4_Kucoin;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class KuCoinTests {
  static final String BASEURL = "https://api.kucoin.com";

 public List<Ticker> getListTicker() {
   return given()
           .contentType(ContentType.JSON)
           .when()
           .get(BASEURL+"/api/v1/market/allTickers")
           .then().log().all()
           .statusCode(200)
           .statusLine("HTTP/1.1 200 OK")
           .extract().body().jsonPath().getList("data.ticker",Ticker.class);

 }

  @Test
  public void checkCrypto() {
   List<Ticker> usdTickers = getListTicker().stream().filter(x -> x.getSymbol().endsWith("USDT")).collect(Collectors.toList());
   Assert.assertTrue(usdTickers.stream().allMatch(x -> x.getSymbol().endsWith("USDT")));
  }

  @Test
  public void sortHighToLow() {
    List<Ticker> highToLow = getListTicker().stream().filter( x -> x.getSymbol().endsWith("USDT")).sorted(new Comparator<Ticker>() {
      @Override
      public int compare(Ticker o1, Ticker o2) {  //если нужно сортировать от большего к меньшему начинаем с о2
                                                  // если сортируем от меньшего к большеиу начинаем с о1
        return o2.getChangeRate().compareTo(o1.getChangeRate());
      }
    }).collect(Collectors.toList());
    List<Ticker> top10 = highToLow.stream().limit(10).collect(Collectors.toList()); // создаем новый список с 10 элеиентами( устанавливаем limit())
    Assert.assertEquals(highToLow.get(0).getSymbol(),"MNW-USDT");
    Assert.assertEquals(highToLow.get(9).getSymbol(),top10.get(9).getSymbol());
  }
  @Test
  public void sortLowToHigh() {
  List<Ticker> lowToHigh = getListTicker().stream().filter(x -> x.getSymbol().endsWith("USDT")).
          sorted(new ComparatorLow()).limit(10).collect(Collectors.toList());
  }
  @Test
  public  void map() {
   List<String> names = getListTicker().stream().map(x -> x.getSymbol().toLowerCase()).collect(Collectors.toList());
   Map<String,Float> maps = new HashMap<>();
   getListTicker().stream().map(x-> maps.put(x.getSymbol(), Float.parseFloat(x.getBuy()))).collect(Collectors.toList());

  }

  @Test
  public void mapClassPojo() {
   List<MapClass> collection = new ArrayList<>();
   getListTicker().stream().map(x -> collection.add(new MapClass(x.getSymbol(),Float.parseFloat(x.getBuy())))).collect(Collectors.toList());
  }




}
