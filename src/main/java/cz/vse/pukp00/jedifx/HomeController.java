package cz.vse.pukp00.jedifx;

import cz.vse.pukp00.jedifx.game.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class HomeController {

    @FXML
    private Button tlacitkoOdesli;

    @FXML
    private TextArea vystup;

    @FXML
    private TextField vstup;

    private Game game = new Game();

    @FXML
    private void initialize() {
        vystup.appendText(game.getPrologue()+"\n\n");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vstup.requestFocus();
            }
        });
    }
    @FXML
    private void odesliVstup(ActionEvent actionEvent) {
        String prikaz = vstup.getText();
        vystup.appendText("> " +prikaz+"\n");
        String vysledek = game.process(prikaz);
        vystup.appendText(vysledek+"\n\n");
        vstup.clear();

        if (game.isGameOver()) {
            vystup.appendText(game.getEpilogue());
            vstup.setDisable(true);
            tlacitkoOdesli.setDisable(true);

        }
    }

    public void ukoncitHru(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Chces naozaj ukoncit hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
        //Platform.exit();
    }
}
