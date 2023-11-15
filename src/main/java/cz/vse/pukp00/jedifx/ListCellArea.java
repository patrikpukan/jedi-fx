package cz.vse.pukp00.jedifx;

import cz.vse.pukp00.jedifx.game.Area;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

/**
 * Trieda zobrazuje graficke oblasti
 *
 *
 */
public class ListCellArea extends ListCell<Area> {
    @Override
    protected void updateItem(Area area, boolean empty) {
        super.updateItem(area, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(area.getName());
            ImageView iw = new ImageView(getClass().getResource("prostory/"+area.getName()+".jpg").toExternalForm());
            iw.setFitWidth(150);
            iw.setPreserveRatio(true);
            setGraphic(iw);
        }
    }
}
