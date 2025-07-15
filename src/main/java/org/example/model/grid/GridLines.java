package org.example.model.grid;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.controller.ViewportController;
import org.example.model.Point;

public class GridLines extends Grid {

    public GridLines(String name) {
        super(name);
    }

    @Override
    public void draw(GraphicsContext gc, ViewportController vp, double width, double height) {
        Point topleft = vp.toWorld(0, 0);
        Point bottomright = vp.toWorld(width, height);

        int startX = (int) topleft.x() / spacing * spacing;
        int startY = (int) topleft.y() / spacing * spacing;

        int endX = (int) bottomright.x();
        int endY = (int) bottomright.y();

        gc.setStroke(Color.rgb(80, 80, 80));
        gc.setLineWidth(1);
        for (int x = startX; x < endX; x += spacing) {
            double sx = vp.toScreen(x, 0).x();
            gc.strokeLine(sx, 0, sx, height);
        }

        for (int y = startY; y < endY; y += spacing) {
            double sy = vp.toScreen(0, y).y();
            gc.strokeLine(0, sy, width, sy);
        }
    }
}
