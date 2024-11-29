package org.SAPLA.MouvementType.CavalerMouv;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.Result.Result;

import static org.SAPLA.Map.Map.*;

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

    public Result upMove(Tile currentTile,int energyPoint, int numbMove, Direction nextDirection) {

         Result result = moveTile(currentTile, energyPoint, Direction.NORTH, numbMove);

         if (nextDirection != null){
             switch (nextDirection){
                 case EAST:
                     return rightMove(result.getTile(), result.getEnergyPoint(), 2, null);

                 case WEST:
                        return leftMove(result.getTile(), result.getEnergyPoint(), 2, null);
             }
         }
        return new Result(result.getTile(), result.getEnergyPoint());
    }

    public Result downMove(Tile currentTile,int energyPoint, int numbMove, Direction nextDirection) {

        Result result = moveTile(currentTile, energyPoint, Direction.SOUTH, numbMove);

        if (nextDirection != null){

            switch (nextDirection){
                case EAST:
                    return rightMove(result.getTile(), result.getEnergyPoint(), 2, null);

                case WEST:
                    return leftMove(result.getTile(), result.getEnergyPoint(), 2, null);
            }
        }
        return new Result(result.getTile(), result.getEnergyPoint());
    }

    public Result leftMove(Tile currentTile,int energyPoint, int numbMove , Direction nextDirection) {

        Result result = moveTile(currentTile, energyPoint, Direction.WEST, numbMove);

        if (nextDirection != null){

            switch (nextDirection){
                case NORTH:
                    return upMove(result.getTile(), result.getEnergyPoint(), 2, null);

                case SOUTH:
                    return downMove(result.getTile(), result.getEnergyPoint(), 2, null);
            }
        }

        return new Result(result.getTile(), result.getEnergyPoint());
    }

    public Result rightMove(Tile currentTile,int energyPoint, int numbMove , Direction nextDirection) {

        Result result = moveTile(currentTile, energyPoint, Direction.EAST, numbMove);

        if (nextDirection != null){

            switch (nextDirection){
                case NORTH:
                    return upMove(result.getTile(), result.getEnergyPoint(), 2, null);

                case SOUTH:
                    return downMove(result.getTile(), result.getEnergyPoint(), 2, null);
            }
        }

        return new Result(result.getTile(), result.getEnergyPoint());
    }

    public Result directionToGo(Tile currentTile, int energyPoint, int maxEnergy, Direction direction) {

        return switch (direction) {
            case NORTH -> upMove(currentTile, energyPoint, 3, Direction.EAST);
            case NORTHEAST -> rightMove(currentTile, energyPoint, 3, Direction.NORTH);
            case EAST -> rightMove(currentTile, energyPoint, 3, Direction.SOUTH);
            case SOUTHEAST -> downMove(currentTile, energyPoint, 3, Direction.EAST);
            case SOUTH -> downMove(currentTile, energyPoint, 3, Direction.WEST);
            case SOUTHWEST -> leftMove(currentTile, energyPoint, 3, Direction.SOUTH);
            case WEST -> leftMove(currentTile, energyPoint, 3, Direction.NORTH);
            case NORTHWEST -> upMove(currentTile, energyPoint, 3, Direction.WEST);
        };
    }

    public Result moveTile(Tile currentTile, int energyPoint, Direction currentDirection, int numbMove) {
        Tile previousTile = currentTile;
        Tile nextTile = currentTile;

        for (int i = 0; i < numbMove; i++) {
            switch(currentDirection) {
                case NORTH:
                    nextTile = moveNorth(nextTile);
                case EAST:
                    nextTile = moveEast(nextTile);
                case SOUTH:
                    nextTile = moveSouth(nextTile);
                case WEST:
                    nextTile = moveWest(nextTile);
            }
            if (nextTile == previousTile) {
                break;
            }else{
                previousTile = nextTile;
                energyPoint -= 1;
            }
        }

        return new Result(nextTile, energyPoint);
    }
}