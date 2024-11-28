package org.SAPLA.LivingBeing.BadBeing.Faction4;
import org.SAPLA.Enum.Direction;
import org.SAPLA.LivingBeing.BadBeing.BadBeing;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.CavalerMouv.CavalerMouv;
import org.SAPLA.MouvementType.DiagonalMouv.DiagonalMouv;
import org.SAPLA.Result.Result;

import java.util.List;

public class Faction4 <T extends CavalerMouv> extends BadBeing {

    private T _mouvementCavaler ;

    public Faction4(Tile currentTile, Direction lastDirectionTaken, int energyPoint, T mouvementCavaler) {
        super();
        _mouvementCavaler = mouvementCavaler;
        _mouvementCavaler.setLivingBeing(this);
    }

    @Override
    public void move() {
        if (getEnergyPoint() > 0) {
            Result result = _mouvementCavaler.cavalerMov(getCurrentTile(), getEnergyPoint());
            setCurrentTile(result.getTile());
            setEnergyPoint(result.getEnergyPoint());
        }
    }

    @Override
    public void interact() {

    }
}
