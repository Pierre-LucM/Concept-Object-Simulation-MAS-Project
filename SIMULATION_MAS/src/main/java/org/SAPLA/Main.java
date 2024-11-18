package org.SAPLA;

import org.SAPLA.Map.GameMap;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) {
        GameMap service = new GameMap();
        service.setSize(10,20);
    service.createGrid();
    service.printGrid();


    }
}