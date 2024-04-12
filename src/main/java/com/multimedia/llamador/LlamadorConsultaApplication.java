package com.multimedia.llamador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author EnriqueVazquez
 */
public class LlamadorConsultaApplication extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LlamadorConsultaApplication.class.getResource("LlamadorConsultaView.fxml"));
            //Scene scene = new Scene(fxmlLoader.load(), 520, 440);
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setTitle("Llamador de Consultas");
            primaryStage.setScene(scene);
            //primaryStage.setMaximized(true);
            primaryStage.show();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}