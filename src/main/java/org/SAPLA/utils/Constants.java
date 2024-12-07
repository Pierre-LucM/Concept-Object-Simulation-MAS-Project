package org.SAPLA.utils;

import org.SAPLA.Map.Position;

public class Constants {
    //Empêcher l'instanciation de la classe
    private Constants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }

    // Constantes spécifiques
    public static final int MAP_WIDTH = 20;
    public static final int MAP_HEIGHT = 20;
    public static final int NB_INDIVIDUALS = 3; //Nombre d'individus dans la simulation
    public static final int STARTING_ENERGY = 100; //Énergie initiale des individus
    public static final int MAX_ENERGY = 100;     //Énergie maximale des individus

    // Positions fixes des masters
    public static final Position MASTER_FACTION_1_POSITION = new Position(0,0);
    public static final Position MASTER_FACTION_2_POSITION = new Position(19,0);
    public static final Position MASTER_FACTION_3_POSITION = new Position(0,19);
    public static final Position MASTER_FACTION_4_POSITION = new Position(19,19);
}
