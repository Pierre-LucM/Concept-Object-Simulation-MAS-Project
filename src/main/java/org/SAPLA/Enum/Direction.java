package org.SAPLA.Enum;

import org.SAPLA.utils.RandomProvider;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST,
    NORTHEAST,
    SOUTHEAST,
    SOUTHWEST,
    NORTHWEST;

    public static Direction getRandomDirection() {

        int index = RandomProvider.getInstance().nextInt(Direction.values().length);
        return Direction.values()[index];
    }
}
