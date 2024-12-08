package org.SAPLA.Map;

public class Tile {

    private static int _instancesCount = 0;
    private char _tileContent;
    private final boolean _isSafeZone;
    private final Position _position;

    public Tile(char tileContent, boolean isSafeZone, Position position) {
        _instancesCount++;
        _tileContent = tileContent;
        _isSafeZone = isSafeZone;
        _position = position;
    }
    public char getTileContent() {
        return _tileContent;
    }
    public void setTileContent(char tileContent) {
        this._tileContent = tileContent;
    }
    public boolean isSafeZone() {
        return _isSafeZone;
    }
    public Position getPosition() {
        return _position;
    }

    public Tile getTile() {
        return this;
    }

    public static int getInstancesCount() {
        return _instancesCount;
    }
}
