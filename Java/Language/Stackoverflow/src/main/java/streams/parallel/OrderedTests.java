package streams.parallel;

import java.util.stream.IntStream;

public class OrderedTests {
    public static void main(String[] args) {
        // Creating an IntStream
        for (int j = 0; j < 100; j++) {
            IntStream stream = IntStream.range(1, 1000);

            // It doesn't prints in ordered
//        stream.parallel().forEach(value -> System.out.println(value));

            int[] ints = stream.parallel().map(operand -> operand + 10).toArray();

            int oldValue = -1;
            for (int i=0; i < ints.length; i++){
                if(oldValue > ints[i])
                    System.err.println(oldValue +  ">" + ints[i]);
                oldValue = ints[i];
            }
        }

        /**  @@@ Parallel stream keeps the ordered results, but it doesn't guarantee the process order  */
    }
}
