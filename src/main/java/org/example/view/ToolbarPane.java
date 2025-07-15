package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.example.controller.ToolController;
import org.example.tools.ToolType;

public class ToolbarPane extends VBox {
    public ToolbarPane(ToolController toolController) {
        setPadding(new Insets(12));
        setSpacing(8);
        setAlignment(Pos.TOP_LEFT);
        setPickOnBounds(false);

        Button selectBtn = new Button("Select");
//        selectBtn.setMaxWidth(Double.MAX_VALUE);
        selectBtn.setOnAction(event -> toolController.setCurrentTool(ToolType.SELECT));

        Button moveBtn = new Button("Move");
        moveBtn.setOnAction(event -> toolController.setCurrentTool(ToolType.MOVE));

        Button lineBtn = new Button("Line");
        lineBtn.setOnAction(event -> toolController.setCurrentTool(ToolType.LINE));

        Button line2Btn = new Button("Field ref");
        line2Btn.setOnAction(event -> toolController.setCurrentTool(ToolType.FIELD_REFERENCE));

        Button line3Btn = new Button("Floor ref");
        line3Btn.setOnAction(event -> toolController.setCurrentTool(ToolType.FLOOR_REFERENCE));

        Button line4Btn = new Button("Body ref");
        line4Btn.setOnAction(event -> toolController.setCurrentTool(ToolType.BODY_REFERENCE));

        getChildren().addAll(selectBtn, moveBtn, lineBtn, line2Btn, line3Btn, line4Btn);
    }
}
