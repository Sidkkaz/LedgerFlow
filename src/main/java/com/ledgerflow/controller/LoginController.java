package com.ledgerflow.controller;

import com.ledgerflow.service.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;

    public void Login(ActionEvent event) throws Exception {

        String email = this.email.getText();
        String password = this.password.getText();

        if(LoginService.ConferirLogin(email,password)){
            AbrirSistema(event);
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        }
    }

    public void AbrirSistema(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/Main.fxml")
        );

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Ledgerflow");
        stage.show();
    }
}
