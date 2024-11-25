package org.SAPLA.Map;

import org.SAPLA.Enum.Direction;
import org.SAPLA.LivingBeing.LivingBeing;

import java.util.Dictionary;

public class Map {

    private Tile[][] _mapGrid;
    private int _mapWidth;
    private int _mapHeight;

    private Dictionary<String, SafeZone> _safeZones;

    public Map(int mapWidth, int mapHeight) {
        _mapWidth = mapWidth;
        _mapHeight = mapHeight;
        _mapGrid = new Tile[_mapWidth][_mapHeight];
    }

    public void generateMap(){
    }

    public int tileAvailableAroundAGivenTile(Tile tile){
        return 0;
    }

    public Direction directionToReachSafeZone(LivingBeing livingBeing){

        Tile currentTile = livingBeing.getCurrentTile();
        SafeZone safeZone = livingBeing.getSafeZone();
        Tile[][] safeZoneGrid = safeZone.getSafeZoneGrid();

        // If the living being is already in the safe zone, return null
        if (currentTile.isSafeZone()) {
            return null;
        }

        // get relative position of the safe zone from the current tile
        int x = safeZoneGrid[0][0].getPosition().getX() - currentTile.getPosition().getX();
        int y = safeZoneGrid[0][0].getPosition().getY() - currentTile.getPosition().getY();

        // if the safe zone is in the same row as the current tile
        if (x == 0) {
            if (y > 0) {
                return Direction.NORTH;
            } else {
                return Direction.SOUTH;
            }
        }

        // if the safe zone is in the same column as the current tile
        if (y == 0) {
            if (x > 0) {
                return Direction.EAST;
            } else {
                return Direction.WEST;
            }
        }

        // if the safe zone is in the diagonal of the current tile
        if (x > 0) {
            if (y > 0) {
                return Direction.NORTHEAST;
            } else {
                return Direction.SOUTHEAST;
            }
        } else {
            if (y > 0) {
                return Direction.NORTHWEST;
            } else {
                return Direction.SOUTHWEST;
            }
        }
    }




}
