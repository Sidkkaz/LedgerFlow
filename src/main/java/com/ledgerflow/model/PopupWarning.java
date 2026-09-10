package com.ledgerflow.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class PopupWarning {

    public static void warning(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle(titulo);
        alert.setContentText(mensagem);

        alert.showAndWait();
    }

    public static boolean confirmation(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle(titulo);
        alert.setContentText(mensagem);

        ButtonType buttonTypeOk = new ButtonType("Sim", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == buttonTypeOk;

    }
}
