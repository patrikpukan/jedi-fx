package cz.vse.pukp00.jedifx.game;
import java.util.*;
/**
 * Třída představující mapu oblastí herního světa. V datovém atributu
 * {@link #currentArea} uchovává odkaz na aktuální oblast, ve které
 * se hráč právě nachází. Z aktuální oblasti je možné se prostřednictvím
 * jejích sousedů dostat ke všem přístupným oblastem ve hře.
 * <p>
 * Veškeré informace o stavu hry <i>(mapa oblastí, inventář, vlastnosti
 * hlavní postavy, informace o plnění úkolů apod.)</i> by měly být uložené
 * zde v podobě datových atributů.
 *
 * @author Jan Říha
 * @version ZS-2022, 2022-12-14
 */
public class GameWorld
{
    private Area currentArea;
    private Inventory playerInventory;

    /**
     * Konstruktor třídy, vytvoří jednotlivé oblasti, propojí je pomocí východů a vloží do nich předměty.
     */
    public GameWorld()
    {
        
        Area lod = new Area("lod", "Tu pristala lod a zacina tu hra.");
        Area strom = new Area("strom", "Toto je strom, od ktoreho vedie viacero ciest.");
        Area domcek = new Area("domcek", "Toto je maly domcek, byva tu maly wookie.");
        Area dzungla = new Area("dzungla", "Toto je dzungla, je tu tmavo.");
        Area cistinka = new Area("cistinka", "Toto je cistinka, vidis pri nej tmavy brloh.");
        Area brloh = new Area("brloh", "Toto je brloh, byva tu nebezpecny zly wookie.");
        Area jazero = new Area("jazero", "Toto je jazero, je tu maly cln a na jeho konci vidis jaskynu.");
        Area jaskyna = new Area("jaskyna", "Toto je jaskyna, mozes nou prejst popri jazere alebo do tajnej chodby.");
        Area chodba = new Area("chodba", "Toto je chodba s dverami, ktore sa daju odomknut klucom.");
        Area radioveza = new Area("radioveza", "Toto je radiova veza, mozes odtialto zavolat pomoc.");

        Item blaster = new Item("blaster", "Blaster, ktory striela.", false);
        Item stul = new Item("stul", "Těžký dubový stůl.", false);
        Item lampas = new Item("lampas", "rucny lampas.");
        Item cln = new Item("cln", "cln s padlami.", false);
        Item exitKey = new Item("kluc", "kluc k radiovezi",false);
        Item transmiter = new Item("transmiter", "transmiter vo vezi, vezmi si ho a vyhrajes");
        
        Character meeko = new Character("Meeko", "Maly wookie zijuci osamote v domceku.", false);
        Character karkash = new Character("Karkash", "Wookie, ktory ta chce zjest.", true);

        lod.addExit(strom);
        lod.addItem(lampas);
        
        strom.addExit(lod);
        strom.addExit(jazero);
        strom.addExit(dzungla);
        strom.addExit(domcek);
        
        domcek.addExit(strom);
        domcek.addItem(blaster);
        domcek.addItem(stul);
        domcek.addCharacter(meeko);

        dzungla.addExit(strom);
        dzungla.addExit(cistinka);

        cistinka.addExit(dzungla);
        cistinka.addExit(brloh);
        
        brloh.addExit(cistinka);
        brloh.addItem(exitKey);
        brloh.addCharacter(karkash);

        jazero.addExit(strom);
        jazero.addExit(jaskyna);
        jazero.addItem(cln);
        
        jaskyna.addExit(chodba);
        jaskyna.addExit(jazero);
        
        chodba.addExit(jaskyna);
        chodba.addExit(radioveza);
        
        radioveza.addExit(chodba);
        radioveza.addItem(transmiter);

        currentArea = lod;
        playerInventory = new Inventory("playerInventory");
        
    }

    /**
     * Metoda vrací odkaz na aktuální oblast, ve které se hráč právě nachází.
     *
     * @return aktuální oblast
     */
    public Area getCurrentArea()
    {
        return currentArea;
    }
    
    /**
     * Metoda vrací odkaz na inventar hraca.
     *
     * @return inventar hraca
     */
     public Inventory getPlayerInventory()
    {
        return playerInventory;
    }

    /**
     * Metoda nastaví aktuální oblast. Používá ji příkaz {@link CommandMove}
     * při přechodu mezi oblastmi.
     *
     * @param currentArea oblast, která bude nastavena jako aktuální
     */
    public void setCurrentArea(Area currentArea)
    {
        this.currentArea = currentArea;
    }
    
    /**
     * Metoda vrací odkaz ziadany predmet.
     *
     * @param item predmet, ktory chceme vratit
     * @return ziadany predmet
     */
    public Item getItem(Item item)
    {
        return item;
    }
    /**
     * Metoda vrací informaci, zda hráč vyhrál <i>(zda jsou splněné všechny
     * úkoly a podmínky nutné pro výhru)</i>.
     *
     * @return {@code true}, pokud hráč vyhrál; jinak {@code false}
     */
    public boolean isVictorious()
    {
        return currentArea.getName().equals("radioveza")
                     && playerInventory.containsItem("transmiter");
                    // && ...
    }
}
