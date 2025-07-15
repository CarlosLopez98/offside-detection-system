package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MessageBox extends Label {
    public MessageBox() {
        super();
        setWrapText(true);
        setMaxWidth(300);
        setPadding(new Insets(12));
        setFont(Font.font("System", 12));
        setTextAlignment(TextAlignment.RIGHT);
        setAlignment(Pos.CENTER_RIGHT);
        setStyle("""
            -fx-background-color: #2e2e2e;
            -fx-text-fill: #f1f1f1;
            -fx-background-radius: 10;
            -fx-border-radius: 10;
        """);
    }
}
