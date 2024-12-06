package org.SAPLA.LivingBeing.GoodBeing.Faction1;

import org.SAPLA.Enum.Direction;
import org.SAPLA.LivingBeing.GoodBeing.GoodBeing;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.KingMouv.KingMouv;
import org.SAPLA.Result.Result;
import org.SAPLA.utils.Constants;

public class Faction1 <T extends KingMouv> extends GoodBeing {
    private T _mouvementKing ;

    public Faction1(Tile currentTile, Direction lastDirectionTaken, int energyPoint, T mouvementKing) {
        super();
        setCurrentTile(currentTile);
        setEnergyPoint(energyPoint);
        setMaxEnergy(Constants.MAX_ENERGY);
        _mouvementKing = mouvementKing;
        _mouvementKing.setLivingBeing(this);
    }

    @Override
    public void move() {
        if (getEnergyPoint() > 0) {
            Result result = _mouvementKing.kingMov(super.getCurrentTile(), super.getEnergyPoint(), super.getMaxEnergy());
            super.setCurrentTile(result.getTile());
            super.setEnergyPoint(result.getEnergyPoint());
        }
    }

    @Override
    public void interact() {

    }
}
