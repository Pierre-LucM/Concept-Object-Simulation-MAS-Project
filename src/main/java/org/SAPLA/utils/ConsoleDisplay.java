package org.SAPLA.utils;

import org.SAPLA.LivingBeing.LivingBeing;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Tile;

import java.util.List;

public class ConsoleDisplay {

    private Map map;
    private List<LivingBeing> individuals;

    public ConsoleDisplay(Map map, List<LivingBeing> individuals) {
        this.map = map;
        this.individuals = individuals;
    }

    public void displaySimulation() {
        clearConsole();
        displayMap();
        displayIndividualsInfo();
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void displayMap() {
        final String[] SAFE_ZONE_COLORS = {"\033[0;101m", "\033[0;102m", "\033[0;103m", "\033[0;104m"};
        final String RESET_COLOR = "\u001B[0m";
        final String DEFAULT_COLOR = "\033[0;107m";
        final int PADDING = (3 - 1) / 2;
        final String PADDING_SPACES = " ".repeat(PADDING);

        Tile[][] mapGrid = Map.getMapGrid();
        int mapHeight = Map.getMapHeight();
        int mapWidth = Map.getMapWidth();

        for (int j = 0; j < mapHeight; j++) {
            for (int i = 0; i < mapWidth; i++) {
                Tile tile = mapGrid[i][j];
                char displayChar = tile.getTileContent();
                String color;

                if (tile.isSafeZone()) {
                    int safeZoneIndex = (i < mapWidth / 2 ? 0 : 2) + (j < mapHeight / 2 ? 0 : 1);
                    color = SAFE_ZONE_COLORS[safeZoneIndex];
                } else {
                    color = DEFAULT_COLOR;
                }

                System.out.print(color + PADDING_SPACES + displayChar + PADDING_SPACES + RESET_COLOR);
            }
            System.out.println();
        }
    }

    private void displayIndividualsInfo() {
        System.out.println("Individuals Information:");
        for (LivingBeing individual : individuals) {
            System.out.println(individual.toString());
        }
    }
}
