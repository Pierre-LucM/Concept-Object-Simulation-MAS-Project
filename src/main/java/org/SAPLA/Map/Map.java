package org.SAPLA.Map;

import org.SAPLA.Enum.Direction;

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

    public Direction directionToReachSafeZone(Object livingBeing){
        return null;
    }




}
