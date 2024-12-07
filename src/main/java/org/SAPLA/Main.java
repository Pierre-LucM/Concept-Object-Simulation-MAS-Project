package org.SAPLA;
import org.SAPLA.Game.Game;


import org.SAPLA.Map.Map;

public class Main {



    public static void main(String[] args) {
        System.out.println("Hello World");





        //Pavé de test Aurélien
        Game game = Game.getInstance();

        game.startAutomatic();

        //Pour le moment, la simulation automatique s'arrête après 5 secondes,
        //plus tard, il suffira d'appeler la méthode stopAutomatic() pour arrêter la simulation
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.stopAutomatic();


        //game.runManual();
    }
}