package org.example.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.controller.ColorController;
import org.example.controller.StageController;
import org.example.controller.ViewportController;
import org.example.model.Point;
import org.example.model.element.Line;
import org.example.model.element.LineEq;

import java.util.function.Consumer;

public class BodyRefTool extends Tool {
    private final StageController stageController;
    private final ViewportController vp;
    private final ColorController colorController;
    private LineEq lineRef;
    private Point startPoint = null;
    private Point currentMouse = null;

    public BodyRefTool(StageController stageController, ViewportController vp, ColorController colorController) {
        this.stageController = stageController;
        this.vp = vp;
        this.colorController = colorController;
        lineRef = new LineEq(353.57965547493416, -9656.533658586199, 5570853.02282785);
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
        stageController.getActiveStage().addElement(new Line(startPoint, endPoint, colorController.getCurrentColor()));
        stageController.setIntersectPoint(stageController.getFloorRef().getIntersect(new LineEq(startPoint, endPoint)));
        stageController.getActiveStage().addElement(
                new LineEq(
                        stageController.getZVanishingPoint(),
                        stageController.getIntersectPoint(),
                        colorController.getCurrentColor()
                ));
        currentMouse = null;
        renderRequest.accept("");
    }

    @Override
    public void onMouseMoved(MouseEvent event, Consumer<String> renderRequest) {
        currentMouse = vp.toWorld(event.getX(), event.getY());
        startPoint = lineRef.getIntersect(new LineEq(currentMouse, new Point(currentMouse.x(), 0)));
        renderRequest.accept("");
    }

    public LineEq getLineRef() {
        return lineRef;
    }

    public void setLineRef(LineEq lineRef) {
        if (lineRef != null)
            this.lineRef = lineRef;
    }
}
