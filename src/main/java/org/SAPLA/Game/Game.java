package org.SAPLA.Game;

import org.SAPLA.Enum.Direction;
import org.SAPLA.LivingBeing.BadBeing.Faction3.Faction3;
import org.SAPLA.LivingBeing.BadBeing.Faction3.MasterFaction3;
import org.SAPLA.LivingBeing.BadBeing.Faction4.Faction4;
import org.SAPLA.LivingBeing.BadBeing.Faction4.MasterFaction4;
import org.SAPLA.LivingBeing.GoodBeing.Faction1.Faction1;
import org.SAPLA.LivingBeing.GoodBeing.Faction1.MasterFaction1;
import org.SAPLA.LivingBeing.GoodBeing.Faction2.Faction2;
import org.SAPLA.LivingBeing.GoodBeing.Faction2.MasterFaction2;
import org.SAPLA.LivingBeing.IMaster;
import org.SAPLA.LivingBeing.LivingBeing;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Position;
import org.SAPLA.Map.SafeZone;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.CavalerMouv.CavalerMouv;
import org.SAPLA.MouvementType.DiagonalMouv.DiagonalMouv;
import org.SAPLA.MouvementType.KingMouv.KingMouv;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.MouvementType.TowerMouv.TowerMouv;
import org.SAPLA.utils.ConsoleDisplay;
import org.SAPLA.utils.Constants;
import org.SAPLA.utils.RandomProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class Game {
    private static int _instancesCount = 0;
    private static volatile Game _game; // Thread-safe singleton
    private final ScheduledExecutorService executor;
    private final Runnable task;
    private final Map map;
    private final List<LivingBeing> individualsList = new ArrayList<>();
    private final List<LivingBeing> masters = new ArrayList<>();
    private final List<String> allMessages;
    private final ConsoleDisplay consoleDisplay;
    private boolean isRunningAutomatically = false;
    private int stepCount = 0;
    private List<LivingBeing> winners = new ArrayList<>();

    // Singleton thread-safe instance
    public static Game getInstance() {
        if (_game == null) {
            synchronized (Game.class) {
                if (_game == null) {
                    _game = new Game();
                }
            }
        }
        return _game;
    }

    private Game() {
        _instancesCount++;
        this.map = new Map(Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
        RandomProvider randomProvider = RandomProvider.getInstance();
        this.map.generateMap();

        this.allMessages = RandomProvider.generateRandomStrings();

        this.consoleDisplay = new ConsoleDisplay(map, individualsList);

        generateMasters();
        generateFactions();
        consoleDisplay.displaySimulation();
        this.executor = Executors.newScheduledThreadPool(1);
        this.task = this::gameStep; // Step task
        displayIndividuals();
    }

    // Run simulation in manual mode
    public void runManual() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Simulation prête. Appuyez sur [Entrée] pour avancer ou tapez 'quit' pour arrêter.");
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("quit") || !gameStep()) {
                    System.out.println("Simulation terminée.");
                    displayFinalResults();
                    break;
                }
            }
        }
    }

    // Start automatic mode
    public void startAutomatic() {
        if (isRunningAutomatically) {
            System.out.println("La simulation est déjà en mode automatique.");
            return;
        }
        isRunningAutomatically = true;
        System.out.println("Simulation en mode automatique...");
        executor.scheduleAtFixedRate(task, 0, 1000, TimeUnit.MILLISECONDS);
    }

    // Stop automatic mode
    public void stopAutomatic() {
        if (!isRunningAutomatically) {
            System.out.println("La simulation n'est pas en mode automatique.");
            return;
        }
        isRunningAutomatically = false;
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
            System.out.println("Simulation automatique arrêtée.");
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        displayFinalResults();
    }

    // Main game logic for a single step
    public boolean gameStep() {
        System.out.println("================GAME STEP=====================");
        long startTime = System.currentTimeMillis();

        shuffleIndividuals();
        playIndividuals();
        consoleDisplay.displaySimulation();

        System.out.printf("Étape %d terminée en %d ms%n", ++stepCount, System.currentTimeMillis() - startTime);

        if (checkWinners()) {
            if (isRunningAutomatically) stopAutomatic();
            return false;
        }
        return true;
    }

    private void shuffleIndividuals() {
        Collections.shuffle(individualsList, RandomProvider.getInstance());
        System.out.println("Individuals shuffled.");
    }

    private void displayIndividuals() {
        System.out.println("=============================");
        individualsList.forEach(ind -> System.out.println(ind + " : " + ind.getCurrentTile().getPosition()));
    }

    private void playIndividuals() {
        individualsList.forEach(LivingBeing::move);
        System.out.println("Individuals moved.");
    }

    private boolean checkWinners() {
        winners = masters.stream()
                .filter(master -> ((IMaster) master).didIWin(allMessages))
                .toList();
        return !winners.isEmpty();
    }

    private void displayFinalResults() {
        if (winners.isEmpty()) determineWinnerByMessages();
        if (winners.size() == 1) {
            System.out.printf("La faction gagnante est la faction %s.%n", getFactionName(winners.get(0)));
        } else {
            System.out.printf("Les factions gagnantes sont : %s.%n",
                    winners.stream().map(this::getFactionName).distinct().collect(Collectors.joining(", ")));
        }
    }

    private void determineWinnerByMessages() {
        int maxMessages = masters.stream()
                .mapToInt(master -> ((IMaster) master).getNbMessages())
                .max().orElse(0);
        winners = masters.stream()
                .filter(master -> ((IMaster) master).getNbMessages() == maxMessages)
                .toList();
    }

    private String getFactionName(LivingBeing being) {
        return being.getClass().getSuperclass().getSimpleName();
    }

    private void generateMasters() {
        int[][] masterPositions = {
                {0, 0}, {Constants.MAP_WIDTH - 1, 0},
                {0, Constants.MAP_HEIGHT - 1}, {Constants.MAP_WIDTH - 1, Constants.MAP_HEIGHT - 1}
        };

        List<IMaster> masterInstances = List.of(
                MasterFaction1.getInstance(),
                MasterFaction2.getInstance(),
                MasterFaction3.getInstance(),
                MasterFaction4.getInstance()
        );

        for (int i = 0; i < masterInstances.size(); i++) {
            IMaster master = masterInstances.get(i);
            Position pos = new Position(masterPositions[i][0], masterPositions[i][1]);
            master.setFixedTile(map.setTileContentAtPosition(pos, 'M'));
            masters.add((LivingBeing) master);
        }
    }

    private void generateFactions() {
        java.util.Map<Class<? extends LivingBeing>, Class<? extends MouvementType>> factionTable = java.util.Map.of(
                Faction1.class, KingMouv.class,
                Faction2.class, TowerMouv.class,
                Faction3.class, DiagonalMouv.class,
                Faction4.class, CavalerMouv.class
        );

        AtomicInteger messageIndex = new AtomicInteger();
        factionTable.forEach((factionClass, movementClass) -> {
            try {
                // Initialiser le mouvement spécifique à la faction
                MouvementType movementInstance = movementClass.getDeclaredConstructor().newInstance();

                // Récupérer la zone sûre associée à la faction
                SafeZone safeZone = map.getSafeZones().get(factionClass.getSimpleName());

                // Générer les individus de la faction
                generateFactionIndividuals(factionClass, movementInstance, safeZone, messageIndex.get());

                // Augmenter l'index des messages pour la prochaine faction
                messageIndex.addAndGet(Constants.NB_INDIVIDUALS * Constants.NB_MESSAGES_PER_INDIVIDUAL_AT_START);
            } catch (Exception e) {
                System.err.println("Erreur lors de la génération des factions : " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private void generateFactionIndividuals(
            Class<? extends LivingBeing> factionClass,
            MouvementType movementInstance,
            SafeZone safeZone,
            int messageStartIndex
    ) throws Exception {
        for (int i = 0; i < Constants.NB_INDIVIDUALS; i++) {
            // Générer une position aléatoire dans la zone sûre
            Tile safeTile = getRandomSafeTile(safeZone);

            // Créer un nouvel individu de la faction
            LivingBeing individual = factionClass
                    .getDeclaredConstructor(Tile.class, Direction.class, int.class, movementInstance.getClass(), List.class)
                    .newInstance(
                            safeTile,
                            Direction.NORTH,
                            Constants.STARTING_ENERGY,
                            movementInstance,
                            allMessages.subList(messageStartIndex, messageStartIndex + Constants.NB_MESSAGES_PER_INDIVIDUAL_AT_START)
                    );

            // Ajouter l'individu à la liste et marquer sa position sur la carte
            individualsList.add(individual);
            safeTile.setTileContent(factionClass.getSimpleName().charAt(7));
        }
    }
    public static int getInstancesCount() {
        return _instancesCount;
    }

    private Tile getRandomSafeTile(SafeZone safeZone) {
        RandomProvider randomProvider = RandomProvider.getInstance();
        Tile tile;
        int attempts = 0;

        // Retry until an unoccupied tile is found (to prevent infinite loops, limit retries)
        do {
            int x = randomProvider.nextInt(0,4);
            int y = randomProvider.nextInt(0, 4);
            tile = safeZone.getSafeZoneGrid()[x][y];

            attempts++;
            if (attempts > 100) {
                throw new RuntimeException("Failed to find an unoccupied tile after 100 attempts. Check your safe zone size.");
            }
        } while (tile.getTileContent() !=' '); // Ensure the tile is not already occupied

        return tile;
    }

    public IMaster getMaster(LivingBeing livingBeing) {
      return (IMaster) masters.stream()
                .filter(master -> master.getClass().getSuperclass().equals(livingBeing.getClass()))
                .findFirst().orElse(null);
    }

    public void startInteraction(Position position1, Position position2) {
        LivingBeing individu1 = individualsList.stream().filter(individual -> individual.getCurrentTile().getPosition().getX() == position1.getX() && individual.getCurrentTile().getPosition().getY() == position1.getY()).findFirst().orElse(null);
        LivingBeing individu2 = individualsList.stream().filter(individual -> individual.getCurrentTile().getPosition().getX() == position2.getX() && individual.getCurrentTile().getPosition().getY() == position2.getY()).findFirst().orElse(null);
        if(individu1 != null && individu2 != null) {
            individu1.interact(individu2);
        } else {
            throw new IllegalStateException("Failed to find individual to interact with.");
        }
    }
}
