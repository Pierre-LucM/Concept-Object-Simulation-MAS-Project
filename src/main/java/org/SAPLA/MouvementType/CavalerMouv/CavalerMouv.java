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
                nextTile = moveStepbyStep(currentTile, Direction.NORTH, 3);
                if (getIsStop()) {
                    yield nextTile;
                }
                yield moveStepbyStep(nextTile, Direction.EAST,2);
            case NORTHEAST:
                nextTile = moveStepbyStep(currentTile, Direction.EAST, 3);
                if (getIsStop()) {
                    yield nextTile;
                }
                yield moveStepbyStep(nextTile, Direction.NORTH,2);
            case EAST:
                nextTile = moveStepbyStep(currentTile, Direction.EAST, 3);
                if (getIsStop()) {
                    yield nextTile;
                }
                yield moveStepbyStep(nextTile, Direction.SOUTH,2);
            case SOUTHEAST:
                nextTile = moveStepbyStep(currentTile, Direction.SOUTH, 3);
                if (getIsStop()) {
                    yield nextTile;
                }
                yield moveStepbyStep(nextTile, Direction.EAST,2);
            case SOUTH :
                nextTile = moveStepbyStep(currentTile, Direction.SOUTH, 3);
                if (getIsStop()) {
                    yield nextTile;
                }
                yield moveStepbyStep(nextTile, Direction.WEST,2);
            case SOUTHWEST :
                nextTile = moveStepbyStep(currentTile, Direction.WEST, 3);
                if (getIsStop()) {
                    yield nextTile;
                }
                yield moveStepbyStep(nextTile, Direction.SOUTH,2);
            case WEST :
                nextTile = moveStepbyStep(currentTile, Direction.WEST, 3);
                if (getIsStop()) {
                    yield nextTile;
                }
                yield moveStepbyStep(nextTile, Direction.NORTH,2);
            case NORTHWEST :
                nextTile = moveStepbyStep(currentTile, Direction.NORTH, 3);
                if (getIsStop()) {
                    yield nextTile;
                }
                yield moveStepbyStep(nextTile, Direction.WEST,2);
        };
    }

    public Tile moveStepbyStep(Tile currentTile, Direction direction, int stepNumber) {
        Tile nextTile = currentTile, previousTile = currentTile;
        for (int i = 0; i < stepNumber; i++) {
            switch (direction) {
                case NORTH -> nextTile = moveNorth(nextTile);
                case EAST -> nextTile = moveEast(nextTile);
                case SOUTH -> nextTile = moveSouth(nextTile);
                case WEST -> nextTile = moveWest(nextTile);
            }
            if (nextTile == previousTile) {
                setIsStop(true);
                break;
            }
            previousTile = nextTile;
        }
        return nextTile;
    }

    @Override
    public Result nextTile(Tile currentTile, int energyPoint, Direction targetDirection) {
        if (energyPoint < 5) { // Pas assez d'énergie pour un mouvement complet
            return new Result(currentTile, energyPoint, null, false);
        }

        Tile nextTile = moveStep(currentTile, targetDirection);

        return new Result(nextTile, energyPoint, targetDirection, nextTile == currentTile);
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