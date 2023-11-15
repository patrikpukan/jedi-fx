package cz.vse.pukp00.jedifx;

import cz.vse.pukp00.jedifx.game.Item;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class ListCellItem extends ListCell<Item> {
    @Override
    protected void updateItem(Item item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item.getName());
            ImageView iw = new ImageView(getClass().getResource("predmety/"+item.getName()+".jpg").toExternalForm());
            iw.setFitWidth(150);
            iw.setPreserveRatio(true);
            setGraphic(iw);
        }
    }
}
