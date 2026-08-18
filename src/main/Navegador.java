package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class Navegador {

    private final StackPane content;

    public Navegador(StackPane content) {
        this.content = content;
    }

    public void AlterarView(String view){

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/" + view + ".fxml")
            );

            Node node = loader.load();

            content.getChildren().setAll(node);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler view" + view, e);
        }

    }
}
