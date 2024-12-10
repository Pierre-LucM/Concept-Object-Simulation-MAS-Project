package org.SAPLA.LivingBeing.BadBeing.Faction4;

import org.SAPLA.LivingBeing.IMaster;
import org.SAPLA.LivingBeing.LivingBeing;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.CavalerMouv.CavalerMouv;
import org.SAPLA.utils.ConsoleDisplay;

import java.util.List;

public class MasterFaction4 extends Faction4<CavalerMouv> implements IMaster {

    private static int _instancesCount = 0;

    private static MasterFaction4 _masterFaction4;

    private MasterFaction4() {
        super(null, null, 0, null, null);
        _instancesCount++;
    }

    public static MasterFaction4 getInstance() {
        if(_masterFaction4 == null) {
            _masterFaction4 = new MasterFaction4();
        }
        return _masterFaction4;
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
