module cz.vse.pukp00.jedifx {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.vse.pukp00.jedifx to javafx.fxml;
    exports cz.vse.pukp00.jedifx;
}