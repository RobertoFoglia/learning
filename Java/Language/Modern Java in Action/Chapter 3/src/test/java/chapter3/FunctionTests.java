package chapter3;

import java.util.function.Function;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/** @@@ Function examples PAG 74 */
@RunWith(MockitoJUnitRunner.class)
public class FunctionTests {
  @Test
  public void functionTest() {
    Function<Integer, Integer> sum = integer -> integer + 1;
    Function<Integer, Integer> product = integer -> integer * 2;

    // sum(product(2))
    int result = sum.andThen(product).apply(2);
    Assert.assertEquals(6, result);
    // product(sum(2))
    result = sum.compose(product).apply(2);
    Assert.assertEquals(5, result);
  }
}
