package hhTest;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$x;

/*
Страница резюмк на hh.ru
 */
public class ResumePage {
 private final SelenideElement sex = $x("//span[@data-qa='resume-personal-gender']");
 private final SelenideElement age = $x("//span[@data-qa='resume-personal-age']/span");
 private final SelenideElement address = $x("//span[@data-qa='resume-personal-address']");
 private  final SelenideElement live = $x("//span[@data-qa='resume-personal-address']/ancestor::p");
 private final SelenideElement markTelephone = $x("//span[@class='bloko-icon bloko-icon_done bloko-icon_initial-secondary']");

  public static String GENDER = "Пол";
  public static String AGE = "Возраст";
  public static String CITY = "Город";
  public static String MARK_TELEPHONE = "Подтвержденный номер";
  public static String REMOVAL = "Готовность к переезду";

  public Map< String, Object> getAttributes() {
    Map<String, Object> attributes = new HashMap<>();
    attributes.put(GENDER, getGender());
    attributes.put(AGE,getAge());
    attributes.put(CITY,getCity());
    attributes.put(MARK_TELEPHONE, getMarkTelephone());
    attributes.put(REMOVAL,getRemoval());
    return attributes;
  }

    public ResumePage(String url){
      Selenide.open(url);
    }


   public String getGender(){
     String textGender = sex.getText();
     if(textGender.equals("Мужчина")){
       return "М";
     } else  return "Ж";
   }
   public int getAge(){
    return Integer.parseInt(age.getText().replaceAll("\\D+",""));
   }
   public String getCity(){
     return address.getText();
   }
   public boolean getRemoval(){
     String [] array = live.getText().split(", ");
     return array[2].equals("готов к переезду");
   }
   public boolean getMarkTelephone(){
     return markTelephone.isDisplayed();
   }






}
