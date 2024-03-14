package com.example.assign1;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HelloApplication extends Application {

    private static final int THUMBNAIL_SIZE = 150;
    private static final int FULL_IMAGE_SIZE = 300;

    private int currentIndex = 0;
    private Image[] images = {
            loadImage("C:/Users/ADMIN/IdeaProjects/assign1/src/main/resources/com/example/assign1/cat.jfif"),
            loadImage("C:/Users/ADMIN/IdeaProjects/assign1/src/main/resources/com/example/assign1/dog.jfif"),
            loadImage("C:/Users/ADMIN/IdeaProjects/assign1/src/main/resources/com/example/assign1/Nails.jfif"),

    };

    private ImageView imageView = new ImageView();

    @Override

    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        FlowPane thumbnailPane = createThumbnailPane();

        Button beforeButton = new Button("before");
        beforeButton.setOnAction(e -> showPreviousImage());

        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> showNextImage());

        BorderPane.setAlignment(beforeButton, Pos.CENTER);
        BorderPane.setAlignment(nextButton, Pos.CENTER_RIGHT);

        BorderPane bottomPane = new BorderPane();
        bottomPane.setLeft(beforeButton);
        bottomPane.setRight(nextButton);

        root.setCenter(imageView);
        root.setTop(thumbnailPane);
        root.setBottom(bottomPane);

        Scene scene = new Scene(root, 400, 200);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private FlowPane createThumbnailPane() {
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);

        for (int i = 0; i < images.length; i++) {
            ImageView thumbnail = new ImageView(images[i]);
            thumbnail.setFitWidth(THUMBNAIL_SIZE);
            thumbnail.setFitHeight(THUMBNAIL_SIZE);
            int index = i;
            thumbnail.setOnMouseClicked(e -> showFullImage(index));
            pane.getChildren().add(thumbnail);
        }

        return pane;
    }

    private void showFullImage(int index) {
        currentIndex = index;
        imageView.setImage(images[index]);
        imageView.setFitWidth(FULL_IMAGE_SIZE);
        imageView.setFitHeight(FULL_IMAGE_SIZE);
    }

    private void showNextImage() {
        currentIndex = (currentIndex + 1) % images.length;
        imageView.setImage(images[currentIndex]);
    }

    private void showPreviousImage() {
        currentIndex = (currentIndex - 1 + images.length) % images.length;
        imageView.setImage(images[currentIndex]);
    }

    private Image loadImage(String path) {
        try {
            return new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load image: " + path);
            alert.showAndWait();
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

