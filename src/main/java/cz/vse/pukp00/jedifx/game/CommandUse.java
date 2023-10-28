package cz.vse.pukp00.jedifx.game;
import java.util.*;

/**
 * Třída implementující příkaz pro pouziti predmetu.
 *
 * @author Patrik Pukan
 * @version ZS-2022, 2022-12-30
 */
public class CommandUse implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandUse(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>pouzi</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "pouzi";
    }

    /**
     * Metoda na pouzitie predmetu.
     *
     * @param parameters parametry příkazu <i>(očekává se nazov predmetu)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length < 1) {
            return "Tomu nerozumím, musíš mi říct, co mám pouzit.";
        }

        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím pouzit vic veci soucasne.";
        }

        String itemName = parameters[0];

        GameWorld world = game.getWorld();
        Area currentArea = world.getCurrentArea();
        Inventory playerInventory = world.getPlayerInventory();


        if (playerInventory.containsItem(itemName)) {
            return "Předmět '" + itemName + "' je v inventari, musis ho polozit do oblasti.";
        }
        if (!currentArea.containsItem(itemName)) {
            return "Předmět '" + itemName + "' neni v oblasti.";
        }
        
        if (currentArea.containsItem(itemName)) {
            if (itemName.contains("cln")) {
               // System.out.println("1");
                Area targetA = currentArea.getExit("jaskyna");
                currentArea=targetA;
                world.setCurrentArea(targetA);
                return "Vyuzil si lodku na cestu do jaskyne";
            }
        }
        
        if (currentArea.containsItem(itemName)) {
            if (currentArea.getName().contains("chodba"))
            {
            if (itemName.contains("kluc")) {
                //System.out.println("2");
                Area targetA = currentArea.getExit("radioveza");
                currentArea=targetA;
                world.setCurrentArea(targetA);
                    return "Klucom si otvoril dvere a dostal sa do cielovej lokacie";
                }
            }
        }
        
        if (currentArea.containsItem(itemName)) {
            if (itemName.contains("lampas")) {
               // System.out.println("3");
                Item blaster = currentArea.getItem("blaster");
                currentArea.removeItem("blaster");
                blaster.setMoveable(true);
                playerInventory.addItem(blaster);
                return "Meeko vyuzije lampas a necha ti jeho blaster";
            }
        }
        
        Item item = playerInventory.getItem(itemName);
        //currentArea.addItem(item);
       //playerInventory.removeItem(itemName);
        return "Problem " + item.getName();
    }
}
