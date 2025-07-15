package org.example.controller;

import javafx.scene.canvas.GraphicsContext;
import org.example.model.Point;
import org.example.model.drawable.Drawable;
import org.example.model.element.Element;
import org.example.model.element.LineEq;
import org.example.model.stage.*;

import java.util.HashMap;
import java.util.Map;

import static org.example.model.stage.StageType.*;

public class StageController {
    private final Map<StageType, Stage> stages = new HashMap<>();
    private Stage activeStage;
    private StageType stageIndex = Z_AXIS;
    private Point zVanishingPoint = null;
    private Point xVanishingPoint = null;
    private LineEq floorRef = null;
    private Point intersectPoint = null;

    public StageController() {
        stages.put(Z_AXIS, new ZAxisStage());
        stages.put(X_AXIS, new XAxisStage());
        stages.put(DEF_REFERENCES, new DefRefStage());
        stages.put(ATT_REFERENCES, new AttRefStage());
        stages.put(RESUME, new ResumeStage());

        setActiveStage(Z_AXIS);
    }

    public void renderDrawableElements(GraphicsContext gc) {
        for (Drawable d: activeStage.getDrawableElements()) {
            d.draw(gc);
        }
    }

    public Map<StageType, Stage> getStages() {
        return stages;
    }

    public StageType getStageIndex() {
        return stageIndex;
    }

    public void setStageIndex(StageType stageIndex) {
        this.stageIndex = stageIndex;
    }

    public Stage getActiveStage() {
        return activeStage;
    }

    public void setActiveStage(StageType type) {
        if (stages.containsKey(type)) {
            this.activeStage = stages.get(type);
        }
    }

    public Point getZVanishingPoint() {
        return zVanishingPoint;
    }

    public void setZVanishingPoint(Point zVanishingPoint) {
        this.zVanishingPoint = zVanishingPoint;
    }

    public Point getXVanishingPoint() {
        return xVanishingPoint;
    }

    public void setXVanishingPoint(Point xVanishingPoint) {
        this.xVanishingPoint = xVanishingPoint;
    }

    public LineEq getFloorRef() {
        return floorRef;
    }

    public void setFloorRef(LineEq floorRef) {
        this.floorRef = floorRef;
    }

    public Point getIntersectPoint() {
        return intersectPoint;
    }

    public void setIntersectPoint(Point intersectPoint) {
        this.intersectPoint = intersectPoint;
    }

    public void nextStage() {
        switch (stageIndex) {
            case Z_AXIS -> {
                if (activeStage.isCompleted()) {
                    stageIndex = X_AXIS;
                }
            }
            case X_AXIS -> {
                if (activeStage.isCompleted()) {
                    zVanishingPoint = LineEq.vanishingPoint(stages.get(Z_AXIS).getElementsByType(LineEq.class));
                    xVanishingPoint = LineEq.vanishingPoint(stages.get(X_AXIS).getElementsByType(LineEq.class));
                    stageIndex = DEF_REFERENCES;
                }
            }
            case DEF_REFERENCES -> {
                if (activeStage.isCompleted()) {
                    stageIndex = ATT_REFERENCES;
                }
            }
            case ATT_REFERENCES -> {
                if (activeStage.isCompleted()) {
                    stageIndex = RESUME;
                    stages.get(RESUME).addElements(stages.get(DEF_REFERENCES).getElements());
                    stages.get(RESUME).addElements(stages.get(ATT_REFERENCES).getElements());
                }
            }
        }
        activeStage = stages.get(stageIndex);
    }

    public void previousStage() {
        switch (stageIndex) {
            case X_AXIS -> {
                stageIndex = Z_AXIS;
            }
            case DEF_REFERENCES -> {
                stageIndex = X_AXIS;
            }
            case ATT_REFERENCES -> {
                stageIndex = DEF_REFERENCES;
            }
            case RESUME -> {
                stageIndex = ATT_REFERENCES;
            }
        }
        activeStage = stages.get(stageIndex);
    }
}
