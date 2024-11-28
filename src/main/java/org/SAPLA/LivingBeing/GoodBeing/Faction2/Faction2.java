package org.SAPLA.LivingBeing.GoodBeing.Faction2;

import org.SAPLA.Enum.Direction;
import org.SAPLA.LivingBeing.GoodBeing.GoodBeing;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.CavalerMouv.CavalerMouv;
import org.SAPLA.MouvementType.TowerMouv.TowerMouv;
import org.SAPLA.Result.Result;

public class Faction2 <T extends TowerMouv> extends GoodBeing {

    private T _mouvementTower ;

    public Faction2(Tile currentTile, Direction lastDirectionTaken, int energyPoint, T mouvementTower) {
        super();
        _mouvementTower = mouvementTower;
        _mouvementTower.setLivingBeing(this);
    }

    @Override
    public void move() {
        if (getEnergyPoint() > 0){
            Result result = _mouvementTower.towerMov(getCurrentTile(), getEnergyPoint());
            setCurrentTile(result.getTile());
            setEnergyPoint(result.getEnergyPoint());
        }
    }

    @Override
    public void interact() {

    }
}
