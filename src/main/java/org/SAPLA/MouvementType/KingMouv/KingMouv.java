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

        Result resultNextTile = moveStep(currentTile, targetDirection);

        if (resultNextTile.getTile() != currentTile) {
            energyPoint--;
        }
        resultNextTile.setEnergyPoint(energyPoint);

        return resultNextTile;
    }

    @Override
    public Result moveStep(Tile currentTile, Direction direction) {
        Result result;
        Tile bufferTile;
        return switch (direction) {
            case NORTH -> {
                result = new Result(currentTile, 0, Direction.NORTH, false);
                bufferTile = moveNorth(currentTile);
                if (bufferTile != currentTile) {
                    result.setHasBeenBlocked(true);
                }
                yield result;
            }
            case NORTHEAST -> {
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
            case EAST -> {
                result = new Result(currentTile, 0, Direction.EAST, false);
                bufferTile = moveEast(currentTile);
                if (bufferTile != currentTile) {
                    result.setHasBeenBlocked(true);
                }
                yield result;
            }
            case SOUTHEAST -> {
                result = new Result(currentTile, 0, Direction.SOUTH, false);
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
            case SOUTH -> {
                result = new Result(currentTile, 0, Direction.SOUTH, false);
                bufferTile = moveSouth(currentTile);
                if (bufferTile != currentTile) {
                    result.setHasBeenBlocked(true);
                }
                yield result;
            }
            case SOUTHWEST -> {
                result = new Result(currentTile, 0, Direction.SOUTH, false);
                bufferTile = super.moveSouth(currentTile);
                if (bufferTile != currentTile) {
                    result.setTile(bufferTile);
                    result.setLastDirection(Direction.WEST);
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
            case WEST -> {
                result = new Result(currentTile, 0, Direction.WEST, false);
                bufferTile = moveWest(currentTile);
                if (bufferTile != currentTile) {
                    result.setHasBeenBlocked(true);
                }
                yield result;
            }
            case NORTHWEST -> {
                result = new Result(currentTile, 0, Direction.NORTH, false);
                bufferTile = super.moveNorth(currentTile);
                if (bufferTile != currentTile) {
                    result.setTile(bufferTile);
                    result.setLastDirection(Direction.WEST);
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