package cz.vse.pukp00.jedifx.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování třídy ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class GameTest
{
    private Game game;

    //== Priprava a uklid pripravku ================================================

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @BeforeEach
    public void setUp()
    {
        game = new Game();
    }

    //== Vlastni testovaci metody ==================================================

    /**
     * Testuje jednu z moznosti prubehu hry.
     */
    @Test
    public void testUtekBezBoja() {
        game.process("jdi strom");
        game.process("jdi domcek");
        game.process("jdi strom");
        game.process("jdi jazero");
        game.process("pouzi cln");
        game.process("jdi chodba");
        assertEquals(false,game.isGameOver());
    }

    /**
     * Testuje jednu z moznosti prubehu hry.
     */
    @Test
    public void testVyhra() {
        game.process("vezmi lampas");
        game.process("jdi strom");
        game.process("jdi domcek");
        game.process("poloz lampas");
        game.process("pouzi lampas");
        game.process("jdi strom");
        game.process("jdi dzungla");
        game.process("jdi cistinka");
        game.process("jdi brloh");
        game.process("boj karkash");
        game.process("jdi cistinka");
        game.process("jdi dzungla");
        game.process("jdi strom");
        game.process("jdi jazero");
        game.process("pouzi cln");
        game.process("jdi chodba");
        game.process("poloz kluc");
        game.process("pouzi kluc");
        game.process("vezmi transmiter");
        assertEquals(true, game.isGameOver());
    }

}

