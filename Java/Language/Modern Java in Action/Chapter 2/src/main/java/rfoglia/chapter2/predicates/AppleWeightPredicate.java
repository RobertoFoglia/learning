package rfoglia.chapter2.predicates;

import rfoglia.chapter2.model.Apple;

public class AppleWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
