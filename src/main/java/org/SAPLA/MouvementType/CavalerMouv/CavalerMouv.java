package org.SAPLA.MouvementType.CavalerMouv;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.Result.Result;

import static org.SAPLA.Map.Map.*;
import static org.SAPLA.Map.Map.getMapHeight;

public class CavalerMouv extends MouvementType {

    private boolean isStopMove = false;

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

            if (randomDirection == Direction.NORTH) {

                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 2][currentTile.getPosition().getY() + 3];

                if (targetTile.getPosition().getX() > getMapWidth()){
                    targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() > getMapHeight()){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
                }

                Result result = new Result(currentTile, energyPoint);

                while(true){

                    result =upMove(currentTile, energyPoint, 3);

                    if (getStopMove()){break;}

                    result = rightMove(currentTile, energyPoint, 2);

                    if (getStopMove()){break;}

                    if (result.getTile() == targetTile) {break;}
                }
                return result;
            }

            if (randomDirection == Direction.NORTHEAST) {

                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 3][currentTile.getPosition().getY() + 2];

                if (targetTile.getPosition().getX() > getMapWidth()){
                    targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() > getMapHeight()){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
                }

                Result result = new Result(currentTile, energyPoint);

                while(true){

                    result = rightMove(currentTile, energyPoint, 3);

                    if (getStopMove()){break;}

                    result = upMove(currentTile, energyPoint, 2);

                    if (getStopMove()){break;}

                    if (result.getTile() == targetTile) {break;}
                }
                return result;
            }

            if (randomDirection == Direction.EAST) {
                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 3][currentTile.getPosition().getY() - 2];

                if (targetTile.getPosition().getX() > getMapWidth()){
                    targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() < 0){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
                }

                Result result = new Result(currentTile, energyPoint);

                while(true){

                    result = rightMove(currentTile, energyPoint, 3);

                    if (getStopMove()){break;}

                    result = downMove(currentTile, energyPoint, 2);

                    if (getStopMove()){break;}

                    if (result.getTile() == targetTile) {break;}
                }
                return result;
            }

            if (randomDirection == Direction.SOUTHEAST) {
                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 2][currentTile.getPosition().getY() - 3];

                if (targetTile.getPosition().getX() > getMapWidth()){
                    targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() < 0){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
                }

                Result result = new Result(currentTile, energyPoint);

                while(true){

                    result = downMove(currentTile, energyPoint, 3);

                    if (getStopMove()){break;}

                    result = rightMove(currentTile, energyPoint, 2);

                    if (getStopMove()){break;}

                    if (result.getTile() == targetTile) {break;}
                }
                return result;
            }

            if (randomDirection == Direction.SOUTH) {
                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 2][currentTile.getPosition().getY() - 3];

                if (targetTile.getPosition().getX() < 0){
                    targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() <0){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
                }

                Result result = new Result(currentTile, energyPoint);

                while(true){

                    result = downMove(currentTile, energyPoint, 3);

                    if (getStopMove()){break;}

                    result = leftMove(currentTile, energyPoint, 2);

                    if (getStopMove()){ break;}

                    if (result.getTile() == targetTile) {break;}
                }
                return result;
            }

            if (randomDirection == Direction.SOUTHWEST) {
                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 3][currentTile.getPosition().getY() - 2];

                if (targetTile.getPosition().getX() < 0){
                    targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() < 0){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
                }

                Result result = new Result(currentTile, energyPoint);

                while(true){

                    result = leftMove(currentTile, energyPoint, 3);

                    if (getStopMove()){break;}

                    result = downMove(currentTile, energyPoint, 2);

                    if (getStopMove()){break;}

                    if (result.getTile() == targetTile) {break;}
                }
                return result;
            }

            if (randomDirection == Direction.WEST) {
                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 3][currentTile.getPosition().getY() + 2];

                if (targetTile.getPosition().getX() < 0){
                    targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() > getMapHeight()){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
                }

                Result result = new Result(currentTile, energyPoint);

                while(true){

                    result = leftMove(currentTile, energyPoint, 3);

                    if (getStopMove()){break;}

                    result = upMove(currentTile, energyPoint, 2);

                    if (getStopMove()){break;}

                    if (result.getTile() == targetTile) {
                        break;
                    }
                }
                return result;
            }

            if (randomDirection == Direction.NORTHWEST) {

                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 2][currentTile.getPosition().getY() + 3];

                if (targetTile.getPosition().getX() < 0){
                    targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() > getMapHeight()){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
                }

                Result result = new Result(currentTile, energyPoint);

                while(true){

                    result = upMove(currentTile, energyPoint, 3);

                    if (getStopMove()){break;}

                    result = leftMove(currentTile, energyPoint, 2);

                    if (getStopMove()){break;}

                    if (result.getTile() == targetTile) {
                        break;
                    }
                }
                return result;
            }
        }

        Direction directionToSafeZone = Map.directionToReachSafeZone(super._livingBeing);

        if (directionToSafeZone == Direction.NORTH) {

            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 2][currentTile.getPosition().getY() + 3];

            if (targetTile.getPosition().getX() > getMapWidth()){
                targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() > getMapHeight()){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
            }

            Result result = new Result(currentTile, energyPoint);

            while(true){

                result =upMove(currentTile, energyPoint, 3);

                if (getStopMove()){break;}

                result = rightMove(currentTile, energyPoint, 2);

                if (getStopMove()){break;}

                if (result.getTile() == targetTile) {break;}
            }
            return result;
        }

        if (directionToSafeZone == Direction.NORTHEAST) {

            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 3][currentTile.getPosition().getY() + 2];

            if (targetTile.getPosition().getX() > getMapWidth()){
                targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() > getMapHeight()){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
            }

            Result result = new Result(currentTile, energyPoint);

            while(true){

                result = rightMove(currentTile, energyPoint, 3);

                if (getStopMove()){break;}

                result = upMove(currentTile, energyPoint, 2);

                if (getStopMove()){break;}

                if (result.getTile() == targetTile) {break;}
            }
            return result;
        }

        if (directionToSafeZone == Direction.EAST) {
            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 3][currentTile.getPosition().getY() - 2];

            if (targetTile.getPosition().getX() > getMapWidth()){
                targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() < 0){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
            }

            Result result = new Result(currentTile, energyPoint);

            while(true){

                result = rightMove(currentTile, energyPoint, 3);

                if (getStopMove()){break;}

                result = downMove(currentTile, energyPoint, 2);

                if (getStopMove()){break;}

                if (result.getTile() == targetTile) {break;}
            }
            return result;
        }

        if (directionToSafeZone == Direction.SOUTHEAST) {
            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 2][currentTile.getPosition().getY() - 3];

            if (targetTile.getPosition().getX() > getMapWidth()){
                targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() < 0){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
            }

            Result result = new Result(currentTile, energyPoint);

            while(true){

                result = downMove(currentTile, energyPoint, 3);

                if (getStopMove()){break;}

                result = rightMove(currentTile, energyPoint, 2);

                if (getStopMove()){break;}

                if (result.getTile() == targetTile) {break;}
            }
            return result;
        }

        if (directionToSafeZone == Direction.SOUTH) {
            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 2][currentTile.getPosition().getY() - 3];

            if (targetTile.getPosition().getX() < 0){
                targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() <0){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
            }

            Result result = new Result(currentTile, energyPoint);

            while(true){

                result = downMove(currentTile, energyPoint, 3);

                if (getStopMove()){break;}

                result = leftMove(currentTile, energyPoint, 2);

                if (getStopMove()){ break;}

                if (result.getTile() == targetTile) {break;}
            }
            return result;
        }

        if (directionToSafeZone == Direction.SOUTHWEST) {
            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 3][currentTile.getPosition().getY() - 2];

            if (targetTile.getPosition().getX() < 0){
                targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() < 0){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
            }

            Result result = new Result(currentTile, energyPoint);

            while(true){

                result = leftMove(currentTile, energyPoint, 3);

                if (getStopMove()){break;}

                result = downMove(currentTile, energyPoint, 2);

                if (getStopMove()){break;}

                if (result.getTile() == targetTile) {break;}
            }
            return result;
        }

        if (directionToSafeZone == Direction.WEST) {
            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 3][currentTile.getPosition().getY() + 2];

            if (targetTile.getPosition().getX() < 0){
                targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() > getMapHeight()){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
            }

            Result result = new Result(currentTile, energyPoint);

            while(true){

                result = leftMove(currentTile, energyPoint, 3);

                if (getStopMove()){break;}

                result = upMove(currentTile, energyPoint, 2);

                if (getStopMove()){break;}

                if (result.getTile() == targetTile) {
                    break;
                }
            }
            return result;
        }

        if (directionToSafeZone == Direction.NORTHWEST) {

            Tile targetTile = getMapGrid()[currentTile.getPosition().getX() - 2][currentTile.getPosition().getY() + 3];

            if (targetTile.getPosition().getX() < 0){
                targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() > getMapHeight()){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
            }

            Result result = new Result(currentTile, energyPoint);

            while(true){

                result = upMove(currentTile, energyPoint, 3);

                if (getStopMove()){break;}

                result = leftMove(currentTile, energyPoint, 2);

                if (getStopMove()){break;}

                if (result.getTile() == targetTile) {
                    break;
                }
            }
            return result;
        }

        Result result = new Result(currentTile, energyPoint);
        return result;
    }

    public Result upMove(Tile currentTile,int energyPoint, int numbMove) {
        Tile previousTile = currentTile;
        Tile nextTile = currentTile;

        for (int i = 0; i < numbMove; i++) {
            nextTile = moveNorth(nextTile);
            if (nextTile == previousTile) {
                setStopMove(true);
                break;
            }else{
                previousTile = nextTile;
                energyPoint -= 1;
            }
        }
        Result result = new Result(nextTile, energyPoint);
        return result;

    }

    public Result downMove(Tile currentTile,int energyPoint, int numbMove) {
        Tile previousTile = currentTile;
        Tile nextTile = currentTile;

        for (int i = 0; i < numbMove; i++) {
            nextTile = moveSouth(nextTile);
            if (nextTile == previousTile) {
                setStopMove(true);
                break;
            }else{
                previousTile = nextTile;
                energyPoint -= 1;
            }
        }
        Result result = new Result(nextTile, energyPoint);
        return result;

    }

    public Result leftMove(Tile currentTile,int energyPoint, int numbMove) {
        Tile previousTile = currentTile;
        Tile nextTile = currentTile;

        for (int i = 0; i < numbMove; i++) {
            nextTile = moveWest(nextTile);
            if (nextTile == previousTile) {
                setStopMove(true);
                break;
            }else{
                previousTile = nextTile;
                energyPoint -= 1;
            }
        }
        Result result = new Result(nextTile, energyPoint);
        return result;

    }

    public Result rightMove(Tile currentTile,int energyPoint, int numbMove) {
        Tile previousTile = currentTile;
        Tile nextTile = currentTile;

        for (int i = 0; i < numbMove; i++) {
            nextTile = moveEast(nextTile);
            if (nextTile == previousTile) {
                setStopMove(true);
                break;
            }else{
                previousTile = nextTile;
                energyPoint -= 1;
            }
        }
        Result result = new Result(nextTile, energyPoint);
        return result;

    }

    public void setStopMove(boolean stopMove) {
        this.isStopMove = stopMove;
    }

    public boolean getStopMove() {
        return this.isStopMove;
    }
}