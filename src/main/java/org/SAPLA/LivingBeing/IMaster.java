package org.SAPLA.LivingBeing;

import org.SAPLA.Map.Tile;

import java.util.List;

public interface IMaster {
    void collectMessages(List<String> messages);

    void setFixedTile(Tile tile);

    boolean didIWin(List<String> allMessages);

    int getNbMessages();
}
