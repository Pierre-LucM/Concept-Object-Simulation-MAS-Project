package org.SAPLA.LivingBeing;

import org.SAPLA.Map.Tile;

import java.util.List;

public interface IMaster {
    public void collectMessages(List<String> messages);

    public void setFixedTile(Tile tile);
}
