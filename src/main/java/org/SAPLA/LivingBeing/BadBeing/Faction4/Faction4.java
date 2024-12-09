package org.SAPLA.LivingBeing.BadBeing.Faction4;
import org.SAPLA.Enum.Direction;
import org.SAPLA.Game.Game;
import org.SAPLA.LivingBeing.BadBeing.BadBeing;
import org.SAPLA.LivingBeing.IMaster;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.SafeZone;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.CavalerMouv.CavalerMouv;
import org.SAPLA.Result.Result;
import org.SAPLA.utils.Constants;

import java.util.List;

public class Faction4 <T extends CavalerMouv> extends BadBeing {

    private static int _instancesCount = 0;

    private T _mouvementCavaler ;

    public Faction4(Tile currentTile, Direction lastDirectionTaken, int energyPoint, T mouvementCavaler, List<String> messages) {
        super();
        _instancesCount++;
        setCurrentTile(currentTile);
        setEnergyPoint(energyPoint);
        setMaxEnergy(Constants.MAX_ENERGY);
        _mouvementCavaler = mouvementCavaler;
        if (_mouvementCavaler != null) {
            _mouvementCavaler.setLivingBeing(this);
        }
        addMessage(messages);
    }

    @Override
    public void move() {
        if (getEnergyPoint() > 0) {
            Result result = _mouvementCavaler.cavalerMov(super.getCurrentTile(), super.getEnergyPoint(), super.getMaxEnergy());
            if(result==null){
                return;
            }

            java.util.Map<String, SafeZone> safeZones = Map.getSafeZones();

            for (String faction : safeZones.keySet()) {
                if (!faction.equals("Faction4")) {
                    Tile[][] safeZoneGrid = safeZones.get(faction).getSafeZoneGrid();

                    for (Tile[] row : safeZoneGrid) {
                        for (Tile tile : row) {
                            if (tile.equals(result.getTile())) {
                                super.setCurrentTile(super.getCurrentTile());
                                super.setEnergyPoint(result.getEnergyPoint());
                                return;
                            }
                        }
                    }
                }
            }

            super.getCurrentTile().setTileContent(' ');
            result.getTile().setTileContent(this.getClass().getSimpleName().charAt(7));
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
