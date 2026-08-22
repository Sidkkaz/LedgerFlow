package com.ledgerflow.controller;

import com.ledgerflow.service.LoginService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
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
    private Button Close;
    @FXML
    private CheckBox checkBox;

    private String remember;


    public void Login(ActionEvent event) throws Exception {

        String email = this.emailField.getText();
        String password = this.passwordField.getText();

        if(LoginService.ConferirLogin(email,password)){
            AbrirSistema(event);
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
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

        try{

            remember = Files.readString(path);

        }catch (FileNotFoundException e){
            System.out.println("Não foi possivel fazer a leitura");
        }
    }

    public void Close(){
        Platform.exit();
    }

    public void Minimize(){
        Stage stage = (Stage) login.getScene().getWindow();
        stage.setIconified(true);
    }

    public void Maximize(){
        Stage stage = (Stage) login.getScene().getWindow();
        stage.setMaximized(true);
    }
}
