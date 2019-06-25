package rfoglia.chapter2.predicates;

import rfoglia.chapter2.model.Apple;
import rfoglia.chapter2.model.Color;

public class AppleRedAndHeavyPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getColor() == Color.RED && apple.getWeight() > 150;
    }
}
