package org.SAPLA.MouvementType;

import org.SAPLA.Enum.Direction;
import org.SAPLA.LivingBeing.LivingBeing;
import org.SAPLA.Map.Tile;
import org.SAPLA.Result.Result;

public abstract class MouvementType {

    protected LivingBeing _livingBeing;

    public void setLivingBeing(LivingBeing livingBeing){
        _livingBeing = livingBeing;
    }

    public abstract Result moveNorth(Tile currentTile, Tile targetTile ,int energyPoint);

    public abstract Result moveEast(Tile currentTile, Tile targetTile ,int energyPoint);

    public abstract Result moveSouth(Tile currentTile, Tile targetTile ,int energyPoint);

    public abstract Result moveWest(Tile currentTile, Tile targetTile ,int energyPoint);

    public abstract Result moveToTile(Tile currentTile, int energyPoint, Direction currentDirection, int numbMove);
}
