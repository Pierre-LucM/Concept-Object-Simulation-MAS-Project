package org.SAPLA.Map;

import org.SAPLA.Enum.Direction;
import org.SAPLA.LivingBeing.LivingBeing;
import org.SAPLA.utils.Constants;
import org.SAPLA.utils.RandomProvider;
public class Map {

    private static int _instancesCount = 0;
    private Tile[][] _mapGrid;
    private static Tile[][] s_mapGrid;
    private int _mapWidth;
    private int _mapHeight;
    private static int s_mapWidth;
    private static int s_mapHeight;

    private static java.util.Map<String, SafeZone> _safeZones;

    public Map(int mapWidth, int mapHeight) {
        _instancesCount++;
        _mapWidth = mapWidth;
        _mapHeight = mapHeight;
        s_mapWidth = mapWidth;
        s_mapHeight = mapHeight;
        _mapGrid = new Tile[_mapWidth][_mapHeight];
        s_mapGrid = new Tile[_mapWidth][_mapHeight];
        _safeZones = new java.util.HashMap<>();
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
        assignSafeZone("Faction1", 0, 0, safeZoneWidth, safeZoneHeight);                               // Top-left
        assignSafeZone("Faction2", 0, _mapHeight - safeZoneHeight, safeZoneWidth, safeZoneHeight);     // Top-right
        assignSafeZone("Faction3", _mapWidth - safeZoneWidth, 0, safeZoneWidth, safeZoneHeight);       // Bottom-left
        assignSafeZone("Faction4", _mapWidth - safeZoneWidth, _mapHeight - safeZoneHeight, safeZoneWidth, safeZoneHeight); // Bottom-right
        this.generateObstacle();
        s_mapGrid = _mapGrid;
    }

    private void generateObstacle(){
        RandomProvider randomProvider = RandomProvider.getInstance();
        for (int i=0; i< Constants.OBSTACLE_COUNT; i++){
            int x = randomProvider.nextInt(_mapWidth);
            int y = randomProvider.nextInt(_mapHeight);
            if (_mapGrid[x][y].getTileContent() == ' ' && !_mapGrid[x][y].isSafeZone()){
                _mapGrid[x][y].setTileContent('X');
            }
        }
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
        int x = tile.getPosition().getX();
        int y = tile.getPosition().getY();

        // Les 8 positions possibles autour de la case (haut, bas, gauche, droite et diagonales)
        int[][] directions = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        int maxAvailableTiles;
        if ((x == 0 && y == 0) || (x == 0 && y == _mapHeight - 1) ||
                (x == _mapWidth - 1 && y == 0) || (x == _mapWidth - 1 && y == _mapHeight - 1)) {
            // Coin : maximum 3 cases disponibles
            maxAvailableTiles = 3;
        } else if (x == 0 || x == _mapWidth - 1 || y == 0 || y == _mapHeight - 1) {
            // Bordure : maximum 5 cases disponibles
            maxAvailableTiles = 5;
        } else {
            // Centre : maximum 8 cases disponibles
            maxAvailableTiles = 8;
        }

        int availableTiles = 0;

        // Vérifie toutes les directions autour de la case
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            // Vérifie si la nouvelle position est dans les limites de la carte
            if (newX >= 0 && newX < _mapWidth && newY >= 0 && newY < _mapHeight) {
                // Vérifie si la case est vide
                if (_mapGrid[newX][newY].getTileContent() == ' ') {
                    availableTiles++;
                }
            }
        }

        return Math.min(availableTiles, maxAvailableTiles);
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
                Position position = new Position(startX + i, startY + j);
                this._mapGrid[position.getX()][position.getY()].setSafeZone(true);
                safeZone[i][j] = this._mapGrid[position.getX()][position.getY()];
            }
        }

        return safeZone;
    }

    public Tile getTileAtPosition(Position position) {
        return _mapGrid[position.getX()][position.getY()];
    }

    public Tile setTileContentAtPosition(Position position, char content) {
        this._mapGrid[position.getX()][position.getY()].setTileContent(content);
        return this._mapGrid[position.getX()][position.getY()];
    }

    public static Tile[][] getMapGrid() {
        return s_mapGrid;
    }



    public static int getMapWidth() {
        return s_mapWidth;
    }

    public static int getMapHeight() {
        return s_mapHeight;
    }

    public static int getInstancesCount() {
        return _instancesCount;
    }
    public static java.util.Map<String, SafeZone> getSafeZones() {
        return _safeZones;
    }

}