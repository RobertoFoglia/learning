import static model.Dish.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import model.Dish;

/**
 * @@@ Reducing pag 111
 */
public class Reducing {

  public static void main(String... args) {
    List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
    int sum = numbers.stream().reduce(0, (a, b) -> a + b);
    System.out.println(sum);

    int sum2 = numbers.stream().reduce(0, Integer::sum);
    System.out.println(sum2);

    int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
    System.out.println(max);

    numbers.stream().reduce(Integer::max).ifPresent(System.out::println);

    Optional<Integer> min = numbers.stream().reduce(Integer::min);
    min.ifPresent(System.out::println);

    int calories = menu.stream()
        .map(Dish::getCalories)
        .reduce(0, Integer::sum);
    System.out.println("Number of calories:" + calories);


    // Quiz 5.3: Reducing
    System.out.println(menu.stream().map(dish -> 1).reduce(0, Integer::sum));
    System.out.println(menu.stream().count());
  }

}
