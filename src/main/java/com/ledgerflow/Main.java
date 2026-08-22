package com.ledgerflow;

import com.ledgerflow.controller.MenuController;
import com.ledgerflow.repository.InitDataBase;
import com.ledgerflow.service.ContaFinanceiraService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        var View = Main.class.getResource("/view/Login.fxml");

        FXMLLoader loader = new FXMLLoader(View);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws Exception {
        InitDataBase db = new InitDataBase();
        launch();
    }

}