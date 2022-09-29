package appleinsider.ru;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

/*
 * Главная страница сайта Appleinsider.ru
 */

public class StartPage {
  public StartPage(String url){
    Selenide.open(url);
  }

 private final SelenideElement buttonSearch = $x("//input[@type='text']");
  private final SelenideElement textSearch = $x("//input");

  /**
   * Осуществляется ввод данных в поисковое поле и нажимается Enter
   * @param quest текст поиска
   */
 public SearchPage clickTest(String quest){
   buttonSearch.setValue(quest);
   textSearch.sendKeys( Keys.ENTER);
   return new SearchPage();
 }



}
