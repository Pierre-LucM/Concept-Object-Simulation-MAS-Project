package org.SAPLA.Game;

import org.SAPLA.Map.Map;

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


    public Game() {

        this.individuals = new ArrayList<String>();

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
    public void shuffleOrderIndividuals(){
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
    public void remplirTableauIndviduals(){
        this.individuals.add("Individu 1");
        this.individuals.add("Individu 2");
        this.individuals.add("Individu 3");
        this.individuals.add("Individu 4");
        this.individuals.add("Individu 5");
    }

    //TODO DELETE WHEN LivingBeing CLASS IS CREATED
    public void afficherTableauIndviduals(){
        System.out.println("=============================");
        for (String individual : this.individuals) {
            System.out.println(individual);
        }
    }

}
