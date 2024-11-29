package org.SAPLA.MouvementType.CavalerMouv;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.Result.Result;

import static org.SAPLA.Map.Map.*;

public class CavalerMouv extends MouvementType {

    @Override
    public Result moveNorth(Tile currentTile, Tile targetTile, int energyPoint) {
            if (currentTile.getPosition().getY() == targetTile.getPosition().getY()) {
                return moveToTile(currentTile, energyPoint, Direction.NORTH, 2);
            }
            if(currentTile.getPosition().getX() < targetTile.getPosition().getX()) {
                Result result = moveToTile(currentTile, energyPoint, Direction.NORTH, 3);
                return moveEast(result.getTile(), targetTile, result.getEnergyPoint());
            }
            return new Result(currentTile, energyPoint);
    }

    @Override
    public Result moveEast(Tile currentTile, Tile targetTile, int energyPoint) {
        if (currentTile.getPosition().getX() == targetTile.getPosition().getX()) {
            return moveToTile(currentTile, energyPoint, Direction.EAST, 2);
        }
        if(currentTile.getPosition().getX() < targetTile.getPosition().getX()) {
            Result result = moveToTile(currentTile, energyPoint, Direction.EAST, 3);
            return moveSouth(result.getTile(), targetTile, result.getEnergyPoint());
        }
        return new Result(currentTile, energyPoint);
    }

    @Override
    public Result moveSouth(Tile currentTile, Tile targetTile, int energyPoint) {
        if (currentTile.getPosition().getY() == targetTile.getPosition().getY()) {
            return moveToTile(currentTile, energyPoint, Direction.SOUTH, 2);
        }
        if(currentTile.getPosition().getX() < targetTile.getPosition().getX()) {
            Result result = moveToTile(currentTile, energyPoint, Direction.SOUTH, 3);
            return moveWest(result.getTile(), targetTile, result.getEnergyPoint());
        }
        return new Result(currentTile, energyPoint);
    }

    @Override
    public Result moveWest(Tile currentTile, Tile targetTile, int energyPoint) {
        if (currentTile.getPosition().getX() == 0) {
            return moveToTile(currentTile, energyPoint, Direction.WEST, 2);
        }
        if(currentTile.getPosition().getX() < targetTile.getPosition().getX()) {
            Result result = moveToTile(currentTile, energyPoint, Direction.WEST, 3);
            return moveNorth(result.getTile(), targetTile, result.getEnergyPoint());
        }
        return new Result(currentTile, energyPoint);
    }

    public Result cavalerMov(Tile currentTile, int energyPoint , int maxEnergy) {

        if (energyPoint > (maxEnergy * 0.2)) {
            Direction randomDirection = Direction.getRandomDirection();

            Result result = directionToGo(currentTile, energyPoint, maxEnergy, randomDirection);
            return result;
        }

        Direction directionToSafeZone = Map.directionToReachSafeZone(super._livingBeing);

        Result result = directionToGo(currentTile, energyPoint, maxEnergy, directionToSafeZone);
        return result;
    }

    public Result directionToGo(Tile currentTile, int energyPoint, int maxEnergy, Direction direction) {
        return switch (direction) {
            case NORTH -> moveNorth(currentTile,getMapGrid()[currentTile.getPosition().getX() + 2][currentTile.getPosition().getY() + 3] , energyPoint);
            case NORTHEAST -> moveEast(currentTile, getMapGrid()[currentTile.getPosition().getX() + 3][currentTile.getPosition().getY() + 2], energyPoint);
            case EAST -> moveEast(currentTile, getMapGrid()[currentTile.getPosition().getX() + 3][currentTile.getPosition().getY() -2], energyPoint);
            case SOUTHEAST -> moveSouth(currentTile, getMapGrid()[currentTile.getPosition().getX() + 2][currentTile.getPosition().getY() - 3], energyPoint);
            case SOUTH -> moveSouth(currentTile, getMapGrid()[currentTile.getPosition().getX() - 2][currentTile.getPosition().getY() - 3], energyPoint);
            case SOUTHWEST -> moveWest(currentTile, getMapGrid()[currentTile.getPosition().getX() - 3][currentTile.getPosition().getY() - 2], energyPoint);
            case WEST -> moveWest(currentTile, getMapGrid()[currentTile.getPosition().getX() - 3][currentTile.getPosition().getY() + 2], energyPoint);
            case NORTHWEST -> moveNorth(currentTile, getMapGrid()[currentTile.getPosition().getX() - 2][currentTile.getPosition().getY() + 3], energyPoint);
        };
    }

    @Override
    public Result moveToTile(Tile currentTile, int energyPoint, Direction currentDirection, int numbMove) {
        Tile nextTile = currentTile;

        for (int i = 0; i < numbMove; i++) {
            Tile previousTile = nextTile;
            switch(currentDirection) {
                case NORTH:
                    Tile bufferNextTileUp = getMapGrid()[currentTile.getPosition().getX()][ currentTile.getPosition().getY() + 1];
                    char bufferTileContentUp = bufferNextTileUp.getTileContent();

                    if (bufferTileContentUp == ' ') {
                        nextTile = bufferNextTileUp;
                    }else{
                        nextTile = previousTile;
                    }

                case EAST:
                    Tile bufferNextTileRight = getMapGrid()[currentTile.getPosition().getX()][ currentTile.getPosition().getY() + 1];
                    char bufferTileContentRight = bufferNextTileRight.getTileContent();

                    if (bufferTileContentRight == ' ') {
                        nextTile = bufferNextTileRight;
                    }else{
                        nextTile = previousTile;
                    }

                case SOUTH:
                    Tile bufferNextTileDown = getMapGrid()[currentTile.getPosition().getX()][ currentTile.getPosition().getY() - 1];
                    char bufferTileContentDown = bufferNextTileDown.getTileContent();

                    if (bufferTileContentDown == ' ') {
                        nextTile = bufferNextTileDown;
                    }else{
                        nextTile = previousTile;
                    }

                case WEST:
                    Tile bufferNextTileLeft = getMapGrid()[currentTile.getPosition().getX() -1][ currentTile.getPosition().getY()];
                    char bufferTileContentLeft = bufferNextTileLeft.getTileContent();

                    if (bufferTileContentLeft == ' ') {
                        nextTile = bufferNextTileLeft;
                    }else{
                        nextTile = previousTile;
                    }
            }
            if (nextTile == previousTile) {
                energyPoint = energyPoint -3;
                break;
            }
                energyPoint --;
            }
        return new Result(nextTile, energyPoint);
    }
}