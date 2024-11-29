package org.SAPLA.MouvementType.CavalerMouv;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.Result.Result;

import static org.SAPLA.Map.Map.directionToReachSafeZone;

public class CavalerMouv extends MouvementType {

    private boolean _isStop = false;

    public void setIsStop(boolean stop) {
        _isStop = stop;
    }

    public boolean getIsStop() {
        return _isStop;
    }

    @Override
    public Tile moveStep (Tile currentTile, Direction direction) {
        Tile nextTile;
        return switch (direction) {
            case NORTH :
                nextTile = move3Step(currentTile, Direction.NORTH);
                if (getIsStop()) {
                    yield move2Step(nextTile, Direction.EAST);
                }
                yield nextTile;
            case NORTHEAST:
                nextTile = move3Step(currentTile, Direction.EAST);
                if (getIsStop()) {
                    yield nextTile;
                }
                yield move2Step(nextTile, Direction.NORTH);
            case EAST:
                nextTile = move3Step(currentTile, Direction.EAST);
                if (getIsStop()) {
                    yield move2Step(nextTile, Direction.SOUTH);
                }
                yield nextTile;
            case SOUTHEAST:
                nextTile = move3Step(currentTile, Direction.SOUTH);
                if (getIsStop()) {
                    yield nextTile;
                }
                yield move2Step(nextTile, Direction.EAST);
            case SOUTH :
                nextTile = move3Step(currentTile, Direction.SOUTH);
                if (getIsStop()) {
                    yield move2Step(nextTile, Direction.WEST);
                }
                yield nextTile;
            case SOUTHWEST :
                nextTile = move3Step(currentTile, Direction.WEST);
                if (getIsStop()) {
                    yield nextTile;
                }
                yield move2Step(nextTile, Direction.SOUTH);
            case WEST :
                nextTile = move3Step(currentTile, Direction.WEST);
                if (getIsStop()) {
                    yield move2Step(nextTile, Direction.NORTH);
                }
                yield nextTile;
            case NORTHWEST :
                nextTile = move3Step(currentTile, Direction.NORTH);
                if (getIsStop()) {
                    yield nextTile;
                }
                yield move2Step(nextTile, Direction.WEST);
        };
    }

    public Tile move3Step(Tile currentTile, Direction direction) {
        Tile buffer = currentTile, previousTile = currentTile;
        for (int i = 0; i < 3; i++) {
            switch (direction) {
                case NORTH -> buffer = moveNorth(buffer);
                case EAST -> buffer = moveEast(buffer);
                case SOUTH -> buffer = moveSouth(buffer);
                case WEST -> buffer = moveWest(buffer);
            }
            if (buffer == previousTile) {
                setIsStop(true);
                break;
            }
            previousTile = buffer;
        }
        return buffer;
    }

    public Tile move2Step(Tile currentTile, Direction direction) {
        Tile buffer = currentTile, previousTile = currentTile;
        for (int i = 0; i < 2; i++) {
            switch (direction) {
                case NORTH -> buffer = moveNorth(buffer);
                case EAST -> buffer = moveEast(buffer);
                case SOUTH -> buffer = moveSouth(buffer);
                case WEST -> buffer = moveWest(buffer);
            }
            if (buffer == previousTile) {
                setIsStop(true);
                break;
            }
            previousTile = buffer;
        }
        return buffer;
    }

    @Override
    public Result nextTile(Tile currentTile, int energyPoint, Direction targetDirection) {
        if (energyPoint < 5) { // Pas assez d'énergie pour un mouvement complet
            return new Result(currentTile, energyPoint);
        }

        Tile nextTile = moveStep(currentTile, targetDirection);

        return new Result(nextTile, energyPoint);
    }

    public Result cavalerMov(Tile currentTile, int energyPoint, int maxEnergy) {
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