package org.SAPLA.Map;

public class SafeZone {
    private static int _instancesCount = 0;
    private final Tile[][] _safeZone;

    public SafeZone(Tile[][] safeZone) {
        _instancesCount++;
        _safeZone = safeZone;
    }
    public Tile[][] getSafeZoneGrid() {
        return _safeZone;
    }

    public static int getInstancesCount() {
        return _instancesCount;
    }
}
