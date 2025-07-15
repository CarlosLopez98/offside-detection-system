package org.example.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.controller.ViewportController;

import java.io.File;

public class ImageLayer {
    private final Image image;
    private final Point position;

    public ImageLayer(String imageUrl) {
        File file = new File(imageUrl);
        image = new Image(file.toURI().toString());
        position = new Point(0, 0);
    }

    public ImageLayer(File imageFile) {
        image = new Image(imageFile.toURI().toString());
        position = new Point(0, 0);
    }

    public void draw(GraphicsContext gc) {
        ViewportController vp = ViewportController.getInstance();
        Point screenPos = vp.toScreen(position);
        double scale = vp.getScale();
        gc.drawImage(image, screenPos.x(), screenPos.y(), image.getWidth() * scale, image.getHeight() * scale);
    }

    public Image getImage() {
        return image;
    }

    public Point getPosition() {
        return position;
    }
}
