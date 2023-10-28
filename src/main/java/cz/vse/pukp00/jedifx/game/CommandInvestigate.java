package cz.vse.pukp00.jedifx.game;

/**
 * Třída implementující příkaz pro průzkum konkrétního předmětu.
 *
 * @author Jan Říha
 * @version ZS-2022, 2022-12-14
 */
public class CommandInvestigate implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandInvestigate(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>prozkoumej</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "prozkoumej";
    }

    /**
     * Metoda vrátí detailní popis vybraného předmětu v inventáři nebo
     * v aktuální oblasti.
     *
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length < 1) {
            return "Tomu nerozumím, musíš mi říct, co mám prozkoumat.";
        }

        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím prozkoumat více věcí současně.";
        }

        String itemName = parameters[0];

        GameWorld world = game.getWorld();
        Area currentArea = world.getCurrentArea();
        Inventory playerInventory = world.getPlayerInventory();

        //# TODO: Příkaz by kromě průzkumu předmětů v aktuální oblasti měl podporovat
        //# i průzkum předmětů v inventáři (postava se může detailně podívat na předměty,
        //# které má kolem sebe, i na předměty, které si přinesla). Po implementaci třídy
        //# Inventory byste kód měli upravit tak, aby předmět k prozkoumání hledal i tam.

        if (!currentArea.containsItem(itemName)) {
            if (playerInventory.containsItem(itemName)) {
                Item itemik = playerInventory.getItem(itemName);
                return "Predmet '"+ itemName + "' v oblasti neni, ale mas ho v inventari. \n" + itemik.getDescription();
            }
            return "Předmět '" + itemName + "' v oblasti není.";
        }
        
        Item item = currentArea.getItem(itemName);

        return item.getDescription();
    }
}
