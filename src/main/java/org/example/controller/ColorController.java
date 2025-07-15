package org.example.controller;

import javafx.scene.paint.Color;

public class ColorController {
    private final StageController stageController;
    private Color currentColor;

    public ColorController(StageController stageController) {
        this.stageController = stageController;
    }

    public Color getCurrentColor() {
        switch (stageController.getStageIndex()) {
            case Z_AXIS -> currentColor = Color.rgb(220, 230, 10);
            case X_AXIS -> currentColor = Color.rgb(230, 160, 10);
            case DEF_REFERENCES -> currentColor = Color.rgb(10, 50, 230);
            case ATT_REFERENCES -> currentColor = Color.rgb(230, 10, 20);
            default -> currentColor = Color.rgb(220, 240, 240);
        }
        return currentColor;
    }
}
