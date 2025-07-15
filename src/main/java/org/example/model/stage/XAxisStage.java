package org.example.model.stage;

import org.example.model.element.LineEq;

public class XAxisStage extends Stage {

    public XAxisStage() {
        super("X Axis references",
                "Create at least two references of the field. " +
                        "These references must be from the long side of the field.");
    }

    @Override
    public boolean isCompleted() {
        long size = elements.stream()
                .filter(e -> e instanceof LineEq)
                .count();

        return size >= 2;
    }
}
