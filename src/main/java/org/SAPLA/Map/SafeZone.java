package org.SAPLA.Map;

public class SafeZone {
    private Tile[][] _safeZone;

    public SafeZone(Tile[][] safeZone) {
        _safeZone = safeZone;
    }
    public Tile[][] getSafeZone() {
        return _safeZone;
    }
}
