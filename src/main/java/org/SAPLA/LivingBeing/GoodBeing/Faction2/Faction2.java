package org.SAPLA.LivingBeing.GoodBeing.Faction2;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Game.Game;
import org.SAPLA.LivingBeing.GoodBeing.GoodBeing;
import org.SAPLA.LivingBeing.IMaster;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.TowerMouv.TowerMouv;
import org.SAPLA.Result.Result;
import org.SAPLA.utils.Constants;

import java.util.List;

public class Faction2 <T extends TowerMouv> extends GoodBeing {
    private static int _instancesCount = 0;

    private T _mouvementTower ;

    public Faction2(Tile currentTile, Direction lastDirectionTaken, int energyPoint, T mouvementTower, List<String> messages) {
        super();
        _instancesCount++;
        setCurrentTile(currentTile);
        setEnergyPoint(energyPoint);
        setMaxEnergy(Constants.MAX_ENERGY);
        _mouvementTower = mouvementTower;
        if (_mouvementTower != null) {
            _mouvementTower.setLivingBeing(this);
        }
        addMessage(messages);
    }

    @Override
    public void move() {
        if (getEnergyPoint() > 0){
            Result result = _mouvementTower.towerMov(super.getCurrentTile(), super.getEnergyPoint(), super.getMaxEnergy());
            super.setCurrentTile(result.getTile());
            super.setEnergyPoint(result.getEnergyPoint());
            // Si l'individu est dans la safe zone, alors on envoie tous nos messages au master
            if(this.getCurrentTile().isSafeZone()) {
                IMaster IMaster = Game.getInstance().getMaster(this);
                IMaster.collectMessages(this.getMessage());
            }
        }
    }

    public static int getInstancesCount() {
        return _instancesCount;
    }
}
