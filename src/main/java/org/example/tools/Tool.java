package org.example.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.function.Consumer;

public abstract class Tool {
    public void draw(GraphicsContext gc) {};
    public void onMouseClicked(MouseEvent event, Consumer<String> renderRequest) {};
    public void onMousePressed(MouseEvent event, Consumer<String> renderRequest) {};
    public void onMouseReleased(MouseEvent event, Consumer<String> renderRequest) {};
    public void onMouseDragged(MouseEvent event, Consumer<String> renderRequest) {};
    public void onMouseMoved(MouseEvent event, Consumer<String> renderRequest) {};
}
