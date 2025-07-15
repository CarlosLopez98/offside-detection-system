package org.example.model.stage;

public class ResumeStage extends Stage {

    public ResumeStage() {
        super("Resume", "Here you have the result");
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
