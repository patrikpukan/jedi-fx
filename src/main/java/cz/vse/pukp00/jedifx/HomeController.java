package cz.vse.pukp00.jedifx;

import cz.vse.pukp00.jedifx.game.Area;
import cz.vse.pukp00.jedifx.game.Game;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class HomeController {

    @FXML
    private ListView<Area> panelVychodu;
    @FXML
    private Button tlacitkoOdesli;

    @FXML
    private TextArea vystup;

    @FXML
    private TextField vstup;

    private Game game = new Game();

    private ObservableList<Area> seznamVychodu = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        vystup.appendText(game.getPrologue()+"\n\n");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vstup.requestFocus();
            }
        });
        panelVychodu.setItems(seznamVychodu);
        game.getWorld().registruj(ZmenaHry.ZMENA_MISTNOSTI, () -> aktualizujSeznamVychodu());
        game.registruj(ZmenaHry.KONEC_HRY, () -> aktualizujKonecHry());
        aktualizujSeznamVychodu();
    }

    @FXML
    private void aktualizujSeznamVychodu() {
        seznamVychodu.clear();
        seznamVychodu.addAll(game.getWorld().getCurrentArea().getExits());
    }

    private void aktualizujKonecHry() {
        //System.out.print("aktualizuj koneeec");
        if (game.isGameOver()) {
            vystup.appendText(game.getEpilogue());
        }
        vstup.setDisable(game.isGameOver());
        tlacitkoOdesli.setDisable(game.isGameOver());
        panelVychodu.setDisable(game.isGameOver());
    }

    @FXML
    private void odesliVstup(ActionEvent actionEvent) {
        String prikaz = vstup.getText();
        vstup.clear();

        zpracujPrikaz(prikaz);
    }

    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("> " + prikaz +"\n");
        String vysledek = game.process(prikaz);
        vystup.appendText(vysledek+"\n\n");


    }

    public void ukoncitHru(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Chces naozaj ukoncit hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
        //Platform.exit();
    }

    @FXML
    private void klikPanelVychodu(MouseEvent mouseEvent) {
        Area cil =  panelVychodu.getSelectionModel().getSelectedItem();
        if (cil==null) return;
        String prikaz = "jdi " +cil;
       // game.process(prikaz);
        zpracujPrikaz(prikaz);
    }
}
