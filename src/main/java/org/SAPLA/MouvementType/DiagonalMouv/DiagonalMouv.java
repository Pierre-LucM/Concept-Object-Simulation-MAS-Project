package org.SAPLA.MouvementType.DiagonalMouv;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.Result.Result;
import org.SAPLA.utils.RandomProvider;

public class DiagonalMouv extends MouvementType {


    public Result diagMove(Tile currentTile, int energyPoint, int maxEnergy) {
        // Si l'énergie est inférieure à 20 %, se diriger vers la zone sécurisée
        if (energyPoint <= (maxEnergy * 0.2)) {
            Direction safeZoneDirection = Map.directionToReachSafeZone(super._livingBeing);
            return nextTile(currentTile, energyPoint, safeZoneDirection);
        }

        // Sinon, choisir une direction aléatoire
        Direction randomDirection = Direction.getRandomDirection();
        return nextTile(currentTile, energyPoint, randomDirection);
    }

    @Override
    public Result nextTile(Tile currentTile, int energyPoint, Direction targetDirection) {
        if (energyPoint < 5) { // Pas assez d'énergie pour un mouvement complet
            return new Result(currentTile, energyPoint, null, false);
        }

        int numberMouv = RandomProvider.getInstance().nextInt(4);
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

        return new Result(nextTile, energyPoint, targetDirection, nextTile == currentTile);
    }

    @Override
    public Tile moveStep(Tile currentTile, Direction direction) {
        Tile bufferTile;
        return switch (direction) {
            case NORTH, NORTHEAST -> {
                bufferTile = super.moveNorth(currentTile);
                if (bufferTile != currentTile) {
                    yield moveEast(bufferTile);
                }
                yield currentTile;
            }
            case EAST,SOUTHEAST -> {
                bufferTile = super.moveSouth(currentTile);
                if (bufferTile != currentTile) {
                    yield moveEast(bufferTile);
                }
                yield currentTile;
            }
            case SOUTH, SOUTHWEST -> {
                bufferTile = super.moveSouth(currentTile);
                if (bufferTile != currentTile) {
                    yield moveWest(bufferTile);
                }
                yield currentTile;
            }
            case WEST,NORTHWEST -> {
                bufferTile = super.moveNorth(currentTile);
                if (bufferTile != currentTile) {
                    yield moveWest(bufferTile);
                }
                yield currentTile;
            }
        };
    }

}
