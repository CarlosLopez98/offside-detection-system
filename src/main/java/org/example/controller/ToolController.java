package org.example.controller;

import org.example.tools.*;

import java.util.HashMap;
import java.util.Map;

public class ToolController {
    private final StageController layerController;
    private final ViewportController viewportController;
    private final ColorController colorController;
    private final Map<ToolType, Tool> tools = new HashMap<>();
    private Tool currentTool;
    private Tool lastTool;

    public ToolController(StageController layerController, ViewportController viewportController, ColorController colorController) {
        this.layerController = layerController;
        this.viewportController = viewportController;
        this.colorController = colorController;

        tools.put(ToolType.SELECT, new SelectionTool());
        tools.put(ToolType.MOVE, new MoveTool());
        tools.put(ToolType.LINE, new LineTool(layerController, viewportController, colorController));
        tools.put(ToolType.FIELD_REFERENCE, new LineEqTool(layerController, viewportController, colorController));
        tools.put(ToolType.FLOOR_REFERENCE, new FloorRefTool(layerController, viewportController, colorController));
        tools.put(ToolType.BODY_REFERENCE, new BodyRefTool(layerController, viewportController, colorController));

        currentTool = tools.get(ToolType.MOVE);
        lastTool = currentTool;
    }

    public Tool getCurrentTool() {
        return currentTool;
    }

    public void setCurrentTool(ToolType type) {
        lastTool = currentTool;
        switch (type) {
            case SELECT -> currentTool = tools.get(ToolType.SELECT);
            case MOVE -> currentTool = tools.get(ToolType.MOVE);
            case LINE -> currentTool = tools.get(ToolType.LINE);
            case FIELD_REFERENCE -> currentTool = tools.get(ToolType.FIELD_REFERENCE);
            case FLOOR_REFERENCE -> {
                currentTool = tools.get(ToolType.FLOOR_REFERENCE);
                if (currentTool instanceof FloorRefTool) {
                    ((FloorRefTool) currentTool).setStartPoint(layerController.getXVanishingPoint());
                }
            }
            case BODY_REFERENCE -> {
                currentTool = tools.get(ToolType.BODY_REFERENCE);
                if (currentTool instanceof BodyRefTool) {
                    ((BodyRefTool) currentTool).setLineRef(layerController.getFloorRef());
                }
            }
        }
    }

    public void goBackToLastTool() {
        currentTool = lastTool;
    }
}
