package innerclass;

/**
 * https://stackoverflow.com/questions/57581397/how-instantiating-an-inner-class-in-java-really-works
 *
 * @@@ New instance of Inner class
 */
public class OuterClass {
    public static class StaticInnerClass {
        int i = 0;

        public String call() {
            i++;
            return this.getClass().getName() + i;
        }
    }
}
