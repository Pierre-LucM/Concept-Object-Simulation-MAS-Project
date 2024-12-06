package org.SAPLA.LivingBeing.BadBeing.Faction4;
import org.SAPLA.Enum.Direction;
import org.SAPLA.LivingBeing.BadBeing.BadBeing;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.CavalerMouv.CavalerMouv;
import org.SAPLA.Result.Result;
import org.SAPLA.utils.Constants;

public class Faction4 <T extends CavalerMouv> extends BadBeing {

    private T _mouvementCavaler ;

    public Faction4(Tile currentTile, Direction lastDirectionTaken, int energyPoint, T mouvementCavaler) {
        super();
        setCurrentTile(currentTile);
        setEnergyPoint(energyPoint);
        setMaxEnergy(Constants.MAX_ENERGY);
        _mouvementCavaler = mouvementCavaler;
        _mouvementCavaler.setLivingBeing(this);
    }

    @Override
    public void move() {
        if (getEnergyPoint() > 0) {
            Result result = _mouvementCavaler.cavalerMov(super.getCurrentTile(), super.getEnergyPoint(), super.getMaxEnergy());
            super.setCurrentTile(result.getTile());
            super.setEnergyPoint(result.getEnergyPoint());
        }
    }
}
