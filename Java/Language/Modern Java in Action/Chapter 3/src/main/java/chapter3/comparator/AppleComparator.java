package chapter3.comparator;

import java.util.Comparator;
import rfoglia.chapter2.model.Apple;

public class AppleComparator implements Comparator<Apple> {
  @Override
  public int compare(Apple a1, Apple a2) {
    return a1.getWeight() - a2.getWeight();
  }
}
