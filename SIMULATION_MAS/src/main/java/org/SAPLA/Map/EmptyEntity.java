package org.SAPLA.Map;

public class EmptyEntity extends Entity{

    protected EmptyEntity() {
        super();
    }

    @Override
    public void interact() {
        System.out.println("Empty Entity");
    }

    @Override
    public char getSymbol() {
        return '*';
    }
}
