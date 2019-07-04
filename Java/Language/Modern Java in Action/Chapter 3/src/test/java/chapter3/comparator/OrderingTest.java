package chapter3.comparator;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import rfoglia.chapter2.model.Apple;
import rfoglia.chapter2.model.Color;

/**
 * @@@ Order with Comparator, anonymous class, lambdas and method references
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderingTest {
  private static final Logger LOGGER = LogManager.getLogger(OrderingTest.class);

  private static final int MAX_WEIGHT = 155;
  private static final int MIN_WEIGHT = 80;
  private List<Apple> inventory;

  @Before
  public void setUp() {
    inventory = new ArrayList<>();
    inventory.addAll(Arrays.asList(
      new Apple(MIN_WEIGHT, Color.GREEN),
      new Apple(MAX_WEIGHT, Color.GREEN),
      new Apple(120, Color.RED)
    ));
  }

  @Test
  public void sort_with_comparator_class() {
    inventory.sort(new AppleComparator());
    orderCheck(inventory);
  }

  @Test
  public void sort_with_anonymous_class() {
    inventory.sort(new Comparator<Apple>() {
      @Override
      public int compare(Apple a1, Apple a2) {
        return a1.getWeight() - a2.getWeight();
      }
    });
    orderCheck(inventory);
  }

  @Test
  public void sort_with_lambda() {
    inventory.sort((a1, a2) -> a1.getWeight() - a2.getWeight());
    orderCheck(inventory);
  }

  @Test
  public void sort_with_comparing() {
    inventory.sort(Comparator.comparingInt(Apple::getWeight));
    orderCheck(inventory);
  }

  private void orderCheck(List<Apple> apples) {
    ListIterator<Apple> appleListIterator = apples.listIterator();
    int current = MIN_WEIGHT;
    while(appleListIterator.hasNext()) {
      Apple nextApple = appleListIterator.next();
      LOGGER.debug(nextApple.getColor() + " " + nextApple.getWeight());
      if (current > nextApple.getWeight()) {
        fail("this apple have to be more weight");
      }
      current = nextApple.getWeight();
    }
  }
}
