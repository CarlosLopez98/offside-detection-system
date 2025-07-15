package org.example.model.stage;

public class AttRefStage extends Stage {

    public AttRefStage() {
        super("Attacker references",
                " Create the attacker reference. \n" +
                        "1. Set the floor reference of the player (can be one of the foot). \n" +
                        "2. Now set the part of the body (Ex. The shoulder, the head, the knee, etc.). \n" +
                        "3. After this, the wide side reference of the player will appear.");
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
