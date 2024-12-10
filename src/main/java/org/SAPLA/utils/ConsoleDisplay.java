package org.SAPLA.utils;

import org.SAPLA.Game.Game;
import org.SAPLA.LivingBeing.BadBeing.BadBeing;
import org.SAPLA.LivingBeing.BadBeing.Faction3.Faction3;
import org.SAPLA.LivingBeing.BadBeing.Faction3.MasterFaction3;
import org.SAPLA.LivingBeing.BadBeing.Faction4.Faction4;
import org.SAPLA.LivingBeing.BadBeing.Faction4.MasterFaction4;
import org.SAPLA.LivingBeing.GoodBeing.Faction1.Faction1;
import org.SAPLA.LivingBeing.GoodBeing.Faction1.MasterFaction1;
import org.SAPLA.LivingBeing.GoodBeing.Faction2.Faction2;
import org.SAPLA.LivingBeing.GoodBeing.Faction2.MasterFaction2;
import org.SAPLA.LivingBeing.GoodBeing.GoodBeing;
import org.SAPLA.LivingBeing.LivingBeing;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.SafeZone;
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
                } else if (displayChar == 'X') {
                    color = "\033[1;90m";
                } else {
                    color = DEFAULT_COLOR;
                }

                System.out.print(color + PADDING_SPACES + displayChar + PADDING_SPACES + RESET_COLOR);
            }
            System.out.println();
        }
    }

    public static void displayMessagesTransfertInfo(LivingBeing individu1, LivingBeing individu2, List<String> individu1NewMessages, List<String> individu2NewMessages) {
        System.out.printf("\n Transfert messages between %s and %s.\n", individu1.getClass().getSimpleName(), individu2.getClass().getSimpleName());
        System.out.println("_________________________________________________");
        System.out.printf("|           | %-15s | %-15s |%n", individu1.getClass().getSimpleName(), individu2.getClass().getSimpleName());
        System.out.println("|-----------|-----------------|-----------------|");
        int index = 0;
        while (index < individu1NewMessages.size() || index < individu2NewMessages.size()) {
            System.out.print("|           | ");
            if(index < individu1NewMessages.size())  {
                System.out.printf("+%-14s | ", individu1NewMessages.get(index));
            } else {
                System.out.print("                | ");
            }
            if(index < individu2NewMessages.size())  {
                System.out.printf("+%-14s |", individu2NewMessages.get(index));
            } else {
                System.out.print("                |");
            }
            System.out.print("\n");
            index++;
        }
        System.out.println("_________________________________________________");
        System.out.println("\n");
    }

    public static void displayFightMessagesTransfert(LivingBeing winner, LivingBeing loser, List<String> messagesVoles) {
        System.out.printf("\n Transfert messages from %s to %s.\n", loser.getClass().getSimpleName(), winner.getClass().getSimpleName());
        System.out.println("_________________________________________________");
        System.out.printf("|           | %-15s | %-15s |%n", loser.getClass().getSimpleName(), winner.getClass().getSimpleName());
        System.out.println("|-----------|-----------------|-----------------|");
        int index = 0;
        while (index < messagesVoles.size()) {
            System.out.printf("|           | -%-14s | +%-14s |%n",messagesVoles.get(index), messagesVoles.get(index));
            index++;
        }
        System.out.println("_________________________________________________");
    }

    public static void displayInstancesCount() {
        System.out.println("\nNombres d'instances de chaque classe :");
        System.out.println(LivingBeing.class.getSimpleName() + " : " + LivingBeing.getInstancesCount() + " instance(s)");
        System.out.println(GoodBeing.class.getSimpleName() + " : " + GoodBeing.getInstancesCount() + " instance(s)");
        System.out.println(BadBeing.class.getSimpleName() + " : " + BadBeing.getInstancesCount() + " instance(s)");
        System.out.println(Faction1.class.getSimpleName() + " : " + Faction1.getInstancesCount() + " instance(s)");
        System.out.println(Faction2.class.getSimpleName() + " : " + Faction2.getInstancesCount() + " instance(s)");
        System.out.println(Faction3.class.getSimpleName() + " : " + Faction3.getInstancesCount() + " instance(s)");
        System.out.println(Faction4.class.getSimpleName() + " : " + Faction4.getInstancesCount() + " instance(s)");
        System.out.println(MasterFaction1.class.getSimpleName() + " : " + MasterFaction1.getInstancesCount() + " instance(s)");
        System.out.println(MasterFaction2.class.getSimpleName() + " : " + MasterFaction2.getInstancesCount() + " instance(s)");
        System.out.println(MasterFaction3.class.getSimpleName() + " : " + MasterFaction3.getInstancesCount() + " instance(s)");
        System.out.println(MasterFaction4.class.getSimpleName() + " : " + MasterFaction4.getInstancesCount() + " instance(s)");
        System.out.println(Game.class.getSimpleName() + " : " + Game.getInstancesCount() + " instance(s)");
        System.out.println(Map.class.getSimpleName() + " : " + Map.getInstancesCount() + " instance(s)");
        System.out.println(SafeZone.class.getSimpleName() + " : " + SafeZone.getInstancesCount() + " instance(s)");
        System.out.println(Tile.class.getSimpleName() + " : " + Tile.getInstancesCount() + " instance(s)");
    }
}
