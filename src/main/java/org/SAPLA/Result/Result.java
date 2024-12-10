package org.SAPLA.Result;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.Tile;

public class Result {
    private Tile _tile;
    private int _energyPoint;
    private Direction _lastDirection;

    private Boolean _hasBeenBlocked;

    public Result(Tile tile, int energyPoint, Direction direction, Boolean hasBeenBlocked) {
        this._tile = tile;
        this._energyPoint = energyPoint;
        this._lastDirection = direction;
        this._hasBeenBlocked = hasBeenBlocked;
    }

    public Tile getTile() {
        return this._tile;
    }

    public int getEnergyPoint() {
        return this._energyPoint;
    }

    public Direction getLastDirection() {
        return this._lastDirection;
    }

    public Boolean getHasBeenBlocked() {
        return this._hasBeenBlocked;
    }

    public void setTile(Tile tile) {
        this._tile = tile;
    }

    public void setEnergyPoint(int energyPoint) {
        this._energyPoint = energyPoint;
    }

    public void setLastDirection(Direction direction) { this._lastDirection = direction; }

    public void setHasBeenBlocked(Boolean hasBeenBlocked) { this._hasBeenBlocked = hasBeenBlocked; }
}
