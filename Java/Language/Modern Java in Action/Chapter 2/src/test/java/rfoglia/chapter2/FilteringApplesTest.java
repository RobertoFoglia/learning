package rfoglia.chapter2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import rfoglia.chapter2.model.Apple;
import rfoglia.chapter2.model.Color;
import rfoglia.chapter2.predicates.AppleColorPredicate;
import rfoglia.chapter2.predicates.ApplePredicate;
import rfoglia.chapter2.predicates.AppleRedAndHeavyPredicate;
import rfoglia.chapter2.predicates.AppleWeightPredicate;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FilteringApplesTest {
    @Test
    public void main() {
        Apple green80Apple = new Apple(80, Color.GREEN);
        Apple green155apple = new Apple(155, Color.GREEN);
        Apple red120Apple = new Apple(120, Color.RED);
        List<Apple> inventory = Arrays.asList(
                green80Apple,
                green155apple,
                red120Apple);

        // [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
        List<Apple> greenApples = FilteringApples.filterApplesByColor(inventory, Color.GREEN);
        assertEquals(Arrays.asList(green80Apple, green155apple), greenApples);

        // [Apple{color=RED, weight=120}]
        List<Apple> redApples = FilteringApples.filterApplesByColor(inventory, Color.RED);
        assertEquals(Arrays.asList(red120Apple), redApples);

        // [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
        List<Apple> greenApples2 = FilteringApples.filter(inventory, new AppleColorPredicate());
        assertEquals(Arrays.asList(green80Apple, green155apple), greenApples2);

        // [Apple{color=GREEN, weight=155}]
        List<Apple> heavyApples = FilteringApples.filter(inventory, new AppleWeightPredicate());
        assertEquals(green155apple, heavyApples.get(0));

        // []
        List<Apple> redAndHeavyApples = FilteringApples.filter(inventory, new AppleRedAndHeavyPredicate());
        assertEquals(EMPTY_LIST, redAndHeavyApples);

        // [Apple{color=RED, weight=120}]
        List<Apple> redApples2 = FilteringApples.filter(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple a) {
                return a.getColor() == Color.RED;
            }
        });
        assertEquals(red120Apple, redApples2.get(0));
    }
}