package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.example.controller.CanvasController;
import org.example.controller.GridController;
import org.example.controller.ViewportController;
import org.example.model.grid.Grid;
import org.example.model.ImageLayer;
import org.example.model.Point;

public class CanvasView extends StackPane {
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final CanvasController canvasController;
    private final ViewportController vp;
    private final GridController gridController;
    private Grid grid;
    private final ImageLayer imageLayer;
    private boolean viewAxis = true;

    public CanvasView(double width, double height, CanvasController canvasController, ViewportController vp,
                      GridController gridController, Grid grid, ImageLayer imageLayer, Label messageBox) {
        super();
        this.canvas = new Canvas(width, height);
        this.gc = canvas.getGraphicsContext2D();
        this.canvasController = canvasController;
        this.vp = vp;
        this.gridController = gridController;
        this.grid = grid;
        this.imageLayer = imageLayer;

        canvas.setManaged(false);

        canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());

        canvas.widthProperty().addListener((obs, oldVal, newVal) -> render());
        canvas.heightProperty().addListener((obs, oldVal, newVal) -> render());

        ConfigPane configPane = new ConfigPane(this, gridController);
        ToolbarPane toolbarPane = new ToolbarPane(canvasController.getToolController());

        getChildren().addAll(canvas, configPane, toolbarPane, messageBox);

        setAlignment(configPane, Pos.TOP_RIGHT);
        setAlignment(toolbarPane, Pos.TOP_LEFT);
        setAlignment(messageBox, Pos.BOTTOM_RIGHT);
        setMargin(messageBox, new Insets(0, 12, 12, 0));


        // Events
        canvas.setOnMouseClicked(event -> {
            canvasController.onMouseClicked(event, msg -> render());
        });
        canvas.setOnMousePressed(event -> {
            canvasController.onMousePressed(event, msg -> render());
        });
        canvas.setOnMouseReleased(event -> {
            canvasController.onMouseReleased(event, msg -> render());
        });
        canvas.setOnMouseDragged(event -> {
            canvasController.onMouseDragged(event, msg -> render());
        });
        canvas.setOnMouseMoved(event -> {
            canvasController.onMouseMoved(event, msg -> render());
        });

        // Scroll event for zoom
        canvas.setOnScroll(event -> {
            double factor = event.getDeltaY() > 0 ? 1.1 : 0.9;
            Point pivot = new Point(event.getX(), event.getY());
            vp.zoom(factor, pivot);
            render();
        });

        render();
    }

    public void render() {
        // Update canvas size
        vp.setWidth(canvas.getWidth());
        vp.setHeight(canvas.getHeight());

        gc.setFill(Color.rgb(30, 30, 30));
        gc.fillRect(0, 0, getWidth(), getHeight());

        grid = gridController.getCurrentGrid();
        grid.draw(gc, vp, getWidth(), getHeight());

        if (grid.isViewAxis()) {
            grid.drawAxis(gc, vp, getWidth(), getHeight());
        }

        imageLayer.draw(gc);

        canvasController.draw(gc);
    }

    private void drawAxis() {
        Point zero = vp.toScreen(0, 0);

        gc.setStroke(Color.rgb(141, 141, 141));
        gc.setLineWidth(1);
        gc.strokeLine(0, zero.y(), getWidth(), zero.y());
        gc.strokeLine(zero.x(), 0, zero.x(), getHeight());
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public boolean isViewAxis() {
        return viewAxis;
    }

    public void setViewAxis(boolean viewAxis) {
        this.viewAxis = viewAxis;
    }
}
