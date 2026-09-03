package com.ledgerflow.controller;

import com.ledgerflow.model.PopupWarning;
import com.ledgerflow.model.Sessao;
import com.ledgerflow.service.AuthService;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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

        if(auth.Login(email, password)){
            var session = new Sessao(auth.getUserAtual());
            AbrirSistema(event);
        }else {
            PopupWarning.warning("Login Incorreto", "Email ou Senha incorreto");
            return;
        }

        if(checkBox.isSelected()){
            remember = email;
            CreateRemember();
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

        Remember();
        if(remember != null && !remember.isBlank()){
            emailField.setText(remember);
        }

        emailField.setOnAction(event -> passwordField.requestFocus());
        passwordField.setOnAction(event -> login.requestFocus());

        if(!emailField.getText().isEmpty()){
            passwordField.requestFocus();
        }

    }

    public void CreateRemember() throws IOException {
        Path path = Paths.get("./remember.txt");

        if(Files.notExists(path)){
            Files.createFile(path);
        }

        Files.write(path, remember.getBytes());

    }

    public void Remember() throws IOException {
        Path path = Paths.get("./remember.txt");

        if(Files.exists(path)) {
            remember = Files.readString(path);
        }
    }

    public void Close(){
        Platform.exit();
    }

    public void Minimize(){
        Stage stage = (Stage) login.getScene().getWindow();
        stage.setIconified(true);
    }

}
