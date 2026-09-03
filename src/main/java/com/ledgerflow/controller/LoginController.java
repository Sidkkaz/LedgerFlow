package com.ledgerflow.controller;

import com.ledgerflow.model.PopupWarning;
import com.ledgerflow.service.AuthService;
import com.ledgerflow.service.RememberService;
import com.ledgerflow.service.UsuarioService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button login;
    @FXML
    private CheckBox checkBox;

    private String remember;
    private final AuthService auth = new AuthService(new UsuarioService());


    public void Login(ActionEvent event) throws Exception {

        String email = this.emailField.getText();
        String password = this.passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            PopupWarning.warning("Dados Faltantes","Campo(s) vazio(os), preencha-os corretamente.");
            return;
        }

        if(auth.login(email, password)){
            AbrirSistema(event);
        }else {
            PopupWarning.warning("Login Incorreto", "Email ou Senha incorreto");
            return;
        }

        if(checkBox.isSelected()){
            remember = email;
            RememberService.CreateRemember();
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

    public void initialize() throws IOException {

        remember = RememberService.Remember();

        if(remember != null && !remember.isBlank()){
            emailField.setText(remember);
        }

        emailField.setOnAction(event -> passwordField.requestFocus());
        passwordField.setOnAction(event -> login.requestFocus());

    }

    public void Close(){
        Platform.exit();
    }

    public void Minimize(){
        Stage stage = (Stage) login.getScene().getWindow();
        stage.setIconified(true);
    }

}
