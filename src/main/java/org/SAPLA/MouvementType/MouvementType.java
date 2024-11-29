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

    public abstract Tile moveNorth(Tile currentTile);

    public abstract Tile moveEast(Tile currentTile);

    public abstract Tile moveSouth(Tile currentTile);

    public abstract Tile moveWest(Tile currentTile);

    public abstract Result upMove(Tile currentTile,int energyPoint, int numbMove, Direction nextDirection);

    public abstract Result downMove(Tile currentTile,int energyPoint, int numbMove, Direction nextDirection);

    public abstract Result leftMove(Tile currentTile,int energyPoint, int numbMove, Direction nextDirection);

    public abstract Result rightMove(Tile currentTile,int energyPoint, int numbMove, Direction nextDirection);

    public abstract Result moveToTile(Tile currentTile, int energyPoint, Direction currentDirection, int numbMove);
}
