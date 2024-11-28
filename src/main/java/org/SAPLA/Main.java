package org.SAPLA;


import org.SAPLA.Map.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) {
        Map map = new Map(20, 20);
        map.generateMap();
        map.DisplayMap();
    }
}