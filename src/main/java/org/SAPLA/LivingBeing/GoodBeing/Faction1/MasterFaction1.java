package org.SAPLA.LivingBeing.GoodBeing.Faction1;

import org.SAPLA.Game.Game;
import org.SAPLA.LivingBeing.Master;
import org.SAPLA.Map.Tile;

import java.util.List;

public class MasterFaction1 extends Faction1 implements Master {

    private static MasterFaction1 _masterFaction1;

    private MasterFaction1() {
        super(null, null, 0, null);
    }

    public static MasterFaction1 getInstance() {
        if(_masterFaction1 == null) {
            _masterFaction1 = new MasterFaction1();
        }
        return _masterFaction1;
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
