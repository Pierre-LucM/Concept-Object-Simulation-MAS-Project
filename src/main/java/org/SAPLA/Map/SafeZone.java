package org.SAPLA.Map;

public class SafeZone {
    private final Tile[][] _safeZone;

    public SafeZone(Tile[][] safeZone) {
        _safeZone = safeZone;
    }
    public Tile[][] getSafeZoneGrid() {
        return _safeZone;
    }
}
