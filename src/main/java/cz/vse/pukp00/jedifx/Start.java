package cz.vse.pukp00.jedifx;

import cz.vse.pukp00.jedifx.game.Game;
import cz.vse.pukp00.jedifx.ui.TextUI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Spouštěcí třída aplikace.
 *
 * @author Jan Říha
 * @version ZS-2022, 2022-12-14
 */
public class Start extends Application
{
    /**
     * Konstruktor třídy {@link Start} je privátní, třída obsahuje pouze statickou
     * metodu {@link #main(String[]) main} pro spuštění aplikace, není třeba vytvářet
     * její instance.
     */
   /* private Start()
    {
    } */

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Adventura");
    }

    /**
     * Spouštěcí metoda aplikace. Vytvoří instanci hry, uživatelského rozhraní a zahájí hru.
     *
     * @param args parametry aplikace z příkazové řádky, aktuálně se nijak nevyužívají
     */
    public static void main(String[] args)
    {
        if (args.length>0 && args[0].equals("text")) {
            Game game = new Game();
            TextUI textUI = new TextUI(game);
            textUI.play();
            Platform.exit();
        } else {
            launch();
        }
    }
}
