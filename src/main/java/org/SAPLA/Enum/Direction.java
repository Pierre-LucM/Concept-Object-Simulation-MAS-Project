package org.SAPLA.Enum;

import java.util.Random;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST,
    NORTHEAST,
    SOUTHEAST,
    SOUTHWEST,
    NORTHWEST;

    public Direction getRandomDirection() {
        Random random = new Random();
        int index = random.nextInt(Direction.values().length);
        return Direction.values()[index];
    }
}
