package cz.vse.pukp00.jedifx.game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*******************************************************************************
 * Testovací třída KabelkaTest slouží ke komplexnímu otestování třídy ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class InventoryTest {
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @BeforeEach
    public void setUp() {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @AfterEach
    public void tearDown() {
    }

    //== VLASTNÍ TESTY =========================================================

    /**
     * testuje, zda jde věc vložit do kabelky a jestli ji pak obsahuje
     */
    @Test
    public void testVlozDoInventara() {
        Inventory inventory = new Inventory("playerInventory");
        Item item1 = new Item("auticko","male auticko hracka", true);
        Item item2 = new Item("auto","velke auto", false);
        inventory.addItem(item1);
        inventory.addItem(item2);
        assertEquals(true, inventory.containsItem("auticko"));

    }
}
