package org.SAPLA.utils;

public class Constants {
    //Empêcher l'instanciation de la classe
    private Constants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated");
    }

    // Constantes spécifiques
    public static final int NB_INDIVIDUALS = 3; //Nombre d'individus dans la simulation
    public static final int STARTING_ENERGY = 100; //Énergie initiale des individus
    public static final int MAX_ENERGY = 100;     //Énergie maximale des individus
}
