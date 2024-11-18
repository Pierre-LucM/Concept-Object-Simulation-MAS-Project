package org.SAPLA.LivingBeing;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.SafeZone;
import org.SAPLA.Map.Tile;

import java.util.List;

public abstract class LivingBeing {
    private List<String> _message;
    private Tile _currentTile;
    private Direction _lastDirectionTaken;
    private int _energyPoint;
    private SafeZone _safeZone;

    public abstract void move();
    public abstract void interact();
}
