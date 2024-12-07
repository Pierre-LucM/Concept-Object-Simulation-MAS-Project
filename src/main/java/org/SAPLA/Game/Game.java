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
import org.SAPLA.LivingBeing.LivingBeing;
import org.SAPLA.LivingBeing.Master;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.CavalerMouv.CavalerMouv;
import org.SAPLA.MouvementType.DiagonalMouv.DiagonalMouv;
import org.SAPLA.MouvementType.KingMouv.KingMouv;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.MouvementType.TowerMouv.TowerMouv;
import org.SAPLA.utils.Constants;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {

    private final ScheduledExecutorService executor;
    private final Runnable task;

    private Map map;
    //private LivingBeing winner;
    private final ArrayList<String> individuals;//TODO: à changer par tableau de LivingBeing

    private int stepCount = 0;
    private boolean isRunningAutomatically = false;

    private ArrayList<LivingBeing> individualsList = new ArrayList<LivingBeing>();

    private ArrayList<LivingBeing> masters = new ArrayList<>();

    public Game() {

        this.individuals = new ArrayList<String>();

        this.map = new Map(Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
        map.generateMap();

        generateMasters();
        generateFactions();

        map.DisplayMap();
        displayIndividuals();

        executor = Executors.newScheduledThreadPool(1);


        //TODO Modifier le contenu, actuellement c'est simplement un test
        task = this::gameStep;
    }


    //Mode manuel : exécuter la simulation
    public void runManual() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Simulation prête. Appuyez sur [Entrée] pour avancer ou tapez 'quit' pour arrêter.");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Simulation terminée.");
                break;
            }
            gameStep();
        }

        scanner.close();
    }

    //Mode automatique : démarrer la simulation
    public void startAutomatic() {
        if (!isRunningAutomatically) {
            isRunningAutomatically = true;
            System.out.println("Simulation en mode automatique...");
            executor.scheduleAtFixedRate(task, 0, 1000, TimeUnit.MILLISECONDS);
        } else {
            System.out.println("La simulation est déjà en mode automatique.");
        }
    }

    //Mode automatique : arrêter la simulation
    public void stopAutomatic() {
        if (isRunningAutomatically) {
            isRunningAutomatically = false;
            executor.shutdown();
            try {
                if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
                System.out.println("Simulation automatique arrêtée.");
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        } else {
            System.out.println("La simulation n'est pas en mode automatique.");
        }
    }


    //Fonction contenant tout ce qui est exécuté à chaque tour
    public void gameStep() {
        long startTime = System.currentTimeMillis();

        //Placer ici les actions à exécuter à chaque tour

        //Randomize the order of individuals
        shuffleOrderIndividuals();
        //Display individuals list
        displayIndividuals();
        //Play each individual
        playIndividuals();


        //Insert 25ms delay to simulate processing time and prevent same random seed
        // (because the seed is based on the current time in milliseconds)
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Fin de l'étape " + (stepCount + 1) + " à " + endTime + ", durée : " + (endTime - startTime) + " ms");

        stepCount++;
    }


    //Fonction pour mélanger l'ordre des individus
    public void shuffleOrderIndividuals() {
        Random random = new Random();

        for (int i = this.individualsList.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            // Échanger array[i] et array[j]
            LivingBeing temp = this.individualsList.get(i);
            this.individualsList.set(i, this.individualsList.get(j));
            this.individualsList.set(j, temp);
        }

    }

    public void displayIndividuals() {
        System.out.println("=============================");
        for (LivingBeing individual : this.individualsList) {
            System.out.println(individual + " : " + individual.getCurrentTile().getPosition().toString());
        }
    }

    //Fonction pour faire jouer chaque individu
    public void playIndividuals() {
        for (LivingBeing individual : this.individualsList) {
            individual.move(this);
        }
    }

    private final Class<?>[] factionClasses = {
            Faction1.class,
            Faction2.class,
            Faction3.class,
            Faction4.class
    };

    private java.util.HashMap<Class<? extends LivingBeing>, Class<? extends MouvementType>> createFactionDictionary() {
        java.util.HashMap<Class<? extends LivingBeing>, Class<? extends MouvementType>> factionTable = new java.util.HashMap<>();

        factionTable.put(Faction1.class, KingMouv.class);
        factionTable.put(Faction2.class, TowerMouv.class);
        factionTable.put(Faction3.class, DiagonalMouv.class);
        factionTable.put(Faction4.class, CavalerMouv.class);

        return factionTable;
    }


    private void generateFactions() {
        java.util.Map<Class<? extends LivingBeing>, Class<? extends MouvementType>> factionTable =
                (java.util.Map<Class<? extends LivingBeing>, Class<? extends MouvementType>>) createFactionDictionary();

        for (java.util.Map.Entry<Class<? extends LivingBeing>, Class<? extends MouvementType>> factionClass : factionTable.entrySet()) {
            try {
                // Get the associated MouvementType class
                Class<? extends MouvementType> mouvementClass = factionClass.getValue();

                // Create an instance of MouvementType
                MouvementType mouvementInstance = mouvementClass.getConstructor().newInstance();

                for(int i = 0; i <= Constants.NB_INDIVIDUALS; i++){
                    switch (mouvementInstance.getClass().getSimpleName()) {
                        case "KingMouv":
                            this.individualsList.add(new Faction1<>(Map.getMapGrid()[0][0], Direction.NORTH, Constants.STARTING_ENERGY, (KingMouv)mouvementInstance));
                            break;
                        case "TowerMouv":
                            this.individualsList.add(new Faction2<>(Map.getMapGrid()[0][0], Direction.NORTH, Constants.STARTING_ENERGY, (TowerMouv)mouvementInstance));
                            break;
                        case "DiagonalMouv":
                            this.individualsList.add(new Faction3<>(Map.getMapGrid()[0][0], Direction.NORTH, Constants.STARTING_ENERGY, (DiagonalMouv)mouvementInstance));
                            break;
                        case "CavalerMouv":
                            this.individualsList.add(new Faction4<>(Map.getMapGrid()[0][0], Direction.NORTH, Constants.STARTING_ENERGY, (CavalerMouv)mouvementInstance));
                            break;
                    }
                }
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                e.printStackTrace(); // Log or handle appropriately
            }
        }
    }

    private void generateMasters() {
        Tile tileMasterFaction1 = this.map.setTileContentAtPosition(Constants.MASTER_FACTION_1_POSITION, 'M');
        Tile tileMasterFaction2 = this.map.setTileContentAtPosition(Constants.MASTER_FACTION_2_POSITION, 'M');
        Tile tileMasterFaction3 = this.map.setTileContentAtPosition(Constants.MASTER_FACTION_3_POSITION, 'M');
        Tile tileMasterFaction4 = this.map.setTileContentAtPosition(Constants.MASTER_FACTION_4_POSITION, 'M');
        MasterFaction1 masterFaction1 = MasterFaction1.getInstance();
        masterFaction1.setFixedTile(tileMasterFaction1);
        this.masters.add(masterFaction1);
        MasterFaction2 masterFaction2 = MasterFaction2.getInstance();
        masterFaction2.setFixedTile(tileMasterFaction2);
        this.masters.add(masterFaction2);
        MasterFaction3 masterFaction3 = MasterFaction3.getInstance();
        masterFaction3.setFixedTile(tileMasterFaction3);
        this.masters.add(masterFaction3);
        MasterFaction4 masterFaction4 = MasterFaction4.getInstance();
        masterFaction4.setFixedTile(tileMasterFaction4);
        this.masters.add(masterFaction4);
    }

    public Master getMaster(LivingBeing individu) {
        return (Master) this.masters.stream().filter(master -> master.getClass().getSuperclass() == individu.getClass()).findFirst().orElse(null);
    }
}



