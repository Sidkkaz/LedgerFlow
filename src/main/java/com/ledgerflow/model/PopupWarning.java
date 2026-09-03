package com.ledgerflow.model;

import javafx.scene.control.Alert;

public class PopupWarning {

    public static void warning(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle(titulo);
        alert.setContentText(mensagem);

        alert.showAndWait();
    }
}
