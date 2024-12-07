package org.SAPLA.utils;

public class Constants {
    //Empêcher l'instanciation de la classe
    private Constants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }

    // Constantes spécifiques
    public static final int MAP_WIDTH = 20;
    public static final int MAP_HEIGHT = 20;
    public static final int NB_INDIVIDUALS = 3; //Nombre d'individus par faction dans la simulation
    public static final int NB_FACTIONS = 4; //Nombre de factions dans la simulation
    public static final int STARTING_ENERGY = 100; //Énergie initiale des individus
    public static final int MAX_ENERGY = 100;     //Énergie maximale des individus
    public static final int MESSAGE_LENGTH = 10; //Taille des messages
    public static final int NB_MESSAGES_PER_INDIVIDUAL_AT_START = 3; //Nombre de messages par individu au début de la simulation
}
