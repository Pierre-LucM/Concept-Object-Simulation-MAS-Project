package org.SAPLA.LivingBeing.BadBeing.Faction3;

import org.SAPLA.LivingBeing.BadBeing.BadBeing;
import org.SAPLA.Map.Tile;
import org.SAPLA.Enum.Direction;
import org.SAPLA.MouvementType.DiagonalMouv.DiagonalMouv;
import org.SAPLA.Result.Result;

public class Faction3 <T extends DiagonalMouv> extends BadBeing{
    private T _mouvementDiagonal ;

    public Faction3(Tile currentTile, Direction lastDirectionTaken, int energyPoint, T mouvementDiagonal) {
        super();
        _mouvementDiagonal = mouvementDiagonal;
        _mouvementDiagonal.setLivingBeing(this);
    }

    @Override
    public void move() {
        if (getEnergyPoint() > 0) {
            Result result = _mouvementDiagonal.diagMove(super.getCurrentTile(),super.getEnergyPoint());
            super.setCurrentTile(result.getTile());
            super.setEnergyPoint(result.getEnergyPoint());
        }
    }

    @Override
    public void interact() {

    }
}
