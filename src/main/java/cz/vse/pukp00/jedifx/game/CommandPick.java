package cz.vse.pukp00.jedifx.game;

/**
 * Třída implementující příkaz pro sbírání předmětů.
 *
 * @author Jan Říha
 * @version ZS-2022, 2022-12-14
 */
public class CommandPick implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandPick(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>vezmi</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "vezmi";
    }

    /**
     * Metoda se pokusí sebrat předmět z aktuální oblasti a uložit ho do hráčova
     * inventáře. Nejprve zkontroluje počet parametrů. Pokud nebyl zadán žádný
     * parametr <i>(tj. neznáme požadovaný předmět)</i>, nebo bylo zadáno dva a
     * více parametrů <i>(tj. hráč chce sebrat více předmětů současně)</i>, vrátí
     * chybové hlášení. Pokud byl zadán právě jeden parametr, zkontroluje, zda
     * v aktuální oblasti je předmět s daným názvem, zda je přenositelný a zda
     * na něj hráč má v inventáři místo <i>(tj. zda ho lze sebrat)</i>. Pokud ne,
     * vrátí chybové hlášení. Pokud všechny kontroly proběhnou v pořádku, provede
     * přesun předmětu z oblasti do inventáře a vrátí informaci o sebrání předmětu.
     *
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length < 1) {
            return "Tomu nerozumím, musíš mi říct, co mám sebrat.";
        }

        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím sebrat více věcí současně.";  //# TODO: Toto by hra samozřejmě umět mohla, stačí
        }                                                                //# následující kód 'obalit' cyklem a provést ho pro
                                                                         //# každý parametr příkazu (námět na možné rozšíření)
        String itemName = parameters[0];

        GameWorld world = game.getWorld();
        Area currentArea = world.getCurrentArea();
        Inventory playerInventory = world.getPlayerInventory();
        

        if (!currentArea.containsItem(itemName)) {
            return "Předmět '" + itemName + "' tady není.";
        }

        Item item = currentArea.getItem(itemName);

        if (!item.isMoveable()) {
            return "Předmět '" + itemName + "' fakt neuneseš.";
        }

        if (playerInventory.getSize()==5)
        {
            return "Inventar je plny";
        }

        /* Inventory inventory = world.getInventory();
         *
         * if (!inventory.addItem(item)) {
         *     return "Předmět '" + itemName + "' se Ti nevejde do inventáře.";
         * } */
        playerInventory.addItem(item);
        currentArea.removeItem(itemName);

        return "Sebral(a) jsi předmět '" + itemName + "' a uložil(a) ho do inventáře.";
    }
}
