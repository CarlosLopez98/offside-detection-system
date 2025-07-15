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

public class FloorRefTool extends Tool {
    private final StageController stageController;
    private final ViewportController vp;
    private final ColorController colorController;
    private final LineEq lineRef = new LineEq(new Point(0, 0), new Point(0, 1));
    private Point startPoint;
    private Point currentMouse = null;

    public FloorRefTool(StageController stageController, ViewportController vp, ColorController colorController) {
        this.stageController = stageController;
        this.vp = vp;
        this.colorController = colorController;
        startPoint = null;
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
        Point endPoint = vp.toWorld(event.getX(), event.getY());
        stageController.getActiveStage().addElement(new LineEq(startPoint, endPoint, colorController.getCurrentColor()));
        LineEq l = new LineEq(startPoint, endPoint);
        stageController.setFloorRef(l);
        currentMouse = null;
        renderRequest.accept("");
    }

    @Override
    public void onMouseMoved(MouseEvent event, Consumer<String> renderRequest) {
        currentMouse = vp.toWorld(event.getX(), event.getY());
        if (startPoint == null) {
            startPoint = lineRef.getIntersect(new LineEq(currentMouse, new Point(0, currentMouse.y())));
        }
        renderRequest.accept("");
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
}
