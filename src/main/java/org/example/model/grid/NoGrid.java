package org.example.model.grid;

import javafx.scene.canvas.GraphicsContext;
import org.example.controller.ViewportController;

public class NoGrid extends Grid {
    public NoGrid(String name) {
        super(name);
    }

    @Override
    public void draw(GraphicsContext gc, ViewportController vp, double width, double height) {

    }
}
