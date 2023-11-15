package cz.vse.pukp00.jedifx.game;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování třídy ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class AreaTest
{
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    private Item item1;
    private Area area1;

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @AfterEach
    public void tearDown()
    {
    }

    //== VLASTNÍ TESTY =========================================================


    /**
     * Testuje, zda lze spravne vlozit veci a zda se v prostoru nachazeji
     */
    @Test
    public void testVeci()
    {
        Area area1 = new Area("Domcek", "domcek");
        Item item1 = new Item("item1", "itemik1", true);
        Item item2 = new Item("item2", "itemik2", true);
        area1.addItem(item1);
        assertEquals(true, area1.containsItem("item1"));
        assertEquals(false,area1.containsItem("item2"));
    }
}

