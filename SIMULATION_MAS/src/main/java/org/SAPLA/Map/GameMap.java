package org.SAPLA.Map;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;

import java.util.ArrayList;

public class GameMap  {




    private ArrayList<ArrayList<Entity>> _grid;
    private int _width;
    private int _height;


    public GameMap() {
        _grid = new ArrayList<ArrayList<Entity>>();
    }

    public void setSize(int width, int height) {
        _width = width;
        _height = height;
    }

    public ArrayList<ArrayList<Entity>> getGrid() {
        return _grid;
    }

    public void createGrid() {
        _grid.clear();
        for (int x = 0; x < _width; x++) {
                this._grid.add(x,new ArrayList<Entity>(_height));
                for (int y = 0; y < _height; y++) {
                    this.addEntity(x,y, new EmptyEntity());
                }
            }
        }

    private void addEntity(int x, int y, Entity entity) {
        Point location = new Point();
        location.setX(x);
        location.setY(y);
        entity.setLocation(location);
        this._grid.get(x).add(entity);
    }

    public void printGrid() {
        // Print column headers with correct spacing
        System.out.print("    "); // Offset for row numbers
        for (int col = 0; col < _width; col++) {
            System.out.printf("%2d ", col); // Print column numbers with 2-width padding
        }
        System.out.println();

        // Print top border of the grid
        System.out.print("   +");
        for (int col = 0; col < _width; col++) {
            System.out.print("---"); // Separator for each column
        }
        System.out.println("+");

        // Print grid with row numbers, symbols, and column/row separators
        for (int x = 0; x < _height; x++) {
            System.out.printf("%2d | ", x); // Print row numbers with padding and row separator
            for (int y = 0; y < _width; y++) {
                System.out.print(_grid.get(y).get(x).getSymbol() + " | "); // Vertical separators between columns
            }
            System.out.println();

            // Print horizontal separator after each row
            System.out.print("   +");
            for (int col = 0; col < _width; col++) {
                System.out.print("---"); // Horizontal separator
            }
            System.out.println("+");
        }
    }


}
