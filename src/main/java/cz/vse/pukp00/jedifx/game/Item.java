package cz.vse.pukp00.jedifx.game;

/**
 * Třída představuje predmet ve scénáři
 * hry. Každy predmet má název, který ho jednoznačně identifikuje. 
 *
 * @author Patrik Pukan
 * @version ZS-2022, 2022-12-30
 */
public class Item implements Comparable<Item>
{
    private String name;
    private String description;
    private boolean moveable;

    /**
     * Konstruktor třídy, vytvoří predmet se zadaným názvem a popisem.
     *
     * @param name název predmetu <i>(jednoznačný identifikátor, musí se jednat o text bez mezer)</i>
     * @param description podrobnější popis predmetu
     */
    public Item(String name, String description)
    {
        this(name, description, true);
    }
    
    /**
     * Konstruktor třídy, vytvoří predmet se zadaným názvem a popisem a ci je pohyblivy.
     *
     * @param name název predmetu <i>(jednoznačný identifikátor, musí se jednat o text bez mezer)</i>
     * @param description podrobnější popis oblasti
     * @param moveable pohyblivost predmetu
     */
    public Item(String name, String description, boolean moveable)
    {
        this.name = name;
        this.description = description;
        this.moveable = moveable;
    }
    
    /**
     * Metoda vracia meno predmetu.
     *
     * @return meno predmetu
     */
    public String getName()
    {
        return name;
    }

    /**
     * Metoda vracia popis predmetu.
     *
     * @return popis predmetu
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Metoda nastavi popis predmetu
     *
     * @param description popis predmetu
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    /**
     * Metoda zkontroluje, zda je predmet pohyblivy
     *
     * @return {@code true}, pokud je predmet pohyblivy; inak {@code false}
     */
    public boolean isMoveable()
    {
        return moveable;
    }
    
    /**
     * Metoda nastavuje pohyblivost predmetu.
     *
     * @param moveable bud je predmet pohyblivy alebo nie je
     */
    public void setMoveable(boolean moveable)
    {
        this.moveable = moveable;
    }

    /**
     * Metoda porovnává dvě predmety <i>(objekty)</i>. Predmety jsou shodné,
     * pokud mají stejný název <i>(atribut {@link #name})</i>.
     * <p>
     * Podrobnější popis metody najdete v dokumentaci třídy {@linkplain Object}.
     *
     * @param o objekt, který bude porovnán s aktuálním
     * @return {@code true}, pokud mají obě predmety stejný název; jinak {@code false}
     *
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(final Object o)
    {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o instanceof Item) {
            Item item = (Item) o;

            return name.equals(item.getName());
        }

        return false;
    }

    /**
     * Metoda vrací číselný identifikátor instance, který se používá
     * pro optimalizaci ukládání v dynamických datových strukturách
     * <i>(např.&nbsp;{@linkplain HashSet})</i>. Při překrytí metody
     * {@link #equals(Object) equals} je vždy nutné překrýt i tuto
     * metodu.
     * <p>
     * Podrobnější popis pravidel pro implementaci metody najdete
     * v dokumentaci třídy {@linkplain Object}.
     *
     * @return číselný identifikátor instance
     *
     * @see Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return name.hashCode();
    }
    
    /**
     * Metoda porovná predmet s inym predmetom předané v parametru abecedně
     * dle jejich názvů a vrátí kladné číslo, nulu, nebo záporné číslo
     * v závislosti na tom, zda je název tohoto predmetu větší, stejný, nebo
     * menší než název druheho predmetu.
     * <p>
     *
     * @param item predmet, jehoz název bude porovnán s názvem tohoto predmetu
     * @return kladné číslo, nula, nebo záporné číslo v závislosti na porovnání názvů predmetov
     */
    @Override
    public int compareTo(Item item)
    {
        return name.compareTo(item.getName());
    }
}
