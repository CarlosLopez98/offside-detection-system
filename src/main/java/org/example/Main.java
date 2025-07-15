package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.controller.*;
import org.example.model.ImageLayer;
import org.example.view.CanvasView;
import org.example.view.MessageBox;
import org.example.view.StageSelectorPane;

import java.io.File;

public class Main extends Application {
    public double width;
    public double height;

    @Override
    public void start(Stage stage) throws Exception {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        width = screenBounds.getWidth() * 0.8;
        height = screenBounds.getHeight() * 0.8;

        StackPane root = new StackPane();
        BorderPane workspace = new BorderPane();

        // Start screen
        HBox startScreen = new HBox(24);
        startScreen.setAlignment(Pos.CENTER);
        startScreen.setStyle("""
            -fx-background-color: #2e2e2e;
            -fx-text-fill: #f1f1f1;
        """);

        Button newProjectBtn = new Button("New project");
        Button loadProjectBtn = new Button("Load project");

        newProjectBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select an image");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.bmp")
            );
            File selected = fileChooser.showOpenDialog(stage);
            if (selected != null) {
                createWorkspace(workspace, screenBounds, selected);
                root.getChildren().clear();
                root.getChildren().add(workspace);
            }
        });

        startScreen.getChildren().addAll(newProjectBtn, loadProjectBtn);
        root.getChildren().add(startScreen);
        // End start screen

        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource("/styles/button.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Offside analyzer");
        stage.setMaximized(true);

        stage.show();
    }

    public void createWorkspace(BorderPane workspace, Rectangle2D screenBounds, File imageFile) {
        // ImageLayer imageLayer = new ImageLayer("D:/Documents/IdeaProjects/Offside3/src/main/resources/images/offside_test_2.jpeg");
        ImageLayer imageLayer = new ImageLayer(imageFile);
        double initialScale = Math.min(screenBounds.getWidth() / imageLayer.getImage().getWidth(),
                screenBounds.getHeight() / imageLayer.getImage().getHeight()) * 0.8;
        double initialOffsetX = -screenBounds.getWidth() / 2 + imageLayer.getImage().getWidth() * initialScale / 2;
        double initialOffsetY = -screenBounds.getHeight() / 2 + imageLayer.getImage().getHeight() * initialScale / 2;

        StageController layerController = new StageController();
        ColorController colorController = new ColorController(layerController);
        ViewportController viewportController = ViewportController.getInstance();
        viewportController.setScale(initialScale);
        viewportController.setOffsetX(initialOffsetX);
        viewportController.setOffsetY(initialOffsetY);
        viewportController.setImage(imageLayer.getImage());

        ToolController toolController = new ToolController(layerController, viewportController, colorController);
        GridController gridController = new GridController();
        CanvasController canvasController = new CanvasController(layerController, viewportController, toolController);

        MessageBox messageBox = new MessageBox();

        CanvasView canvasView = new CanvasView(width, height, canvasController, viewportController,
                gridController, gridController.getCurrentGrid(), imageLayer, messageBox);

        StageSelectorPane layerSelectorPane = new StageSelectorPane(canvasView, layerController, messageBox);

        workspace.setCenter(canvasView);
        workspace.setBottom(layerSelectorPane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}