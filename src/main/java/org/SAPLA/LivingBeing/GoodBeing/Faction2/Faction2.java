package org.SAPLA.LivingBeing.GoodBeing.Faction2;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Game.Game;
import org.SAPLA.LivingBeing.GoodBeing.GoodBeing;
import org.SAPLA.LivingBeing.IMaster;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Position;
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
            if(result==null){
                return;
            }
            super.getCurrentTile().setTileContent(' ');
            result.getTile().setTileContent(this.getClass().getSimpleName().charAt(7));
            super.setCurrentTile(result.getTile());
            super.setEnergyPoint(result.getEnergyPoint());
            // Si l'individu a été bloqué on vérifie si c'est un autre individu et dans ce cas il y a interaction
            if(result.getHasBeenBlocked()) {
                int x = super.getCurrentTile().getPosition().getX();
                int y = super.getCurrentTile().getPosition().getY();
                switch (result.getLastDirection()) {
                    case NORTH, NORTHWEST -> y--;
                    case SOUTH, SOUTHEAST -> y++;
                    case EAST, NORTHEAST -> x++;
                    case WEST, SOUTHWEST -> x--;
                }
                if(x < Map.getMapWidth() && x >= 0 && y < Map.getMapHeight() && y >= 0) {
                    if("1234".indexOf(Map.getMapGrid()[x][y].getTileContent()) != -1) {
                        Game.getInstance().startInteraction(super.getCurrentTile().getPosition(), new Position(x,y));
                    }
                }
            }
            // Si l'individu est dans la safe zone, alors on envoie tous nos messages au master
            if(this.getCurrentTile().isSafeZone()) {
                IMaster IMaster = Game.getInstance().getMaster(this);
                IMaster.collectMessages(this.getMessage(), this);
            }
        }
    }

    public static int getInstancesCount() {
        return _instancesCount;
    }
}
