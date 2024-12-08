package org.SAPLA.LivingBeing.BadBeing.Faction3;

import org.SAPLA.LivingBeing.IMaster;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.DiagonalMouv.DiagonalMouv;

import java.util.List;

public class MasterFaction3 extends Faction3<DiagonalMouv> implements IMaster {

    private static int _instancesCount = 0;
    private static MasterFaction3 _masterFaction3;

    private MasterFaction3() {
        super(null, null, 0, null, null);
        _instancesCount++;
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
    public boolean didIWin(List<String> allMessages) {
        return this.getMessage().containsAll(allMessages);
    }

    @Override
    public int getNbMessages() {
        return this.getMessage().size();
    }

    @Override
    public void move() {}

    public static int getInstancesCount() {
        return _instancesCount;
    }
}
