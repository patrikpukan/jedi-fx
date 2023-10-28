package cz.vse.pukp00.jedifx.game;

/**
 * Třída implementující příkaz pro průzkum charakteru v aktualni lokaci.
 *
 * @author Patrik Pukan
 * @version ZS-2022, 2023-01-15
 */
public class CommandContact implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandContact(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>kontakt</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "kontakt";
    }

    /**
     * Metoda vrati informaciu o charaktere v oblasti.
     *
     * @param parameters parametry příkazu <i>(ocakava sa prazdne pole)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length > 0) {
            return "Tomu nerozumím, poskytujem info o charaktere v danom priestore";
        }

        GameWorld world = game.getWorld();
        Area currentArea = world.getCurrentArea();
        if (currentArea.hasCharacter()){
        Character currentCharacter = currentArea.getCharacter();
        return "Charakter sa vola " + currentCharacter.getName() + ". Dalsie info o nom: \n" + currentCharacter.getDescription();
    }
    return "V oblasti nikto nie je";
    }
}
