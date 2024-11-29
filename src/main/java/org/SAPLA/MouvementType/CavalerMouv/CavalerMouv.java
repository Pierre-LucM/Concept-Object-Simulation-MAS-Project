package org.SAPLA.MouvementType.CavalerMouv;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.Result.Result;

import static org.SAPLA.Map.Map.*;
import static org.SAPLA.Map.Map.getMapHeight;

public class CavalerMouv extends MouvementType {

    @Override
    public Tile moveNorth(Tile currentTile) {
        Tile bufferNextTile = getMapGrid()[currentTile.getPosition().getX()][ currentTile.getPosition().getY() + 1];
        char bufferTileContent = bufferNextTile.getTileContent();

        if (bufferTileContent == ' ') {
            return bufferNextTile;
        }
        return currentTile;
    }

    @Override
    public Tile moveEast(Tile currentTile) {
        Tile bufferNextTile = getMapGrid()[currentTile.getPosition().getX() + 1][ currentTile.getPosition().getY()];
        char bufferTileContent = bufferNextTile.getTileContent();

        if (bufferTileContent == ' ') {
            return bufferNextTile;
        }
        return currentTile;
    }

    @Override
    public Tile moveSouth(Tile currentTile) {
        Tile bufferNextTile = getMapGrid()[currentTile.getPosition().getX()][ currentTile.getPosition().getY() - 1];
        char bufferTileContent = bufferNextTile.getTileContent();

        if (bufferTileContent == ' ') {
            return bufferNextTile;
        }
        return currentTile;
    }

    @Override
    public Tile moveWest(Tile currentTile) {
        Tile bufferNextTile = getMapGrid()[currentTile.getPosition().getX() - 1][ currentTile.getPosition().getY()];
        char bufferTileContent = bufferNextTile.getTileContent();

        if (bufferTileContent == ' ') {
            return bufferNextTile;
        }
        return currentTile;
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

    public Result upMove(Tile currentTile,int energyPoint, int numbMove,  Direction nextDirection) {
        Tile previousTile = currentTile;
        Tile nextTile = currentTile;

        for (int i = 0; i < numbMove; i++) {
            nextTile = moveNorth(nextTile);
            if (nextTile == previousTile) {
                break;
            }else{
                previousTile = nextTile;
                energyPoint -= 1;
            }
        }

         if (nextDirection != null){

             switch (nextDirection){
                 case EAST:
                     return rightMove(nextTile, energyPoint, 2, null);

                 case WEST:
                        return leftMove(nextTile, energyPoint, 2, null);
             }
         }

        return new Result(nextTile, energyPoint);
    }

    public Result downMove(Tile currentTile,int energyPoint, int numbMove, Direction nextDirection) {
        Tile previousTile = currentTile;
        Tile nextTile = currentTile;

        for (int i = 0; i < numbMove; i++) {
            nextTile = moveSouth(nextTile);
            if (nextTile == previousTile) {
                break;
            }else{
                previousTile = nextTile;
                energyPoint -= 1;
            }
        }

        if (nextDirection != null){

            switch (nextDirection){
                case EAST:
                    return rightMove(nextTile, energyPoint, 2, null);

                case WEST:
                    return leftMove(nextTile, energyPoint, 2, null);
            }
        }

        return new Result(nextTile, energyPoint);
    }

    public Result leftMove(Tile currentTile,int energyPoint, int numbMove , Direction nextDirection) {
        Tile previousTile = currentTile;
        Tile nextTile = currentTile;

        for (int i = 0; i < numbMove; i++) {
            nextTile = moveWest(nextTile);
            if (nextTile == previousTile) {
                break;
            }else{
                previousTile = nextTile;
                energyPoint -= 1;
            }
        }

        if (nextDirection != null){

            switch (nextDirection){
                case NORTH:
                    return upMove(nextTile, energyPoint, 2, null);

                case SOUTH:
                    return downMove(nextTile, energyPoint, 2, null);
            }
        }

        return new Result(nextTile, energyPoint);
    }

    public Result rightMove(Tile currentTile,int energyPoint, int numbMove , Direction nextDirection) {
        Tile previousTile = currentTile;
        Tile nextTile = currentTile;

        for (int i = 0; i < numbMove; i++) {
            nextTile = moveEast(nextTile);
            if (nextTile == previousTile) {
                break;
            }else{
                previousTile = nextTile;
                energyPoint -= 1;
            }
        }

        if (nextDirection != null){

            switch (nextDirection){
                case NORTH:
                    return upMove(nextTile, energyPoint, 2, null);

                case SOUTH:
                    return downMove(nextTile, energyPoint, 2, null);
            }
        }

        return new Result(nextTile, energyPoint);
    }

    public Result directionToGo(Tile currentTile, int energyPoint, int maxEnergy, Direction direction) {

        switch (direction) {
            case NORTH:
                return upMove(currentTile, energyPoint, 3, Direction.EAST);
            case NORTHEAST:
                return rightMove(currentTile, energyPoint, 3, Direction.NORTH);
            case EAST:
                return rightMove(currentTile, energyPoint, 3, Direction.SOUTH);
            case SOUTHEAST:
                return downMove(currentTile, energyPoint, 3, Direction.EAST);
            case SOUTH:
                return downMove(currentTile, energyPoint, 3, Direction.WEST);
            case SOUTHWEST:
                return leftMove(currentTile, energyPoint, 3, Direction.SOUTH);
            case WEST:
                return leftMove(currentTile, energyPoint, 3, Direction.NORTH);
            case NORTHWEST:
                return upMove(currentTile, energyPoint, 3, Direction.WEST);
        }

        return new Result(currentTile, energyPoint);
    }
}