package appleinsider.ru;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;

/*
Страница результат поиска статей
 */
public class SearchPage {
                                               // для коллекции используем 2 доллара
 private  final ElementsCollection headers = $$x("//a[@rel='bookmark']");

  /**
   * Возвращает href из первой статьи
   */
 public String getTextHref(){
  return headers.first().getAttribute("href");
 }


}
