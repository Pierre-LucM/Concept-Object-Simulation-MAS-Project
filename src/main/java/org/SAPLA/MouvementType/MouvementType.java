package org.SAPLA.MouvementType;

import org.SAPLA.Enum.Direction;
import org.SAPLA.LivingBeing.LivingBeing;
import org.SAPLA.Map.Tile;
import org.SAPLA.Result.Result;

import static org.SAPLA.Map.Map.*;

public abstract class MouvementType {

    protected LivingBeing _livingBeing;

    public void setLivingBeing(LivingBeing livingBeing){
        _livingBeing = livingBeing;
    }

    //Mouvement vers le Nord

    protected Tile moveNorth(Tile currentTile){
        return moveToTile(currentTile, 0, 1);
    }

    //Mouvement vers l'Est

    protected Tile moveEast(Tile currentTile){
        return moveToTile(currentTile, 1, 0);
    }

    //Mouvement vers le Sud

    protected Tile moveSouth(Tile currentTile){
        return moveToTile(currentTile, 0, -1);
    }

    //Mouvement vers l'Ouest

    protected Tile moveWest(Tile currentTile){
        return moveToTile(currentTile, -1, 0);
    }

    //Verifie si le mouvement est possible et effectue le mouvement

    private Tile moveToTile(Tile currentTile, int deltaX, int deltaY){
        int newX = currentTile.getPosition().getX() + deltaX;
        int newY = currentTile.getPosition().getY() + deltaY;

        if (newX < 0) newX = 0;
        if (newX >= getMapWidth()) newX = getMapWidth() - 1;
        if (newY < 0) newY = 0;
        if (newY >= getMapHeight()) newY = getMapHeight() - 1;

        Tile nextTile = getMapGrid()[newX][newY];
        return nextTile.getTileContent() == ' ' ? nextTile : currentTile;
    }

    //Renvoie le Tile final et l'energie restante

    public abstract Result nextTile(Tile currentTile, int energyPoint, Direction targetDirection);

    //Choisie le mouvement a effectuer en fonction de la direction

    public abstract Result moveStep(Tile currentTile, Direction direction);
}
