package com.example.javafxproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {

    private Label randomLabel;
    private Label fileContentLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple App");

        randomLabel = new Label("Click the button to generate a random number");
        fileContentLabel = new Label("No file selected");

        Button generateButton = new Button("Generate Random Number");
        generateButton.setOnAction(event -> generateRandomNumber());

        Button openButton = new Button("Open File");
        openButton.setOnAction(event -> openFile());

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(randomLabel, generateButton, fileContentLabel, openButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generateRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(100);

        randomLabel.setText("Random Number: " + randomNumber);
    }

    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            String fileContent = readFileContent(file);
            fileContentLabel.setText(fileContent);
        }
    }

    private String readFileContent(File file) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
