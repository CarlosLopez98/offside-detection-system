package org.example.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.example.controller.StageController;

public class StageSelectorPane extends HBox {

    public StageSelectorPane(CanvasView canvasView, StageController layerController, Label messageBox) {
        Label layerLbl = new Label(layerController.getActiveStage().getName());
        layerLbl.setStyle("-fx-text-fill: #f1f1f1;");

        messageBox.setText(layerController.getActiveStage().getMessage());

        Button backBtn = new Button("Back");
        backBtn.setOnAction(event -> {
            layerController.previousStage();
            layerLbl.setText(layerController.getActiveStage().getName());
            messageBox.setText(layerController.getActiveStage().getMessage());
            canvasView.render();
        });

        Button nextBtn = new Button("Next");
        nextBtn.setOnAction(event -> {
            layerController.nextStage();
            layerLbl.setText(layerController.getActiveStage().getName());
            messageBox.setText(layerController.getActiveStage().getMessage());
            canvasView.render();
        });

        setPrefHeight(40);
        setMinHeight(40);
        setMaxHeight(40);
        setAlignment(Pos.CENTER);
        setSpacing(24);
        setStyle("-fx-background-color: #2b2b2b;");

        getChildren().addAll(backBtn, layerLbl, nextBtn);
    }
}
