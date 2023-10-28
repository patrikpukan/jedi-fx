package cz.vse.pukp00.jedifx.game;
import java.util.*;

/**
 * Třída implementující příkaz pro průzkum inventara.
 *
 * @author Patrik Pukan
 * @version ZS-2022, 2023-01-19
 */
public class CommandSearchInventory implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandSearchInventory(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>batoh</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "batoh";
    }

    /**
     * Metoda vrátí informace o inventari.
     *
     * @param parameters parametry příkazu <i>(očekává se prázdné pole)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length > 0) {
            return "Tomu nerozumím, umim se kouknout pouze do vlastniho inventare";
        }

        GameWorld world = game.getWorld();
        Inventory playerInventory = world.getPlayerInventory();

        return playerInventory.getInventoryContent() + "\n max kapacita: 5 predmetov." ;
    }
}
