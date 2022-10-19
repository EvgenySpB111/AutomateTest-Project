package part4_Kucoin;

public class MapClass {
  private String name;
  private Float buy;
  public MapClass(){}
  public MapClass(String name, Float buy) {
    this.name = name;
    this.buy = buy;
  }

  public String getName() {
    return name;
  }

  public Float getBuy() {
    return buy;
  }
}
