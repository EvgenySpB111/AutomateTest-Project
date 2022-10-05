package wikipedia;

import appleinsider.ru.BaseTest;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.open;

public class Wikipedia extends BaseTest {
  @Test
  public void wikipediaHrefTest() {
    Selenide.open("https://ru.wikipedia.org/wiki/Java");
    ElementsCollection href = $$x("//div[@id='toc']//a");
    List<String> links = new ArrayList<>();
    href.forEach(x -> links.add(x.getAttribute("href"))); // третий способ
  /* for(int x = 0;x < href.size(); x++){             // первый способ
      links.add(href.get(x).getAttribute("href"));
   }
    for(SelenideElement element: href){          // второй способ
      links.add(element.getAttribute("href"));
    }

    //links.forEach(x -> open(x)); // открываем все ссылки что есть в коллекции links
    for( int x = 0; x < links.size(); x++){
      String linkUrl = links.get(x);
      Selenide.open(linkUrl);
      String browserUrl = WebDriverRunner.getWebDriver().getCurrentUrl(); //получаем ссылку с открытой страницы
      Assert.assertEquals(linkUrl,browserUrl);
    }
  */
    Random random = new Random();

    for(int x = 0; x < links.size();x++) {
      int numberRand = random.nextInt(links.size());
      Selenide.open(links.get(numberRand));
      links.remove(x);
    }
    System.out.println(links);


  }
  @Test
  public void testTest() {
    Selenide.open("https://ah.stopfilms.info/");
    String href = WebDriverRunner.getWebDriver().getCurrentUrl();
    System.out.println(href);
    Random random = new Random();

    for(int x =0; x < 20; x++){
      int randomNumb = random.nextInt() ;
      System.out.println(randomNumb);
    }


  }

}
