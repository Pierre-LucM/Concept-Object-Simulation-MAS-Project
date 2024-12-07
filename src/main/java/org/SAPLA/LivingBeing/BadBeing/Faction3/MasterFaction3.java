package org.SAPLA.LivingBeing.BadBeing.Faction3;

import org.SAPLA.Game.Game;
import org.SAPLA.LivingBeing.Master;
import org.SAPLA.Map.Tile;

import java.util.List;

public class MasterFaction3 extends Faction3 implements Master {

    private static MasterFaction3 _masterFaction3;

    private MasterFaction3() {
        super(null, null, 0, null);
    }

    public static MasterFaction3 getInstance() {
        if(_masterFaction3 == null) {
            _masterFaction3 = new MasterFaction3();
        }
        return _masterFaction3;
    }
    @Override
    public void collectMessages(List<String> messages) {
        this.addMessage(messages.stream().filter(message -> !this.getMessage().contains(message)).toList());
    }

    @Override
    public void setFixedTile(Tile fixedTile){
        this.setCurrentTile(fixedTile);
    }

    @Override
    public void move(Game game) {}
}
