package org.SAPLA.LivingBeing.GoodBeing.Faction2;

import org.SAPLA.Game.Game;
import org.SAPLA.LivingBeing.Master;
import org.SAPLA.Map.Tile;

import java.util.List;

public class MasterFaction2 extends Faction2 implements Master {

    private static MasterFaction2 _masterFaction2;

    private MasterFaction2() {
        super(null, null, 0, null);
    }

    public static MasterFaction2 getInstance() {
        if(_masterFaction2 == null) {
            _masterFaction2 = new MasterFaction2();
        }
        return _masterFaction2;
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
