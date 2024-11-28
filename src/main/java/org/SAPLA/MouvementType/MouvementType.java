package org.SAPLA.MouvementType;

import org.SAPLA.LivingBeing.LivingBeing;
import org.SAPLA.Map.Tile;

public abstract class MouvementType {

    protected LivingBeing _livingBeing;

    public void setLivingBeing(LivingBeing livingBeing){
        _livingBeing = livingBeing;
    }

    public abstract Tile moveNorth(Tile currentTile);

    public abstract Tile moveEast(Tile currentTile);

    public abstract Tile moveSouth(Tile currentTile);

    public abstract Tile moveWest(Tile currentTile);
}
