package cz.vse.pukp00.jedifx.game;

/**
 * Třída implementující příkaz pro pohyb mezi herními oblastmi.
 *
 * @author Jan Říha
 * @version ZS-2022, 2022-12-14
 */
public class CommandMove implements ICommand
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public CommandMove(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>jdi</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "jdi";
    }

    /**
     * Metoda se pokusí přesunout hráče do jiné oblasti. Nejprve zkontroluje počet
     * parametrů. Pokud nebyl zadán žádný parametr <i>(tj. neznáme cíl cesty)</i>,
     * nebo bylo zadáno dva a více parametrů <i>(tj. hráč chce jít na více míst
     * současně)</i>, vrátí chybové hlášení. Pokud byl zadán právě jeden parametr,
     * zkontroluje, zda s aktuální oblastí sousedí oblast s daným názvem <i>(tj.
     * zda z aktuální oblasti lze jít do požadovaného cíle)</i>. Pokud ne, vrátí
     * chybové hlášení. Pokud všechny kontroly proběhnou v pořádku, provede přesun
     * hráče do cílové oblasti a vrátí její popis.
     *
     * @param parameters parametry příkazu <i>(očekává se pole s jedním prvkem)</i>
     * @return informace pro hráče, které hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        if (parameters.length < 1) {
            return "Tomu nerozumím, musíš mi říct, kam mám jít.";
        }

        if (parameters.length > 1) {
            return "Tomu nerozumím, neumím jít do více oblastí současně.";
        }

        String targetAreaName = parameters[0];

        GameWorld world = game.getWorld();
        Area currentArea = world.getCurrentArea();

        if (!currentArea.hasExit(targetAreaName)) {
            return "Do oblasti '" + targetAreaName + "' se odsud jít nedá.";
        }
        //System.out.println(currentArea.getName());
        //System.out.println(targetAreaName);
        if (currentArea.getName()=="jazero") {
            //System.out.println("1");
            if (targetAreaName.contains("jaskyna")) {
                //System.out.println("2");
                return "Vchod do jaskyne je vysoko nad vodou, potrebujes tam z niecoho vyliezt.";
            }
        }
        
        if (currentArea.getName()=="chodba") {
            //System.out.println("3");
            if (targetAreaName.contains("radioveza")) {
                //System.out.println("4");
                return "Dvere sa nedaju otvorit bez kluca.";
            }
        }
        Area targetArea = currentArea.getExit(targetAreaName);
        world.setCurrentArea(targetArea);

        return targetArea.getFullDescription();
    }
}
