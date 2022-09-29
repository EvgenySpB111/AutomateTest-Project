package appleinsider.ru;

import org.junit.Assert;
import org.junit.Test;

public class SecondTest extends BaseTest{
  private final static String  BASE_URL = "https://appleinsider.ru";
  private final static String  REQUEST = "чем отличается Ipnone 13 от Iphone 12";

  @Test
  public void checkHrefTest() {
    String href = new StartPage(BASE_URL).clickTest(REQUEST).getTextHref();
    Assert.assertTrue(href.contains("iphone-11"));
  }



}
