package org.example.model.stage;

import org.example.model.Point;

public class DefRefStage extends Stage {
    private Point ZVanishingPoint;
    private Point XVanishingPoint;

    public DefRefStage() {
        super("Defender references",
        " Create the defender reference. \n" +
                "1. Set the floor reference of the player (can be one of the foot). \n" +
                "2. Now set the part of the body (Ex. The shoulder, the head, the knee, etc.). \n" +
                "3. After this, the wide side reference of the player will appear.");
    }

    public Point getZVanishingPoint() {
        return ZVanishingPoint;
    }

    public void setZVanishingPoint(Point ZVanishingPoint) {
        this.ZVanishingPoint = ZVanishingPoint;
    }

    public Point getXVanishingPoint() {
        return XVanishingPoint;
    }

    public void setXVanishingPoint(Point XVanishingPoint) {
        this.XVanishingPoint = XVanishingPoint;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
