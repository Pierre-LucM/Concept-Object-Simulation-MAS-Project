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
import org.SAPLA.MouvementType.CavalerMouv.CavalerMouv;
import org.SAPLA.MouvementType.DiagonalMouv.DiagonalMouv;
import org.SAPLA.MouvementType.KingMouv.KingMouv;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.MouvementType.TowerMouv.TowerMouv;
import org.SAPLA.RandomGeneration.RandomGeneration;
import org.SAPLA.utils.Constants;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {

    private static Game _game;

    private final ScheduledExecutorService executor;
    private final Runnable task;

    private final Map map;

    private int stepCount = 0;
    private boolean isRunningAutomatically = false;

    private final List<LivingBeing> individualsList = new ArrayList<>();

    private final List<LivingBeing> masters = new ArrayList<>();

    private List<LivingBeing> winners = new ArrayList<>();

    private List<String> allMessages = new ArrayList<>();

    public static Game getInstance() {
        if(_game == null) {
            _game = new Game();
        }
        return _game;
    }

    private Game() {

        this.map = new Map(Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
        map.generateMap();

        generateAllMessages();
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
            if(!gameStep()) {
                break;
            }
        }
        this.displayFinalResults();
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
                this.displayFinalResults();
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        } else {
            System.out.println("La simulation n'est pas en mode automatique.");
        }
    }


    //Fonction contenant tout ce qui est exécuté à chaque tour
    public boolean gameStep() {
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

        checkIfThereIsAWinner();
        if(!winners.isEmpty()) {
            if(isRunningAutomatically) {
                this.stopAutomatic();
            }
            return false;
        } else {
            stepCount++;
            return true;
        }
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
            individual.move();
        }
    }

    private void checkIfThereIsAWinner() {
        winners = new ArrayList<>(masters.stream().filter(master -> ((IMaster) master).didIWin(allMessages)).toList());
    }

    private void displayFinalResults() {
         if(winners.isEmpty()) { //si on a arrêté la simulation avant qu'un des masters est récupéré tous les messages
            //Le gagnant sera donc le master qui a récupéré le plus de messages
            int nbMessagesMax = 0;
            for (LivingBeing master : masters) {
                int masterNbMessage = ((IMaster) master).getNbMessages();
                if(masterNbMessage > nbMessagesMax) {
                    nbMessagesMax = masterNbMessage;
                    winners.clear();
                    winners.add(master);
                } else if(masterNbMessage == nbMessagesMax) {
                    winners.add(master);
                }
            }
        }

        if(winners.size() == 1) {
            System.out.println("La faction gagnante est la faction " + winners.getFirst().getClass().getSuperclass().getSimpleName() + ".");
        } else if(winners.size() > 1) {
            StringBuilder result = new StringBuilder();
            result.append("Les factions gagnantes sont les factions ");
            for (int i = 0; i < winners.size(); i++) {
                result.append(winners.get(i).getClass().getSuperclass().getSimpleName());
                if (i < winners.size() - 1) {
                    result.append(", ");
                } else {
                    result.append(".");
                }
            }
            System.out.println(result);
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

        int messageIndex = 0;
        for (java.util.Map.Entry<Class<? extends LivingBeing>, Class<? extends MouvementType>> factionClass : factionTable.entrySet()) {
            try {
                // Get the associated MouvementType class
                Class<? extends MouvementType> mouvementClass = factionClass.getValue();

                // Create an instance of MouvementType
                MouvementType mouvementInstance = mouvementClass.getConstructor().newInstance();

                for(int i = 0; i < Constants.NB_INDIVIDUALS; i++){
                    switch (mouvementInstance.getClass().getSimpleName()) {
                        case "KingMouv":
                            this.individualsList.add(new Faction1<>(Map.getMapGrid()[0][0], Direction.NORTH, Constants.STARTING_ENERGY, (KingMouv)mouvementInstance, allMessages.subList(messageIndex, messageIndex + Constants.NB_MESSAGES_PER_INDIVIDUAL_AT_START)));
                            break;
                        case "TowerMouv":
                            this.individualsList.add(new Faction2<>(Map.getMapGrid()[0][0], Direction.NORTH, Constants.STARTING_ENERGY, (TowerMouv)mouvementInstance, allMessages.subList(messageIndex, messageIndex + Constants.NB_MESSAGES_PER_INDIVIDUAL_AT_START)));
                            break;
                        case "DiagonalMouv":
                            this.individualsList.add(new Faction3<>(Map.getMapGrid()[0][0], Direction.NORTH, Constants.STARTING_ENERGY, (DiagonalMouv)mouvementInstance, allMessages.subList(messageIndex, messageIndex + Constants.NB_MESSAGES_PER_INDIVIDUAL_AT_START)));
                            break;
                        case "CavalerMouv":
                            this.individualsList.add(new Faction4<>(Map.getMapGrid()[0][0], Direction.NORTH, Constants.STARTING_ENERGY, (CavalerMouv)mouvementInstance, allMessages.subList(messageIndex, messageIndex + Constants.NB_MESSAGES_PER_INDIVIDUAL_AT_START)));
                            break;
                    }
                    messageIndex += Constants.NB_MESSAGES_PER_INDIVIDUAL_AT_START;
                }
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                e.printStackTrace(); // Log or handle appropriately
            }
        }
    }

    private void generateMasters() {
        MasterFaction1 masterFaction1 = MasterFaction1.getInstance();
        masterFaction1.setFixedTile(this.map.setTileContentAtPosition(new Position(0,0), 'M'));
        this.masters.add(masterFaction1);
        MasterFaction2 masterFaction2 = MasterFaction2.getInstance();
        masterFaction2.setFixedTile(this.map.setTileContentAtPosition(new Position(Constants.MAP_WIDTH - 1,0), 'M'));
        this.masters.add(masterFaction2);
        MasterFaction3 masterFaction3 = MasterFaction3.getInstance();
        masterFaction3.setFixedTile(this.map.setTileContentAtPosition(new Position(0,Constants.MAP_HEIGHT - 1), 'M'));
        this.masters.add(masterFaction3);
        MasterFaction4 masterFaction4 = MasterFaction4.getInstance();
        masterFaction4.setFixedTile(this.map.setTileContentAtPosition(new Position(Constants.MAP_WIDTH - 1, Constants.MAP_HEIGHT - 1), 'M'));
        this.masters.add(masterFaction4);
    }

    private void generateAllMessages() {
        allMessages = RandomGeneration.generateRandomStrings();
    }

    public IMaster getMaster(LivingBeing individu) {
        return (IMaster) this.masters.stream().filter(master -> master.getClass().getSuperclass() == individu.getClass()).findFirst().orElse(null);
    }
}



