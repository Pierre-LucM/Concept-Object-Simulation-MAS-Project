package org.SAPLA.MouvementType.MasterMouv;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.Result.Result;

public class MasterMouv extends MouvementType {

    @Override
    public Result nextTile(Tile currentTile, int energyPoint, Direction targetDirection) {
        return null;
    }

    @Override
    public Tile moveStep(Tile currentTile, Direction direction) {
        return null;
    }
}
