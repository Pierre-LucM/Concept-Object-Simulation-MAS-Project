package org.SAPLA.Game;

import org.SAPLA.Enum.Direction;
import org.SAPLA.LivingBeing.LivingBeing;
import org.SAPLA.LivingBeing.GoodBeing.Faction1.Faction1;
import org.SAPLA.LivingBeing.GoodBeing.Faction2.Faction2;
import org.SAPLA.LivingBeing.BadBeing.Faction4.Faction4;
import org.SAPLA.LivingBeing.BadBeing.Faction3.Faction3;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.CavalerMouv.CavalerMouv;
import org.SAPLA.MouvementType.DiagonalMouv.DiagonalMouv;
import org.SAPLA.MouvementType.KingMouv.KingMouv;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.MouvementType.TowerMouv.TowerMouv;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;
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

    private ArrayList<LivingBeing> indivualsList = new ArrayList<LivingBeing>();


    public Game() {

        this.individuals = new ArrayList<String>();

        this.map = new Map(20, 20);
        map.generateMap();
        map.DisplayMap();

        generateFactions();
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

        // Placez ici les actions à exécuter à chaque tour
        for (int i = 0; i < 3; i++) {
            System.out.println("Action " + i + " exécutée à " + startTime);
        }
        stepCount++;

        long endTime = System.currentTimeMillis();
        System.out.println("Fin de l'étape " + stepCount + " à " + endTime + ", durée : " + (endTime - startTime) + " ms");
    }


    //Fonction pour mélanger l'ordre des individus
    public void shuffleOrderIndividuals() {
        Random random = new Random();

        for (int i = this.individuals.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            // Échanger array[i] et array[j]
            String temp = this.individuals.get(i);
            this.individuals.set(i, this.individuals.get(j));
            this.individuals.set(j, temp);
        }

    }

    //TODO DELETE WHEN LivingBeing CLASS IS CREATED
    public void remplirTableauIndviduals() {
        this.individuals.add("Individu 1");
        this.individuals.add("Individu 2");
        this.individuals.add("Individu 3");
        this.individuals.add("Individu 4");
        this.individuals.add("Individu 5");
    }

    //TODO DELETE WHEN LivingBeing CLASS IS CREATED
    public void afficherTableauIndviduals() {
        System.out.println("=============================");
        for (String individual : this.individuals) {
            System.out.println(individual);
        }
    }

    public void displayIndividuals() {
        for (LivingBeing individual : this.indivualsList) {
            System.out.println(individual);
        }
    }

    private final Class<?>[] factionClasses = {
            Faction1.class,
            Faction2.class,
            Faction3.class,
            Faction4.class
    };

    private Hashtable<Class<? extends LivingBeing>, Class<? extends MouvementType>> createFactionDictionary() {
        Hashtable<Class<? extends LivingBeing>, Class<? extends MouvementType>> factionTable = new Hashtable<>();

        factionTable.put(Faction1.class, KingMouv.class);
        factionTable.put(Faction2.class, TowerMouv.class);
        factionTable.put(Faction3.class, DiagonalMouv.class);
        factionTable.put(Faction4.class, CavalerMouv.class);

        return factionTable;
    }


    private void generateFactions() {
        Hashtable<Class<? extends LivingBeing>, Class<? extends MouvementType>> factionTable = createFactionDictionary();

        for (Class<? extends LivingBeing> factionClass : factionTable.keySet()) {
            try {
                System.out.println(factionClass);

                // Obtenir la classe de MouvementType associée
                Class<? extends MouvementType> mouvementClass = factionTable.get(factionClass);

                // Créer une instance de MouvementType
                MouvementType mouvementInstance = mouvementClass.getConstructor().newInstance();

                // Créer une instance de LivingBeing avec les paramètres spécifiés
                LivingBeing factionInstance = factionClass
                        .getConstructor(Tile.class, Direction.class, int.class, MouvementType.class)
                        .newInstance(Map.getMapGrid()[0][0], Direction.NORTH, 100, mouvementInstance);

                // Ajouter à la liste
                this.indivualsList.add(factionInstance);

            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                e.printStackTrace(); // Gérer l'erreur de manière appropriée (par exemple, journaliser ou lancer une exception personnalisée)
            }
        }
    }
}



