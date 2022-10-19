/*
В этом тесте мы проверяем ключевое слово в ссылке при переходе по поисковому запросу
 */
package appleinsider.ru;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;

public abstract class BaseTestSelenide {
  public void start(){
    WebDriverManager.chromedriver().setup();
    Configuration.browser = "chrome";
    Configuration.driverManagerEnabled = true;
    Configuration.browserSize = "1920x1080";
    Configuration.headless = false;
  }
@Before
  public void setUp() {
    start();
}


@After
  public void tearDown(){
  Selenide.closeWebDriver();
}


}
