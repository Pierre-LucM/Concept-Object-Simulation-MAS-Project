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

    @Override
    public Result moveStep(Tile currentTile, Direction direction) {
        Result result;
        Tile bufferTile;
        return switch (direction) {
            case NORTH, NORTHEAST -> {
                result = new Result(currentTile, 0, Direction.NORTH, false);
                bufferTile = super.moveNorth(currentTile);
                if (bufferTile != currentTile) {
                    result.setTile(bufferTile);
                    result.setLastDirection(Direction.EAST);
                    bufferTile = moveEast(result.getTile());
                    if(result.getTile() != bufferTile) {
                        result.setTile(bufferTile);
                        yield result;
                    }
                    result.setHasBeenBlocked(true);
                    yield result;
                }
                result.setHasBeenBlocked(true);
                yield result;
            }
            case EAST,SOUTHEAST -> {
                result = new Result(currentTile, 0, Direction.NORTH, false);
                bufferTile = super.moveSouth(currentTile);
                if (bufferTile != currentTile) {
                    result.setTile(bufferTile);
                    result.setLastDirection(Direction.EAST);
                    bufferTile = moveEast(result.getTile());
                    if(result.getTile() != bufferTile) {
                        result.setTile(bufferTile);
                        yield result;
                    }
                    result.setHasBeenBlocked(true);
                    yield result;
                }
                result.setHasBeenBlocked(true);
                yield result;
            }
            case SOUTH, SOUTHWEST -> {
                result = new Result(currentTile, 0, Direction.NORTH, false);
                bufferTile = super.moveSouth(currentTile);
                if (bufferTile != currentTile) {
                    result.setTile(bufferTile);
                    result.setLastDirection(Direction.EAST);
                    bufferTile = moveWest(result.getTile());
                    if(result.getTile() != bufferTile) {
                        result.setTile(bufferTile);
                        yield result;
                    }
                    result.setHasBeenBlocked(true);
                    yield result;
                }
                result.setHasBeenBlocked(true);
                yield result;
            }
            case WEST,NORTHWEST -> {
                result = new Result(currentTile, 0, Direction.NORTH, false);
                bufferTile = super.moveNorth(currentTile);
                if (bufferTile != currentTile) {
                    result.setTile(bufferTile);
                    result.setLastDirection(Direction.EAST);
                    bufferTile = moveWest(result.getTile());
                    if(result.getTile() != bufferTile) {
                        result.setTile(bufferTile);
                        yield result;
                    }
                    result.setHasBeenBlocked(true);
                    yield result;
                }
                result.setHasBeenBlocked(true);
                yield result;
            }
        };
    }

}
