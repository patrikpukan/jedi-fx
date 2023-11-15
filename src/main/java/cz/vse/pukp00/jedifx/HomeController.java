package cz.vse.pukp00.jedifx;

import cz.vse.pukp00.jedifx.game.Area;
import cz.vse.pukp00.jedifx.game.Game;
import cz.vse.pukp00.jedifx.game.Item;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Optional;
import java.util.*;

public class HomeController {

    @FXML
    private ImageView hrac;
    @FXML
    private ListView<Area> panelVychodu;
    @FXML
    private ListView<Item> panelPredmetovInventar;
    @FXML
    private ListView<Item> panelPredmetovArea;

    @FXML
    private Button tlacitkoOdesli;

    @FXML
    private TextArea vystup;

    @FXML
    private TextField vstup;

    private Game game = new Game();

    private ObservableList<Area> seznamVychodu = FXCollections.observableArrayList();

    private ObservableList<Item> seznamPredmetovInventar = FXCollections.observableArrayList();
    private ObservableList<Item> seznamPredmetovArea = FXCollections.observableArrayList();


    private Map<String, Point2D> souradniceProstoru = new HashMap<>();

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
        panelPredmetovInventar.setItems(seznamPredmetovInventar);
        panelPredmetovArea.setItems(seznamPredmetovArea);
        game.getWorld().registruj(ZmenaHry.ZMENA_MISTNOSTI, () -> {
            aktualizujSeznamVychodu();
            aktualizujPolohuHrace();
            aktualizujSeznamPredmetovInventar();
            aktualizujSeznamPredmetovArea();
        });
        game.registruj(ZmenaHry.KONEC_HRY, () -> aktualizujKonecHry());
        aktualizujSeznamVychodu();
        aktualizujSeznamPredmetovInventar();
        aktualizujSeznamPredmetovArea();
        vlozSouradnice();
        panelVychodu.setCellFactory(param -> new ListCellArea());
        panelPredmetovInventar.setCellFactory(param -> new ListCellItem());
        panelPredmetovArea.setCellFactory(param -> new ListCellItem());


    }

    private void vlozSouradnice() {
        souradniceProstoru.put("lod", new Point2D(6,105));
        souradniceProstoru.put("strom", new Point2D(88,105));
        souradniceProstoru.put("domcek", new Point2D(160,105));
        souradniceProstoru.put("dzungla", new Point2D(92,195));
        souradniceProstoru.put("cistinka", new Point2D(190,200));
        souradniceProstoru.put("brloh", new Point2D(310,200));
        souradniceProstoru.put("jazero", new Point2D(90,25));
        souradniceProstoru.put("jaskyna", new Point2D(180,25));
        souradniceProstoru.put("chodba", new Point2D(280,25));
        souradniceProstoru.put("radioveza", new Point2D(380,30));
    }

    @FXML
    private void aktualizujSeznamVychodu() {
        seznamVychodu.clear();
        seznamVychodu.addAll(game.getWorld().getCurrentArea().getExits());
    }

    @FXML
    private void aktualizujSeznamPredmetovInventar() {
      //  System.out.print("aktualizuj PredmetyvInv");
        seznamPredmetovInventar.clear();
        seznamPredmetovInventar.addAll(game.getWorld().getPlayerInventory().getItems());
       // System.out.println(seznamPredmetovInventar);
    }

    @FXML
    private void aktualizujSeznamPredmetovArea() {
      //  System.out.print("aktualizuj PredmetyvArea");

        seznamPredmetovArea.clear();
        seznamPredmetovArea.addAll(game.getWorld().getCurrentArea().getItemsFX());
        //System.out.println(seznamPredmetovInventar);

    }

    private void aktualizujPolohuHrace(){
        String menoProstoru = game.getWorld().getCurrentArea().getName();
        hrac.setLayoutX(souradniceProstoru.get(menoProstoru).getX());
        hrac.setLayoutY(souradniceProstoru.get(menoProstoru).getY());
    }

    private void aktualizujKonecHry() {
        //System.out.print("aktualizuj koneeec");
        if (game.isGameOver()) {
            vystup.appendText(game.getEpilogue());
        }
        vstup.setDisable(game.isGameOver());
        tlacitkoOdesli.setDisable(game.isGameOver());
        panelVychodu.setDisable(game.isGameOver());
        panelPredmetovInventar.setDisable(game.isGameOver());
        panelPredmetovArea.setDisable(game.isGameOver());

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
        aktualizujPolohuHrace();
        aktualizujSeznamVychodu();
        aktualizujSeznamPredmetovInventar();
        aktualizujSeznamPredmetovArea();

        if (prikaz.equals("newGame")) {
            vystup.clear();
            game = new Game();
            vystup.appendText("Nova hra zacina." + "\n");
            vystup.appendText(game.getPrologue());
            panelVychodu.getItems().clear();
            game.getWorld().registruj(ZmenaHry.ZACATEK_HRY, () -> {
                aktualizujPolohuHrace();
                aktualizujSeznamVychodu();
                aktualizujSeznamPredmetovInventar();
                aktualizujSeznamPredmetovArea();
            } );
            initialize();
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

    @FXML
    private void klikPanelVychodu(MouseEvent mouseEvent) {
        Area cil =  panelVychodu.getSelectionModel().getSelectedItem();
        if (cil==null) return;
        String prikaz = "jdi " +cil.getName();
       // game.process(prikaz);
        zpracujPrikaz(prikaz);
    }

    @FXML
    private void napovedaKlik(ActionEvent actionEvent) {
        Stage napovedaStage = new Stage();
        WebView wv = new WebView();
        Scene napovedaScena = new Scene(wv);
        napovedaStage.setScene(napovedaScena);
        napovedaStage.show();
        wv.getEngine().load(getClass().getResource("napoveda.html").toExternalForm());

    }

    public void klikNewGame() {
        zpracujPrikaz("newGame");
    }
    public void klikPanelPredmetovInventar(MouseEvent mouseEvent) {
        Item cil =  panelPredmetovInventar.getSelectionModel().getSelectedItem();
        if (cil==null) return;
        String prikaz = "poloz " +cil.getName();
        // game.process(prikaz);
        zpracujPrikaz(prikaz);
        aktualizujSeznamPredmetovInventar();
        aktualizujSeznamPredmetovArea();
    }

    public void klikPanelPredmetovArea(MouseEvent mouseEvent) {
        Item cil =  panelPredmetovArea.getSelectionModel().getSelectedItem();
        if (cil==null) return;
        String prikaz = "vezmi " +cil.getName();
        // game.process(prikaz);
        zpracujPrikaz(prikaz);
        aktualizujSeznamPredmetovInventar();
        aktualizujSeznamPredmetovArea();
    }
}
