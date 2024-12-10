package org.SAPLA.LivingBeing.GoodBeing.Faction1;

import org.SAPLA.LivingBeing.IMaster;
import org.SAPLA.LivingBeing.LivingBeing;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.KingMouv.KingMouv;
import org.SAPLA.utils.ConsoleDisplay;

import java.util.List;

public class MasterFaction1 extends Faction1<KingMouv> implements IMaster {
    private static int _instancesCount = 0;

    private static MasterFaction1 _masterFaction1;

    private MasterFaction1() {
        super(null, null, 0, null, null);
        _instancesCount++;
    }

    public static MasterFaction1 getInstance() {
        if(_masterFaction1 == null) {
            _masterFaction1 = new MasterFaction1();
        }
        return _masterFaction1;
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
