/*
В этом тесте мы 2-мя(сравниваем списки и сравниваем классы) способами тестируем резуме работника
 */
package hhTest;

import core.BaseTestSelenide;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HHruTest extends BaseTestSelenide {
  private  final  static String URL = "https://spb.hh.ru/resume/eec18e26ff0b4584840039ed1f456337777132";

  @Test
  public void checkAttributesHashMap() {
  ResumePage resumePage = new ResumePage(URL);
  Map<String,Object> expectedAttributes = new HashMap<>();
    expectedAttributes.put(ResumePage.GENDER,"М");
    expectedAttributes.put(ResumePage.AGE,29);
    expectedAttributes.put(ResumePage.CITY,"Санкт-Петербург");
    expectedAttributes.put(ResumePage.MARK_TELEPHONE, true);
    expectedAttributes.put(ResumePage.REMOVAL,true);
    Map<String,Object> actualAttributes = resumePage.getAttributes();
    Assert.assertEquals(expectedAttributes,actualAttributes);
  }



  @Test
  public void test1() {

  }
  @Test
  public void CheckResumeTest(){
    ResumePage resumePage = new ResumePage(URL);
    Resume expectedResume = new Resume("М",29,"Санкт-Петербург",true,true);
    Resume actualResume = new Resume(resumePage.getGender()
         ,resumePage.getAge(),resumePage.getCity(),resumePage.getMarkTelephone(),resumePage.getRemoval());
   // Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedResume,actualResume)); // сравнение классов

    Assert.assertEquals(expectedResume.getGender(),actualResume.getGender());
    Assert.assertEquals(expectedResume.getAge(),actualResume.getAge());
    Assert.assertEquals(expectedResume.getCity(),actualResume.getCity());
    Assert.assertEquals(expectedResume.isMarkTelephone(),actualResume.isMarkTelephone());
    Assert.assertEquals(expectedResume.isRemoval(),actualResume.isRemoval());
    System.out.println();
  }

}
