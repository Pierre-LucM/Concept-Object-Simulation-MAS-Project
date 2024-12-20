package org.SAPLA.LivingBeing.GoodBeing.Faction2;

import org.SAPLA.LivingBeing.IMaster;
import org.SAPLA.LivingBeing.LivingBeing;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.TowerMouv.TowerMouv;
import org.SAPLA.utils.ConsoleDisplay;

import java.util.List;

public class MasterFaction2 extends Faction2<TowerMouv> implements IMaster {
    private static int _instancesCount = 0;

    private static MasterFaction2 _masterFaction2;

    private MasterFaction2() {
        super(null, 0, null, null, null);
        _instancesCount++;
    }

    public static MasterFaction2 getInstance() {
        if(_masterFaction2 == null) {
            _masterFaction2 = new MasterFaction2();
        }
        return _masterFaction2;
    }
    @Override
    public void collectMessages(List<String> messages, LivingBeing individu) {
        List<String> messagesCollected = messages.stream().filter(message -> !this.getMessage().contains(message)).toList();
        this.addMessage(messagesCollected);
        ConsoleDisplay.displayMessagesTransfertInfo(this, individu, messagesCollected, List.of());
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
