package cz.vse.pukp00.jedifx.game;

/**
 * Třída implementující příkaz pro polozeni predmetu do mistnosti.
 *
 * @author Patrik Pukan
 * @version ZS-2022, 2023-01-20
 */
public class CommandPut implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandPut(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>poloz</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "poloz";
    }

    /**
     * Metoda polozi predmet do oblasti z inventara, pokial tam je
     * 
     *
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length < 1) {
            return "Tomu nerozumím, musíš mi říct, co mám polozit.";
        }

        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím polozit vic veci soucasne.";
        }

        String itemName = parameters[0];

        GameWorld world = game.getWorld();
        Area currentArea = world.getCurrentArea();
        Inventory playerInventory = world.getPlayerInventory();


        if (!playerInventory.containsItem(itemName)) {
            return "Předmět '" + itemName + "' v inventari není.";
        }
        
        Item item = playerInventory.getItem(itemName);
        currentArea.addItem(item);
        playerInventory.removeItem(itemName);
        return "Dal si do oblasti " + currentArea.getName() + " predmet " + item.getName();
    }
}
