package org.example.controller;

import org.example.model.grid.Grid;
import org.example.model.grid.GridDots;
import org.example.model.grid.GridLines;
import org.example.model.grid.NoGrid;

import java.util.ArrayList;
import java.util.List;

public class GridController {
    private final List<Grid> gridStyles = new ArrayList<>();
    private int gridIndex = 0;

    public GridController() {
        gridStyles.add(new GridLines("Lines"));
        gridStyles.add(new GridDots("Dots"));
        gridStyles.add(new NoGrid("No"));
    }

    public Grid getCurrentGrid() {
        return gridStyles.get(gridIndex);
    }

    public void changeGrid() {
        gridIndex++;
        if (gridIndex >= gridStyles.size()) {
            gridIndex = 0;
        }
    }
}
