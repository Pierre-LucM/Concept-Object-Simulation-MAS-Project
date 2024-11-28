package org.SAPLA.Result;

import org.SAPLA.Map.Tile;

public class Result {
    private Tile _tile;
    private int energyPoint;

    public Result(Tile tile, int energyPoint) {
        _tile = tile;
        this.energyPoint = energyPoint;
    }

    public Tile getTile() {
        return _tile;
    }

    public int getEnergyPoint() {
        return energyPoint;
    }

    public void setTile(Tile tile) {
        _tile = tile;
    }

    public void setEnergyPoint(int energyPoint) {
        this.energyPoint = energyPoint;
    }
}
