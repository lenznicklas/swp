package com.example.text4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class HelloApplication extends Application {

    private TextArea textArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Text Editor");

        textArea = new TextArea();

        Button saveButton = new Button("Save");
        saveButton.setOnAction(new SaveButtonHandler());

        Button openButton = new Button("Open");
        openButton.setOnAction(new OpenButtonHandler());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(textArea, saveButton, openButton);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class SaveButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();

            // Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            // Show save file dialog
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                saveTextToFile(textArea.getText(), file);
            }
        }

        private void saveTextToFile(String content, File file) {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class OpenButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();

            // Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            // Show open file dialog
            File file = fileChooser.showOpenDialog(null);

            if (file != null) {
                openFile(file);
            }
        }

        private void openFile(File file) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                textArea.setText(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
