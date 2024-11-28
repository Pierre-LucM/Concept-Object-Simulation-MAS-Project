package org.SAPLA.MouvementType.CavalerMouv;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.Result.Result;

import java.util.Random;

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

    public Result cavalerMov(Tile currentTile, int energyPoint) {

        if (energyPoint > 20) {
            Direction randomDirection = Direction.getRandomDirection();

            if (randomDirection == Direction.NORTH) {

                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 2][currentTile.getPosition().getY() + 3];

                if (targetTile.getPosition().getX() > getMapWidth()){
                    int maxTargetX = targetTile.getPosition().getX() - getMapWidth();
                    targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
                }
                if (targetTile.getPosition().getY() > getMapHeight()){
                    int maxTargetY = targetTile.getPosition().getY() - getMapHeight();
                    targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
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

                    nextTile = moveNorth(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

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

            if (randomDirection == Direction.NORTHEAST) {

                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 3][currentTile.getPosition().getY() + 2];

                if (targetTile.getPosition().getX() > getMapWidth()){
                    int maxTargetX = targetTile.getPosition().getX() - getMapWidth();
                    targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
                }
                if (targetTile.getPosition().getY() > getMapHeight()){
                    int maxTargetY = targetTile.getPosition().getY() - getMapHeight();
                    targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;

                while(true){
                    nextTile = moveEast(nextTile);

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

                    nextTile = moveEast(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    nextTile = moveNorth(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    nextTile = moveNorth(nextTile);

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

            if (randomDirection == Direction.EAST) {
                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 3][currentTile.getPosition().getY() - 2];

                if (targetTile.getPosition().getX() > getMapWidth()){
                    int maxTargetX = targetTile.getPosition().getX() - getMapWidth();
                    targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
                }
                if (targetTile.getPosition().getY() < 0){
                    int maxTargetY = targetTile.getPosition().getY() + getMapHeight();
                    targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;

                while(true){
                    nextTile = moveEast(nextTile);

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

                    nextTile = moveEast(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    nextTile = moveSouth(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    nextTile = moveSouth(nextTile);

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

            if (randomDirection == Direction.SOUTHEAST) {
                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 2][currentTile.getPosition().getY() - 3];

                if (targetTile.getPosition().getX() > getMapWidth()){
                    int maxTargetX = targetTile.getPosition().getX() - getMapWidth();
                    targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
                }
                if (targetTile.getPosition().getY() < 0){
                    int maxTargetY = targetTile.getPosition().getY() + getMapHeight();
                    targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
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

                    nextTile = moveSouth(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

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

            if (randomDirection == Direction.SOUTH) {
                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 2][currentTile.getPosition().getY() - 3];

                if (targetTile.getPosition().getX() < 0){
                    int maxTargetX = targetTile.getPosition().getX() + getMapWidth();
                    targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
                }
                if (targetTile.getPosition().getY() < 0){
                    int maxTargetY = targetTile.getPosition().getY() + getMapHeight();
                    targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
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

                    nextTile = moveSouth(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

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

            if (randomDirection == Direction.SOUTHWEST) {
                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 3][currentTile.getPosition().getY() - 2];

                if (targetTile.getPosition().getX() < 0){
                    int maxTargetX = targetTile.getPosition().getX() + getMapWidth();
                    targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
                }
                if (targetTile.getPosition().getY() < 0){
                    int maxTargetY = targetTile.getPosition().getY() + getMapHeight();
                    targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;

                while(true){
                    nextTile = moveEast(nextTile);

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

                    nextTile = moveEast(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    nextTile = moveSouth(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    nextTile = moveSouth(nextTile);

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

            if (randomDirection == Direction.WEST) {
                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 3][currentTile.getPosition().getY() + 2];

                if (targetTile.getPosition().getX() < 0){
                    int maxTargetX = targetTile.getPosition().getX() + getMapWidth();
                    targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
                }
                if (targetTile.getPosition().getY() > getMapHeight()){
                    int maxTargetY = targetTile.getPosition().getY() - getMapHeight();
                    targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;

                while(true){

                    nextTile = moveWest(nextTile);

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

                    nextTile = moveWest(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    nextTile = moveNorth(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

                    nextTile = moveNorth(nextTile);

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

            if (randomDirection == Direction.NORTHWEST) {

                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 2][currentTile.getPosition().getY() + 3];

                if (targetTile.getPosition().getX() < getMapWidth()){
                    int maxTargetX = targetTile.getPosition().getX() + getMapWidth();
                    targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
                }
                if (targetTile.getPosition().getY() > getMapHeight()){
                    int maxTargetY = targetTile.getPosition().getY() - getMapHeight();
                    targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
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

                    nextTile = moveNorth(nextTile);

                    if (nextTile == previousTile) {
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }

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

        if (directionToSafeZone == Direction.NORTH) {

            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 2][currentTile.getPosition().getY() + 3];

            if (targetTile.getPosition().getX() > getMapWidth()){
                int maxTargetX = targetTile.getPosition().getX() - getMapWidth();
                targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
            }
            if (targetTile.getPosition().getY() > getMapHeight()){
                int maxTargetY = targetTile.getPosition().getY() - getMapHeight();
                targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
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

                nextTile = moveNorth(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

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

        if (directionToSafeZone == Direction.NORTHEAST) {

            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 3][currentTile.getPosition().getY() + 2];

            if (targetTile.getPosition().getX() > getMapWidth()){
                int maxTargetX = targetTile.getPosition().getX() - getMapWidth();
                targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
            }
            if (targetTile.getPosition().getY() > getMapHeight()){
                int maxTargetY = targetTile.getPosition().getY() - getMapHeight();
                targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;

            while(true){
                nextTile = moveEast(nextTile);

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

                nextTile = moveEast(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                nextTile = moveNorth(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                nextTile = moveNorth(nextTile);

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

        if (directionToSafeZone == Direction.EAST) {
            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 3][currentTile.getPosition().getY() - 2];

            if (targetTile.getPosition().getX() > getMapWidth()){
                int maxTargetX = targetTile.getPosition().getX() - getMapWidth();
                targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
            }
            if (targetTile.getPosition().getY() < 0){
                int maxTargetY = targetTile.getPosition().getY() + getMapHeight();
                targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;

            while(true){
                nextTile = moveEast(nextTile);

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

                nextTile = moveEast(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                nextTile = moveSouth(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                nextTile = moveSouth(nextTile);

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

        if (directionToSafeZone == Direction.SOUTHEAST) {
            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 2][currentTile.getPosition().getY() - 3];

            if (targetTile.getPosition().getX() > getMapWidth()){
                int maxTargetX = targetTile.getPosition().getX() - getMapWidth();
                targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
            }
            if (targetTile.getPosition().getY() < 0){
                int maxTargetY = targetTile.getPosition().getY() + getMapHeight();
                targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
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

                nextTile = moveSouth(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

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

        if (directionToSafeZone == Direction.SOUTH) {
            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 2][currentTile.getPosition().getY() - 3];

            if (targetTile.getPosition().getX() < 0){
                int maxTargetX = targetTile.getPosition().getX() + getMapWidth();
                targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
            }
            if (targetTile.getPosition().getY() < 0){
                int maxTargetY = targetTile.getPosition().getY() + getMapHeight();
                targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
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

                nextTile = moveSouth(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

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

        if (directionToSafeZone == Direction.SOUTHWEST) {
            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 3][currentTile.getPosition().getY() - 2];

            if (targetTile.getPosition().getX() < 0){
                int maxTargetX = targetTile.getPosition().getX() + getMapWidth();
                targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
            }
            if (targetTile.getPosition().getY() < 0){
                int maxTargetY = targetTile.getPosition().getY() + getMapHeight();
                targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;

            while(true){
                nextTile = moveEast(nextTile);

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

                nextTile = moveEast(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                nextTile = moveSouth(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                nextTile = moveSouth(nextTile);

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

        if (directionToSafeZone == Direction.WEST) {
            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 3][currentTile.getPosition().getY() + 2];

            if (targetTile.getPosition().getX() < 0){
                int maxTargetX = targetTile.getPosition().getX() + getMapWidth();
                targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
            }
            if (targetTile.getPosition().getY() > getMapHeight()){
                int maxTargetY = targetTile.getPosition().getY() - getMapHeight();
                targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;

            while(true){

                nextTile = moveWest(nextTile);

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

                nextTile = moveWest(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                nextTile = moveNorth(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

                nextTile = moveNorth(nextTile);

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

        if (directionToSafeZone == Direction.NORTHWEST) {

            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 2][currentTile.getPosition().getY() + 3];

            if (targetTile.getPosition().getX() < getMapWidth()){
                int maxTargetX = targetTile.getPosition().getX() + getMapWidth();
                targetTile = getMapGrid()[maxTargetX][targetTile.getPosition().getX()];
            }
            if (targetTile.getPosition().getY() > getMapHeight()){
                int maxTargetY = targetTile.getPosition().getY() - getMapHeight();
                targetTile = getMapGrid()[targetTile.getPosition().getY()][maxTargetY];
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

                nextTile = moveNorth(nextTile);

                if (nextTile == previousTile) {
                    break;
                }else{
                    previousTile = nextTile;
                    energyPoint -= 1;
                }

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
