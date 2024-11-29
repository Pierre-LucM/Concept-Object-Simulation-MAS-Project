package org.SAPLA.MouvementType.TowerMouv;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.Result.Result;

import java.util.Random;

import static org.SAPLA.Map.Map.*;

public class TowerMouv extends MouvementType {
    @Override
    public Tile moveStep (Tile currentTile,Direction direction) {
        return switch (direction) {
            case NORTH, NORTHWEST -> moveNorth(currentTile);
            case EAST, NORTHEAST -> moveEast(currentTile);
            case SOUTH, SOUTHEAST -> moveSouth(currentTile);
            case WEST, SOUTHWEST -> moveWest(currentTile);
        };
    }

    @Override
    public Result nextTile(Tile currentTile, int energyPoint, Direction targetDirection) {
        if (energyPoint < 5) { // Pas assez d'énergie pour un mouvement complet
            return new Result(currentTile, energyPoint);
        }

        int numberMouv = new Random().nextInt(4);
        numberMouv = numberMouv + 1;
        Tile nextTile = currentTile, previousTile = currentTile;

        for (int i = 0; i < numberMouv; i++) {
            nextTile = moveStep(previousTile, targetDirection);
            if (nextTile == previousTile) {
                break;
            }
            energyPoint--;
            previousTile = nextTile;
        }

        return new Result(nextTile, energyPoint);
    }

    public Result towerMov(Tile currentTile, int energyPoint, int maxEnergy) {
        // Si l'énergie est inférieure à 20 %, se diriger vers la zone sécurisée
        if (energyPoint <= (maxEnergy * 0.2)) {
            Direction safeZoneDirection = directionToReachSafeZone(super._livingBeing);
            return nextTile(currentTile, energyPoint, safeZoneDirection);
        }

        // Sinon, choisir une direction aléatoire
        Direction randomDirection = Direction.getRandomDirection();
        return nextTile(currentTile, energyPoint, randomDirection);
    }
}