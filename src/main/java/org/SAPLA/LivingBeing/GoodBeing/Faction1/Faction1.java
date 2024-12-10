package org.SAPLA.LivingBeing.GoodBeing.Faction1;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Game.Game;
import org.SAPLA.LivingBeing.GoodBeing.GoodBeing;
import org.SAPLA.LivingBeing.IMaster;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Position;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.KingMouv.KingMouv;
import org.SAPLA.Result.Result;
import org.SAPLA.utils.Constants;

import java.util.List;

public class Faction1 <T extends KingMouv> extends GoodBeing {
    private static int _instancesCount = 0;

    private T _mouvementKing ;

    public Faction1(Tile currentTile, Direction lastDirectionTaken, int energyPoint, T mouvementKing, List<String> messages) {
        super();
        _instancesCount++;
        setCurrentTile(currentTile);
        setEnergyPoint(energyPoint);
        setMaxEnergy(Constants.MAX_ENERGY);
        _mouvementKing = mouvementKing;
        if (_mouvementKing != null) {
            _mouvementKing.setLivingBeing(this);
        }
        addMessage(messages);
    }

    @Override
    public void move() {
        if (getEnergyPoint() > 0) {
            Result result = _mouvementKing.kingMov(super.getCurrentTile(), super.getEnergyPoint(), super.getMaxEnergy());
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
