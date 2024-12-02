package org.SAPLA;
import org.SAPLA.Game.Game;


import org.SAPLA.Map.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) {
        System.out.println("Hello World");





        //Pavé de test Aurélien
        Game game = new Game();

        game.startAutomatic();

        //Pour le moment, la simulation automatique s'arrête après 5 secondes,
        //plus tard, il suffira d'appeler la méthode stopAutomatic() pour arrêter la simulation
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.stopAutomatic();


        //game.runManual();
    }
}