package com.ledgerflow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        var View = Main.class.getResource("/view/Login.fxml");

        FXMLLoader loader = new FXMLLoader(View);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}