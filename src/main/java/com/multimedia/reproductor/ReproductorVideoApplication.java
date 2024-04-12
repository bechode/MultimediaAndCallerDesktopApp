package com.multimedia.reproductor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;


/**
 *
 * @author EnriqueVazquez
 */
public class ReproductorVideoApplication extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ReproductorVideoApplication.class.getResource("ReproductorVideoView.fxml"));
            //Scene scene = new Scene(fxmlLoader.load(), 520, 440);
            Scene scene = new Scene(fxmlLoader.load());


            /*
            ReproductorVideoController controller = fxmlLoader.getController();
            controller.defineMediaContent(videoFile);
            */

            //StackPane fullScreenPane = new StackPane(fullScreenMediaView);
            //scene.setStyle("-fx-background-color: black");
            //Scene fullScreenScene = new Scene(fullScreenPane);

            //primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setTitle("Reproductor de Video");
            primaryStage.setScene(scene);
            //primaryStage.setMaximized(true);
            primaryStage.setFullScreen(true);
            primaryStage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}