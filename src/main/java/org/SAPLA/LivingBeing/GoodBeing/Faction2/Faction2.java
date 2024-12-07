package org.SAPLA.LivingBeing.GoodBeing.Faction2;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Game.Game;
import org.SAPLA.LivingBeing.GoodBeing.GoodBeing;
import org.SAPLA.LivingBeing.Master;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.TowerMouv.TowerMouv;
import org.SAPLA.Result.Result;
import org.SAPLA.utils.Constants;

public class Faction2 <T extends TowerMouv> extends GoodBeing {

    private T _mouvementTower ;

    public Faction2(Tile currentTile, Direction lastDirectionTaken, int energyPoint, T mouvementTower) {
        super();
        setCurrentTile(currentTile);
        setEnergyPoint(energyPoint);
        setMaxEnergy(Constants.MAX_ENERGY);
        _mouvementTower = mouvementTower;
        if (_mouvementTower != null) {
            _mouvementTower.setLivingBeing(this);
        }
    }

    @Override
    public void move(Game game) {
        if (getEnergyPoint() > 0){
            Result result = _mouvementTower.towerMov(super.getCurrentTile(), super.getEnergyPoint(), super.getMaxEnergy());
            super.setCurrentTile(result.getTile());
            super.setEnergyPoint(result.getEnergyPoint());
            // Si l'individu est dans la safe zone, alors on envoie tous nos messages au master
            if(this.getCurrentTile().isSafeZone()) {
                Master master = game.getMaster(this);
                master.collectMessages(this.getMessage());
            }
        }
    }
}
