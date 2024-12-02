package org.SAPLA.Map;

import org.SAPLA.Enum.Direction;
import org.SAPLA.LivingBeing.LivingBeing;

import java.util.Dictionary;

public class Map {

    private Tile[][] _mapGrid;
    private static Tile[][] s_mapGrid;
    private int _mapWidth;
    private int _mapHeight;
    private static int s_mapWidth;
    private static int s_mapHeight;

    private Dictionary<String, SafeZone> _safeZones;

    public Map(int mapWidth, int mapHeight) {
        _mapWidth = mapWidth;
        _mapHeight = mapHeight;
        s_mapWidth = mapWidth;
        s_mapHeight = mapHeight;
        _mapGrid = new Tile[_mapWidth][_mapHeight];
        s_mapGrid = new Tile[_mapWidth][_mapHeight];
        _safeZones = new java.util.Hashtable<>();
    }

    private void initializeMapGrid() {
        for (int i = 0; i < _mapWidth; i++) {
            for (int j = 0; j < _mapHeight; j++) {
                _mapGrid[i][j] = new Tile(' ', false, new Position(i, j));
            }
        }
    }

    public void generateMap() {
        // Initialize the map with default tiles
        initializeMapGrid();

        // Generate and assign safe zones
        int safeZoneWidth = 4;
        int safeZoneHeight = 4;
        assignSafeZone("faction1", 0, 0, safeZoneWidth, safeZoneHeight);                               // Top-left
        assignSafeZone("faction2", 0, _mapHeight - safeZoneHeight, safeZoneWidth, safeZoneHeight);     // Top-right
        assignSafeZone("faction3", _mapWidth - safeZoneWidth, 0, safeZoneWidth, safeZoneHeight);       // Bottom-left
        assignSafeZone("faction4", _mapWidth - safeZoneWidth, _mapHeight - safeZoneHeight, safeZoneWidth, safeZoneHeight); // Bottom-right
        s_mapGrid = _mapGrid;
    }

    private void assignSafeZone(String faction, int startX, int startY, int width, int height) {
        Tile[][] safeZone = generateSafeZones(startX, startY, width, height);

        if (safeZone == null) {
            throw new IllegalStateException("Failed to generate safe zone for " + faction);
        }

        // Save the safe zone in the map
        _safeZones.put(faction, new SafeZone(safeZone));

        // Place the safe zone tiles into the main map grid
        placeSafeZoneOnMap(safeZone, startX, startY);
    }

    private void placeSafeZoneOnMap(Tile[][] safeZone, int startX, int startY) {
        int width = safeZone.length;
        int height = safeZone[0].length;

        for (int i = 0; i < width; i++) {
            System.arraycopy(safeZone[i], 0, _mapGrid[startX + i], startY, height);
        }
    }

    public int tileAvailableAroundAGivenTile(Tile tile) {
        return 0;
    }

    public static Direction directionToReachSafeZone(LivingBeing livingBeing) {
        Tile currentTile = livingBeing.getCurrentTile();
        SafeZone safeZone = livingBeing.getSafeZone();
        Tile[][] safeZoneGrid = safeZone.getSafeZoneGrid();

        // Si l'être vivant est déjà dans la zone de sécurité, retourner null
        if (currentTile.isSafeZone()) {
            return null;
        }

        // Obtenir les coordonnées relatives de la zone de sécurité
        int dx = safeZoneGrid[0][0].getPosition().getX() - currentTile.getPosition().getX();
        int dy = safeZoneGrid[0][0].getPosition().getY() - currentTile.getPosition().getY();

        // Déterminer la direction en fonction de dx et dy
        if (dx == 0 && dy > 0) return Direction.NORTH;
        if (dx == 0 && dy < 0) return Direction.SOUTH;
        if (dy == 0 && dx > 0) return Direction.EAST;
        if (dy == 0 && dx < 0) return Direction.WEST;

        if (dx > 0 && dy > 0) return Direction.NORTHEAST;
        if (dx > 0 && dy < 0) return Direction.SOUTHEAST;
        if (dx < 0 && dy > 0) return Direction.NORTHWEST;
        if (dx < 0 && dy < 0) return Direction.SOUTHWEST;

        // Cas par défaut si aucune direction n'est déterminée
        throw new IllegalStateException("Impossible de déterminer la direction.");
    }

    private Tile[][] generateSafeZones(int startX, int startY, int width, int height) {
        startX = Math.max(0, Math.min(_mapWidth - width, startX));
        startY = Math.max(0, Math.min(_mapHeight - height, startY));

        Tile[][] safeZone = new Tile[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                char tileContent = ' ';
                boolean isSafeZone = true;
                Position position = new Position(startX + i, startY + j);
                safeZone[i][j] = new Tile(tileContent, isSafeZone, position);
            }
        }

        return safeZone;
    }

    public Tile getTileAtPosition(Position position) {
        return _mapGrid[position.getX()][position.getY()];
    }

    public static Tile[][] getMapGrid() {
        return s_mapGrid;
    }

    public void DisplayMap() {
        final String[] SAFE_ZONE_COLORS = {"\033[0;101m", "\033[0;102m", "\033[0;103m", "\033[0;104m"};
        final String RESET_COLOR = "\u001B[0m";
        final String DEFAULT_COLOR = "\033[0;107m";
        final int PADDING = (3 - 1) / 2;
        final String PADDING_SPACES = " ".repeat(PADDING);

        for (int j = 0; j < _mapHeight; j++) {
            for (int i = 0; i < _mapWidth; i++) {
                Tile tile = _mapGrid[i][j];
                char displayChar = tile.getTileContent();
                String color;

                if (tile.isSafeZone()) {
                    int safeZoneIndex = (i < _mapWidth / 2 ? 0 : 2) + (j < _mapHeight / 2 ? 0 : 1);
                    color = SAFE_ZONE_COLORS[safeZoneIndex];
                } else {
                    color = DEFAULT_COLOR;
                }

                System.out.print(color + PADDING_SPACES + displayChar + PADDING_SPACES + RESET_COLOR);
            }
            System.out.println();
        }
    }

    public static int getMapWidth() {
        return s_mapWidth;
    }

    public static int getMapHeight() {
        return s_mapHeight;
    }

}