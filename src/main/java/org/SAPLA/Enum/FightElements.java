package org.SAPLA.Enum;

public enum FightElements {
    Rock("Pierre"), Paper("Papier"), Scissors("Ciseaux");

    private final String _name;

    FightElements(String name) {
        this._name = name;
    }

    public String toString() {
        return this._name;
    }
}
