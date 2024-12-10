package org.SAPLA.LivingBeing.BadBeing;

import org.SAPLA.LivingBeing.LivingBeing;

public abstract class BadBeing extends LivingBeing {
    public static int _instancesCount = 0;

    public BadBeing() {
        super();
        _instancesCount++;
    }

    public static int getInstancesCount() {
        return _instancesCount;
    }
}
