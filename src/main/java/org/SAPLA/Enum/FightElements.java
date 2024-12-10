package org.SAPLA.Enum;

public enum FightElements {
    Rock("Rock"), Paper("Paper"), Scissors("Scissors");

    private final String _name;

    FightElements(String name) {
        this._name = name;
    }

    public String toString() {
        return this._name;
    }
}
