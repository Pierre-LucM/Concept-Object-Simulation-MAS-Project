package org.SAPLA;

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

public class Main {



    public static void main(String[] args) {



        //Pavé de test Aurélien
        Game game = Game.getInstance();

        displayAllInstancesCount();
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

    private static void displayAllInstancesCount() {
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