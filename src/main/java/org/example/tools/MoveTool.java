package org.example.tools;

import javafx.scene.input.MouseEvent;
import org.example.controller.ViewportController;

import java.util.function.Consumer;

public class MoveTool extends Tool {
    private final ViewportController vp = ViewportController.getInstance();
    private double lastMousePosX = 0;
    private double lastMousePosY = 0;

    @Override
    public void onMousePressed(MouseEvent event, Consumer<String> renderRequest) {
        lastMousePosX = event.getX();
        lastMousePosY = event.getY();
    }

    @Override
    public void onMouseDragged(MouseEvent event, Consumer<String> renderRequest) {
        double mx = event.getX(), my = event.getY();

        double dx = lastMousePosX - mx;
        double dy = lastMousePosY - my;
        vp.translate(-dx, -dy);

        lastMousePosX = mx;
        lastMousePosY = my;

        renderRequest.accept("");
    }
}
