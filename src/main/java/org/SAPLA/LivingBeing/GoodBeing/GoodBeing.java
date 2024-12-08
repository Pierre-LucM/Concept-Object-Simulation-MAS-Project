package org.SAPLA.LivingBeing.GoodBeing;

import org.SAPLA.LivingBeing.LivingBeing;

public abstract class GoodBeing extends LivingBeing {
    private static int _instancesCount = 0;

    public GoodBeing() {
        super();
        _instancesCount++;
    }

    public static int getInstancesCount() {
        return _instancesCount;
    }
}
