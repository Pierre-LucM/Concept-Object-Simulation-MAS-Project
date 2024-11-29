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

    public Result cavalerMov(Tile currentTile, int energyPoint) {

        if (energyPoint > (100 * 0.2)) {
            Direction randomDirection = Direction.getRandomDirection();

            if (randomDirection == Direction.NORTH) {

                Tile targetTile = getMapGrid()[currentTile.getPosition().getX() + 2][currentTile.getPosition().getY() + 3];

                if (targetTile.getPosition().getX() > getMapWidth()){
                    targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() > getMapHeight()){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;
                boolean stopMove = false;
                while(true){

                    for (int i = 0; i < 3; i++) {
                        nextTile = moveNorth(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
                    }

                    for (int i = 0; i < 2; i++) {
                        nextTile = moveEast(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
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
                    targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() > getMapHeight()){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;
                boolean stopMove = false;

                while(true){

                    for (int i = 0; i < 3; i++) {
                        nextTile = moveEast(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
                    }

                    for (int i = 0; i < 2; i++) {
                        nextTile = moveNorth(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
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
                    targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() < 0){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;
                boolean stopMove = false;

                while(true){

                    for (int i = 0; i < 3; i++) {
                        nextTile = moveEast(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
                    }

                    for (int i = 0; i < 2; i++) {
                        nextTile = moveSouth(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
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
                    targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() < 0){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;
                boolean stopMove = false;

                while(true){

                    for (int i = 0; i < 3; i++) {
                        nextTile = moveSouth(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
                    }

                    for (int i = 0; i < 2; i++) {
                        nextTile = moveEast(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
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
                    targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() <0){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;
                boolean stopMove = false;

                while(true){

                    for (int i = 0; i < 3; i++) {
                        nextTile = moveSouth(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
                    }

                    for (int i = 0; i < 2; i++) {
                        nextTile = moveWest(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
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
                    targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() < 0){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;
                boolean stopMove = false;

                while(true){

                    for (int i = 0; i < 3; i++) {
                        nextTile = moveWest(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
                    }

                    for (int i = 0; i < 2; i++) {
                        nextTile = moveSouth(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
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
                    targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() > getMapHeight()){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;
                boolean stopMove = false;

                while(true){

                    for (int i = 0; i < 3; i++) {
                        nextTile = moveWest(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
                    }

                    for (int i = 0; i < 2; i++) {
                        nextTile = moveNorth(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
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

                if (targetTile.getPosition().getX() < 0){
                    targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
                }
                if (targetTile.getPosition().getY() > getMapHeight()){
                    targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
                }

                Tile previousTile = currentTile;
                Tile nextTile = currentTile;
                boolean stopMove = false;

                while(true){

                    for (int i = 0; i < 3; i++) {
                        nextTile = moveNorth(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
                    }

                    for (int i = 0; i < 2; i++) {
                        nextTile = moveWest(nextTile);
                        if (nextTile == previousTile) {
                            stopMove = true;
                            break;
                        }else{
                            previousTile = nextTile;
                            energyPoint -= 1;
                        }
                    }

                    if (stopMove == true){
                        break;
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
                targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() > getMapHeight()){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;
            boolean stopMove = false;
            while(true){

                for (int i = 0; i < 3; i++) {
                    nextTile = moveNorth(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
                }

                for (int i = 0; i < 2; i++) {
                    nextTile = moveEast(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
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
                targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() > getMapHeight()){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;
            boolean stopMove = false;

            while(true){

                for (int i = 0; i < 3; i++) {
                    nextTile = moveEast(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
                }

                for (int i = 0; i < 2; i++) {
                    nextTile = moveNorth(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
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
                targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() < 0){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;
            boolean stopMove = false;

            while(true){

                for (int i = 0; i < 3; i++) {
                    nextTile = moveEast(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
                }

                for (int i = 0; i < 2; i++) {
                    nextTile = moveSouth(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
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
                targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() < 0){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;
            boolean stopMove = false;

            while(true){

                for (int i = 0; i < 3; i++) {
                    nextTile = moveSouth(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
                }

                for (int i = 0; i < 2; i++) {
                    nextTile = moveEast(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
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
                targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() <0){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;
            boolean stopMove = false;

            while(true){

                for (int i = 0; i < 3; i++) {
                    nextTile = moveSouth(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
                }

                for (int i = 0; i < 2; i++) {
                    nextTile = moveWest(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
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
                targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() < 0){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][1];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;
            boolean stopMove = false;

            while(true){

                for (int i = 0; i < 3; i++) {
                    nextTile = moveWest(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
                }

                for (int i = 0; i < 2; i++) {
                    nextTile = moveSouth(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
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
                targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() > getMapHeight()){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;
            boolean stopMove = false;

            while(true){

                for (int i = 0; i < 3; i++) {
                    nextTile = moveWest(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
                }

                for (int i = 0; i < 2; i++) {
                    nextTile = moveNorth(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
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

            if (targetTile.getPosition().getX() < 0){
                targetTile = getMapGrid()[1][targetTile.getPosition().getY()];
            }
            if (targetTile.getPosition().getY() > getMapHeight()){
                targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
            }

            Tile previousTile = currentTile;
            Tile nextTile = currentTile;
            boolean stopMove = false;

            while(true){

                for (int i = 0; i < 3; i++) {
                    nextTile = moveNorth(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
                }

                for (int i = 0; i < 2; i++) {
                    nextTile = moveWest(nextTile);
                    if (nextTile == previousTile) {
                        stopMove = true;
                        break;
                    }else{
                        previousTile = nextTile;
                        energyPoint -= 1;
                    }
                }

                if (stopMove == true){
                    break;
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
