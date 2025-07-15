package org.example.model.grid;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.controller.ViewportController;
import org.example.model.Point;

public class GridDots extends Grid {

    public GridDots(String name) {
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
        int r = 1;
        for (int x = startX; x < endX; x += spacing) {
            for (int y = startY; y < endY; y += spacing) {
                Point sp = vp.toScreen(x, y);
                gc.fillOval(sp.x() - r, sp.y() - r, r *2, r * 2);
                gc.strokeOval(sp.x() - r, sp.y() - r, r *2, r * 2);
            }
        }
    }
}
