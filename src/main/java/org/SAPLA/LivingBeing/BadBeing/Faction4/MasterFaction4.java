package org.SAPLA.LivingBeing.BadBeing.Faction4;

import org.SAPLA.LivingBeing.IMaster;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.CavalerMouv.CavalerMouv;

import java.util.List;

public class MasterFaction4 extends Faction4<CavalerMouv> implements IMaster {

    private static MasterFaction4 _masterFaction4;

    private MasterFaction4() {
        super(null, null, 0, null);
    }

    public static MasterFaction4 getInstance() {
        if(_masterFaction4 == null) {
            _masterFaction4 = new MasterFaction4();
        }
        return _masterFaction4;
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
    public void move() {}
}