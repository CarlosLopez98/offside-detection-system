package org.example.model.element;

import javafx.scene.paint.Color;
import org.example.model.Point;

public class Line implements Element {
    private Point start;
    private Point end;
    private Color color;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.color = Color.rgb(230, 150, 0);
    }

    public Line(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
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
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                "}";
    }
}
