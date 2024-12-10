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

        game.startAutomatic();

        //Si la simulation ne s'est pas terminée avant elle se terminera automatiquement au bout de 100000 millisecondes
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.stopAutomatic();


        //game.runManual();


    }
}