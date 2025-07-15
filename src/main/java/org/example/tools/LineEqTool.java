package org.example.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.controller.ColorController;
import org.example.controller.StageController;
import org.example.controller.ViewportController;
import org.example.model.Point;
import org.example.model.element.LineEq;

import java.util.function.Consumer;

public class LineEqTool extends Tool {
    private final StageController layerController;
    private final ViewportController vp;
    private final ColorController colorController;
    private Point startPoint = null;
    private Point currentMouse = null;

    public LineEqTool(StageController layerController, ViewportController vp, ColorController colorController) {
        this.layerController = layerController;
        this.vp = vp;
        this.colorController = colorController;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (startPoint != null && currentMouse != null) {
            Point screenStart = vp.toScreen(startPoint);
            Point screenEnd = vp.toScreen(currentMouse);

            gc.setStroke(Color.rgb(200, 30, 30));
            gc.setLineDashes(4);
            gc.setLineWidth(2);
            gc.strokeLine(screenStart.x(), screenStart.y(), screenEnd.x(), screenEnd.y());
            gc.setLineDashes(0);

            gc.setFill(Color.rgb(200, 200, 200));
            double r = 2;
            gc.fillOval(screenStart.x() - r, screenStart.y() - r, r * 2, r * 2);
        }
    }

    @Override
    public void onMouseClicked(MouseEvent event, Consumer<String> renderRequest) {
        currentMouse = vp.toWorld(event.getX(), event.getY());
        if (startPoint == null) {
            startPoint = vp.toWorld(event.getX(), event.getY());
        } else {
            Point endPoint = vp.toWorld(event.getX(), event.getY());
            LineEq newLine = new LineEq(startPoint, endPoint, colorController.getCurrentColor());
            layerController.getActiveStage().addElement(newLine);
            startPoint = null;
            currentMouse = null;
        }
        renderRequest.accept("");
    }

    @Override
    public void onMouseMoved(MouseEvent event, Consumer<String> renderRequest) {
        if (startPoint != null) {
            currentMouse = vp.toWorld(event.getX(), event.getY());
            renderRequest.accept("");
        }
    }
}
