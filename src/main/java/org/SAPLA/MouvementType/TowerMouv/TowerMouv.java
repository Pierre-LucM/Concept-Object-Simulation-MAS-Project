package org.SAPLA.MouvementType.TowerMouv;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.Result.Result;
import org.SAPLA.utils.RandomProvider;

import static org.SAPLA.Map.Map.directionToReachSafeZone;

public class TowerMouv extends MouvementType {
    @Override
    public Result moveStep (Tile currentTile,Direction direction) {
        Result result = new Result(currentTile, 0, Direction.NORTH, false);
        Tile nextTile = null;
        switch (direction) {
            case NORTH, NORTHWEST -> {
                direction = Direction.NORTH;
                nextTile = moveNorth(currentTile);
            }
            case EAST, NORTHEAST -> {
                direction = Direction.EAST;
                nextTile = moveEast(currentTile);
            }
            case SOUTH, SOUTHEAST -> {
                direction = Direction.SOUTH;
                nextTile = moveSouth(currentTile);
            }
            case WEST, SOUTHWEST -> {
                direction = Direction.WEST;
                nextTile = moveWest(currentTile);
            }
        }
        if(nextTile == currentTile) {
            result.setHasBeenBlocked(true);
        }
        result.setLastDirection(direction);
        return result;
    }

    @Override
    public Result nextTile(Tile currentTile, int energyPoint, Direction targetDirection) {
        if (energyPoint < 5) { // Pas assez d'énergie pour un mouvement complet
            return new Result(currentTile, energyPoint, null, false);
        }

        int numberMouv = RandomProvider.getInstance().nextInt(4);
        numberMouv = numberMouv + 1;
        Tile nextTile, previousTile = currentTile;
        Result resultNextTile = null;

        for (int i = 0; i < numberMouv; i++) {
            resultNextTile = moveStep(previousTile, targetDirection);
            nextTile = resultNextTile.getTile();
            if (nextTile == previousTile) {
                break;
            }
            energyPoint--;
            previousTile = nextTile;
        }

        resultNextTile.setEnergyPoint(energyPoint);
        return resultNextTile;
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