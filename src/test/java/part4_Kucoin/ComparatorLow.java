package part4_Kucoin;

import org.junit.Test;

import java.util.Comparator;

public class ComparatorLow implements Comparator<Ticker> {

  @Override
  public int compare(Ticker o1, Ticker o2) {
    float num = Float.compare(Float.parseFloat(o1.getChangeRate()),Float.parseFloat(o2.getChangeRate()));
         // переводим строку в строку с плавающей точкой
    return (int)num;
  }


}
