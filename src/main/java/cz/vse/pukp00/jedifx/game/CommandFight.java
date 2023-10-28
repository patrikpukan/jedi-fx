package cz.vse.pukp00.jedifx.game;
import java.util.*;

/**
 * Třída implementující příkaz pro boj.
 *
 * @author Patrik Pukan
 * @version ZS-2022, 2022-12-30
 */
public class CommandFight implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandFight(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>boj</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "boj";
    }

    /**
     * Metoda zacne boj, nasledne vrati vysledok
     *
     * @param parameters parametry příkazu <i>(očekává se meno protivnika)</i>
     * @return informace o vysledku boja
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length < 1) {
            return "Tomu nerozumím, musíš mi říct, s kym mam bojovat.";
        }

        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím robit viac bojov naraz.";
        }

        String NameOfEnemy = parameters[0];

        GameWorld world = game.getWorld();
        Area currentArea = world.getCurrentArea();
        Inventory playerInventory = world.getPlayerInventory();
        Character currentCharacter = currentArea.getCharacter();

        if (!currentArea.hasCharacter())
        {
            return "Neni s kym bojovat";
        }
        if (!currentCharacter.isCharacterAnEnemy()) {
            return "Charakter nie je nepriatel";
        }
        if (!playerInventory.containsItem("blaster")) {
            game.setGameOver(true);
            return "Nemas zbran a prehravas suboj.";
        }
        
        if (playerInventory.containsItem("blaster")) {
            currentArea.removeCharacter(currentCharacter);
            currentCharacter=null;
            Item kluc = currentArea.getItem("kluc");
            currentArea.removeItem("kluc");
            kluc.setMoveable(true);
            playerInventory.addItem(kluc);
            return "Vyhral si suboj s " + NameOfEnemy + " a zobral si wookiemu kluc od vychodu.";
        }
        return "problem";
    }
}
