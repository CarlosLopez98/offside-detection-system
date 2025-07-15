package org.example.controller;

import javafx.scene.image.Image;
import org.example.model.Point;

public class ViewportController {
    private static ViewportController instance = null;
    private double width;
    private double height;
    private Image image;
    private double scale;
    private double minScale;
    private double maxScale;
    private double offsetX;
    private double minOffsetX;
    private double maxOffsetX;
    private double offsetY;
    private double minOffsetY;
    private double maxOffsetY;

    private ViewportController(double scale, double offsetX, double offsetY) {
        this.scale = scale;
        this.minScale = scale * 0.5;
        this.maxScale = scale * 3;

        this.offsetX = offsetX;
        this.minOffsetX = 0;
        this.maxOffsetX = 0;

        this.offsetY = offsetY;
        this.minOffsetY = 0;
        this.maxOffsetY = 0;
    }

    public static ViewportController getInstance() {
        if (instance == null) {
            instance = new ViewportController(1, 0, 0);
        }
        return instance;
    }

    public void translate(double dx, double dy) {
        offsetX -= dx;
        if (-offsetX < minOffsetX) offsetX = -minOffsetX;
        if (-offsetX > maxOffsetX) offsetX = -maxOffsetX;

        offsetY -= dy;
        if (-offsetY < minOffsetY) offsetY = -minOffsetY;
        if (-offsetY > maxOffsetY) offsetY = -maxOffsetY;
    }

    public void zoom(double factor, Point pivot) {
        Point center = toWorld(pivot);
        scale *= factor;
        if (scale < minScale) scale = minScale;
        if (scale > maxScale) scale = maxScale;

        offsetX = center.x() * scale - pivot.x();
        offsetY = center.y() * scale - pivot.y();
    }

    public Point toWorld(Point sp) {
        return new Point(
                (sp.x() + offsetX) / scale,
                (sp.y() + offsetY) / scale
        );
    }

    public Point toWorld(double sx, double sy) {
        return new Point(
                (sx + offsetX) / scale,
                (sy + offsetY) / scale
        );
    }

    public Point toScreen(Point wp) {
        return new Point(
                wp.x() * scale - offsetX,
                wp.y() * scale - offsetY
        );
    }

    public Point toScreen(double wx, double wy) {
        return new Point(
                wx * scale - offsetX,
                wy * scale - offsetY
        );
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
        this.minScale = scale * 0.5;
        this.maxScale = scale * 3;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
        minOffsetX = -image.getWidth() * scale;
        maxOffsetX = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        minOffsetY = -image.getHeight() * scale;
        maxOffsetY = height;
    }
}
