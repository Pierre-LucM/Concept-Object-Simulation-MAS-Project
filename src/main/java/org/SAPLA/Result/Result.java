package org.SAPLA.Result;

import org.SAPLA.Map.Tile;

public class Result {
    private Tile _tile;
    private int _energyPoint;

    public Result(Tile tile, int energyPoint) {
        this._tile = tile;
        this._energyPoint = energyPoint;
    }

    public Tile getTile() {
        return this._tile;
    }

    public int getEnergyPoint() {
        return this._energyPoint;
    }

    public void setTile(Tile tile) {
        this._tile = tile;
    }

    public void setEnergyPoint(int energyPoint) {
        this._energyPoint = energyPoint;
    }
}
