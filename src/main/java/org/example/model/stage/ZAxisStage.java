package org.example.model.stage;

import org.example.model.element.LineEq;

public class ZAxisStage extends Stage {

    public ZAxisStage() {
        super("Z Axis references",
                "Create at least two references of the field. " +
                        "These references must be from the wide side of the field.");
    }

    @Override
    public boolean isCompleted() {
        long size = elements.stream()
                .filter(e -> e instanceof LineEq)
                .count();

        return size >= 2;
    }
}
