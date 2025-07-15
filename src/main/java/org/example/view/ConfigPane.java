package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.example.controller.GridController;

public class ConfigPane extends HBox {
    public ConfigPane(CanvasView canvasView, GridController gridController) {
        setPadding(new Insets(12));
        setSpacing(8);
        setAlignment(Pos.TOP_RIGHT);
        setPickOnBounds(false);

        Button axisBtn = new Button("Axis");
        axisBtn.setOnAction(event -> {
            gridController.getCurrentGrid().setViewAxis(!gridController.getCurrentGrid().isViewAxis());
            axisBtn.setText(gridController.getCurrentGrid().isViewAxis() ? "Axis" : "No axis");
            canvasView.render();
        });

        Button gridBtn = new Button("Grid: " + gridController.getCurrentGrid().getName());
        gridBtn.setOnAction(event -> {
            gridController.changeGrid();
            gridBtn.setText("Grid: " + gridController.getCurrentGrid().getName());
            canvasView.render();
        });

        getChildren().addAll(axisBtn, gridBtn);
    }
}
