package innerclass;

public class Main {
    public static void main(String[] args) {

        /**
         * https://stackoverflow.com/questions/57581397/how-instantiating-an-inner-class-in-java-really-works
         *
         * @@@ New instance of Inner class
         */
        OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();

        System.out.println(staticInnerClass.call());
        System.out.println(staticInnerClass.call());


        staticInnerClass = new OuterClass.StaticInnerClass();
        System.out.println("\n" + staticInnerClass.call());
        System.out.println(staticInnerClass.call());
    }
}
