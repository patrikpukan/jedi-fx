package cz.vse.pukp00.jedifx.game;

/**
 * Třída implementující příkaz pro zobrazení nápovědy ke hře.
 *
 * @author Jan Říha
 * @version ZS-2022, 2022-12-14
 */
public class CommandHelp implements ICommand
{
    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>napoveda</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "napoveda";
    }

    /**
     * Metoda vrací obecnou nápovědu ke hře. Nyní pouze vypisuje vcelku
     * primitivní zprávu o herním příběhu.
     *
     * @param parameters parametry příkazu <i>(aktuálně se ignorují)</i>
     * @return nápověda, kterou hra vypíše na konzoli
     */
    @Override
    public String execute(String[] parameters)
    {
        return "Tvoja uloha je zachranit sa, k tomu musis prist do radioveze, nebude to vsak jednoduche.\n\n"
                + "Pre info o oblasti pouzi prikaz 'rozhledni_se', pre info o charaktere pouzi 'kontakt (menoCharakteru)', \n" +
                "pre inventar pouzi 'batoh', pre info o predmete pouzi 'prozkoumej (menoPredmetu)'.\n"
                + "Dalsie prikazy: 'jdi (menoOblasti)', 'poloz (menoPredmetu)', 'vezmi (menoPredmetu)', 'boj (menoCharakteru)'. \n" 
                + "Pre koniec hry pouzi prikaz 'konec'";
    }
}
