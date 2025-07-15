package org.example.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.function.Consumer;

public class CanvasController {
    private final StageController layerController;
    private final ViewportController viewportController;
    private final ToolController toolController;

    public CanvasController(StageController layerController,
                            ViewportController viewportController, ToolController toolController) {
        this.layerController = layerController;
        this.viewportController = viewportController;
        this.toolController = toolController;
    }

    public void draw(GraphicsContext gc) {
        layerController.renderDrawableElements(gc);
        toolController.getCurrentTool().draw(gc);
    }

    public void onMouseClicked(MouseEvent event, Consumer<String> renderRequest) {
        toolController.getCurrentTool().onMouseClicked(event, renderRequest);
    }

    public void onMousePressed(MouseEvent event, Consumer<String> renderRequest) {
        toolController.getCurrentTool().onMousePressed(event, renderRequest);
    }

    public void onMouseReleased(MouseEvent event, Consumer<String> renderRequest) {
        toolController.getCurrentTool().onMouseReleased(event, renderRequest);
    }

    public void onMouseDragged(MouseEvent event, Consumer<String> renderRequest) {
        toolController.getCurrentTool().onMouseDragged(event, renderRequest);
    }

    public void onMouseMoved(MouseEvent event, Consumer<String> renderRequest) {
        toolController.getCurrentTool().onMouseMoved(event, renderRequest);
    }

    public StageController getLayerController() {
        return layerController;
    }

    public ViewportController getViewportController() {
        return viewportController;
    }

    public ToolController getToolController() {
        return toolController;
    }
}
