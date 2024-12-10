package org.SAPLA.MouvementType.KingMouv;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.Result.Result;

public class KingMouv extends MouvementType {

    @Override
    public Result nextTile(Tile currentTile, int energyPoint, Direction targetDirection) {
        if (energyPoint < 5) { // Pas assez d'énergie pour un mouvement complet
            return new Result(currentTile, energyPoint, null, false);
        }

        Tile nextTile = moveStep(currentTile, targetDirection);

        if (nextTile != currentTile) {
            energyPoint--;
        }

        return new Result(nextTile, energyPoint, targetDirection, nextTile == currentTile);
    }

    @Override
    public Tile moveStep(Tile currentTile, Direction direction) {
        Tile bufferTile;
        return switch (direction) {
            case NORTH -> moveNorth(currentTile);
            case NORTHEAST -> {
                bufferTile = moveNorth(currentTile);
                if (bufferTile != currentTile) {
                    yield moveEast(bufferTile);
                }
                yield currentTile;
            }
            case EAST -> moveEast(currentTile);
            case SOUTHEAST -> {
                bufferTile = moveSouth(currentTile);
                if (bufferTile != currentTile) {
                    yield moveEast(bufferTile);
                }
                yield currentTile;
            }
            case SOUTH -> moveSouth(currentTile);
            case SOUTHWEST -> {
                bufferTile = moveSouth(currentTile);
                if (bufferTile != currentTile) {
                    yield moveWest(bufferTile);
                }
                yield currentTile;
            }
            case WEST -> moveWest(currentTile);
            case NORTHWEST -> {
                bufferTile = moveNorth(currentTile);
                if (bufferTile != currentTile) {
                    yield moveWest(bufferTile);
                }
                yield currentTile;
            }
        };
    }

    public Result kingMov(Tile currentTile, int energyPoint, int maxEnergy) {
        // Si l'énergie est inférieure à 20 %, se diriger vers la zone sécurisée
        if (energyPoint <= (maxEnergy * 0.2)) {
            Direction safeZoneDirection = Map.directionToReachSafeZone(super._livingBeing);
            return nextTile(currentTile, energyPoint, safeZoneDirection);
        }

        // Sinon, choisir une direction aléatoire
        Direction randomDirection = Direction.getRandomDirection();
        return nextTile(currentTile, energyPoint, randomDirection);
    }
}