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
    private int _maxEnergy;

    public abstract void move();
    public abstract void interact();

    //Getter
    protected List<String> getMessage(){
        return this._message;
    }

    public Tile getCurrentTile(){
        return this._currentTile;
    }

    protected Direction getLastDirectionTaken(){
        return this._lastDirectionTaken;
    }

    protected int getEnergyPoint(){
        return this._energyPoint;
    }

    public SafeZone getSafeZone(){
        return this._safeZone;
    }

    protected int getMaxEnergy(){
        return this._maxEnergy;
    }

    //Setter
    protected void setMessage(List<String> message){
        this._message = message;
    }

    protected void setCurrentTile(Tile currentTile){
        this._currentTile = currentTile;
    }

    protected void setLastDirectionTaken(Direction lastDirectionTaken){
        this._lastDirectionTaken = lastDirectionTaken;
    }

    protected void setEnergyPoint(int energyPoint){
        this._energyPoint = energyPoint;
    }

    protected void setSafeZone(SafeZone safeZone){
        this._safeZone = safeZone;
    }

    protected void setMaxEnergy(int maxEnergy){
        this._maxEnergy = maxEnergy;
    }
}
