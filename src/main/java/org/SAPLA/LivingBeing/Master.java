package org.SAPLA.LivingBeing;

import org.SAPLA.Map.Tile;

import java.util.List;

public interface Master {
    public void collectMessages(List<String> messages);

    public void setFixedTile(Tile tile);
}
