/*
В этом тесте мы проверяем ключевое слово в ссылке при переходе по поисковому запросу
 */
package appleinsider.ru;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {
  public void start(){
    WebDriverManager.chromedriver().setup();
     Configuration.browser = "chrome";
    // System.setProperty("selenide.browser", "chrome"); //можно использовать для запуска браузера
    Configuration.driverManagerEnabled = true; // подтверждаем что у нас есть библиотека WebDriverManager
    Configuration.browserSize = "1920x1080";
    //Configuration.headless = true;    //указываем что не  хотим  видеть работу браузера в тесте
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
