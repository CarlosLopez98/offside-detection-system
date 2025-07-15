package org.example.model.grid;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.controller.ViewportController;
import org.example.model.Point;

public abstract class Grid {
    protected static final int spacing = 40;
    protected static boolean viewAxis = true;
    protected String name;

    public Grid(String name) {
        this.name = name;
    }

    public abstract void draw(GraphicsContext gc, ViewportController vp, double width, double height);

    public void drawAxis(GraphicsContext gc, ViewportController vp, double width, double height) {
        Point zero = vp.toScreen(0, 0);

        gc.setStroke(Color.rgb(141, 141, 141));
        gc.setLineWidth(1);
        gc.strokeLine(0, zero.y(), width, zero.y());
        gc.strokeLine(zero.x(), 0, zero.x(), height);
    }

    public boolean isViewAxis() {
        return viewAxis;
    }

    public void setViewAxis(boolean viewAxis) {
        Grid.viewAxis = viewAxis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
