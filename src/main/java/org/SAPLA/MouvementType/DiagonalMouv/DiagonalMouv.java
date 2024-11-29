package org.SAPLA.MouvementType.DiagonalMouv;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.Result.Result;

import java.util.Random;

import static org.SAPLA.Map.Map.*;

public class DiagonalMouv extends MouvementType {

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

    public Result diagMove(Tile currentTile, int energyPoint) {

        if (energyPoint > (100 * 0.2)) {
            Direction randomDirection = Direction.getRandomDirection();

            if (randomDirection == Direction.NORTH && randomDirection == Direction.NORTHEAST) {

                int numberMouv = new Random().nextInt(4);
                numberMouv = numberMouv + 1;
                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + numberMouv][currentTile.getPosition().getY() + numberMouv];

                if (targetTile.getPosition().getX() > getMapWidth()){
                    targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() > getMapHeight()){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;

                while(true){
                    nextTile = moveNorth(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    nextTile = moveEast(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    if (nextTile == targetTile) {
                        break;
                    }
                }
                Result result = new Result(nextTile, energyPoint);
                return result;
            }

            if (randomDirection == Direction.EAST && randomDirection == Direction.SOUTHEAST) {

                int numberMouv = new Random().nextInt(4);
                numberMouv = numberMouv + 1;
                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + numberMouv][currentTile.getPosition().getY() - numberMouv];

                if (targetTile.getPosition().getX() > getMapWidth()){
                    targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() < 0){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;

                while(true){
                    nextTile = moveSouth(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    nextTile = moveEast(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    if (nextTile == targetTile) {
                        break;
                    }
                }
                Result result = new Result(nextTile, energyPoint);
                return result;
            }

            if (randomDirection == Direction.SOUTH && randomDirection == Direction.SOUTHWEST) {

                int numberMouv = new Random().nextInt(4);
                numberMouv = numberMouv + 1;
                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - numberMouv][currentTile.getPosition().getY() - numberMouv];

                if (targetTile.getPosition().getX() < 0){
                    targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() < 0){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;

                while(true){
                    nextTile = moveSouth(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    nextTile = moveWest(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    if (nextTile == targetTile) {
                        break;
                    }
                }
                Result result = new Result(nextTile, energyPoint);
                return result;
            }

            if (randomDirection == Direction.WEST && randomDirection == Direction.NORTHWEST) {

                int numberMouv = new Random().nextInt(4);
                numberMouv = numberMouv + 1;
                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - numberMouv][currentTile.getPosition().getY() + numberMouv];

                if (targetTile.getPosition().getX() < 0){
                    targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() > getMapHeight()){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;

                while(true){
                    nextTile = moveNorth(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    nextTile = moveWest(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    if (nextTile == targetTile) {
                        break;
                    }
                }
                Result result = new Result(nextTile, energyPoint);
                return result;
            }
        }

        Direction directionToSafeZone = Map.directionToReachSafeZone(super._livingBeing);

        if (directionToSafeZone == Direction.NORTH && directionToSafeZone == Direction.NORTHEAST) {

            int numberMouv = new Random().nextInt(4);
            numberMouv = numberMouv + 1;
            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + numberMouv][currentTile.getPosition().getY() + numberMouv];

            if (targetTile.getPosition().getX() > getMapWidth()){
                targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() > getMapHeight()){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;

            while(true){
                nextTile = moveNorth(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                nextTile = moveEast(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                if (nextTile == targetTile) {
                    break;
                }
            }
            Result result = new Result(nextTile, energyPoint);
            return result;
        }

        if (directionToSafeZone == Direction.EAST && directionToSafeZone == Direction.SOUTHEAST) {

            int numberMouv = new Random().nextInt(4);
            numberMouv = numberMouv + 1;
            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + numberMouv][currentTile.getPosition().getY() - numberMouv];

            if (targetTile.getPosition().getX() > getMapWidth()){
                targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() < 0){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;

            while(true){
                nextTile = moveSouth(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                nextTile = moveEast(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                if (nextTile == targetTile) {
                    break;
                }
            }
            Result result = new Result(nextTile, energyPoint);
            return result;
        }

        if (directionToSafeZone == Direction.SOUTH && directionToSafeZone == Direction.SOUTHWEST) {

            int numberMouv = new Random().nextInt(4);
            numberMouv = numberMouv + 1;
            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - numberMouv][currentTile.getPosition().getY() - numberMouv];

            if (targetTile.getPosition().getX() < 0){
                targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() < 0){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;

            while(true){
                nextTile = moveSouth(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                nextTile = moveWest(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                if (nextTile == targetTile) {
                    break;
                }
            }
            Result result = new Result(nextTile, energyPoint);
            return result;
        }

        if (directionToSafeZone == Direction.WEST && directionToSafeZone == Direction.NORTHWEST) {

            int numberMouv = new Random().nextInt(4);
            numberMouv = numberMouv + 1;
            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - numberMouv][currentTile.getPosition().getY() + numberMouv];

            if (targetTile.getPosition().getX() < 0){
                targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() > getMapHeight()){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;

            while(true){
                nextTile = moveNorth(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                nextTile = moveWest(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                if (nextTile == targetTile) {
                    break;
                }
            }
            Result result = new Result(nextTile, energyPoint);
            return result;
        }

        Result result = new Result(currentTile, energyPoint);
        return result;
    }

}
