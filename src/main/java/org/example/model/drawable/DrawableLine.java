package org.example.model.drawable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.model.Point;

public class DrawableLine implements Drawable {
    private Point start;
    private Point end;
    private Color color;
    private static final int width = 1;

    public DrawableLine(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.color = Color.rgb(200, 100, 40);
    }

    public DrawableLine(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.setLineWidth(width);
        gc.strokeLine(start.x(), start.y(), end.x(), end.y());
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "DrawableLine{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
