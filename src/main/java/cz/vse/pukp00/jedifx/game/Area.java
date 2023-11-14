package cz.vse.pukp00.jedifx.game;

import java.util.*;

/**
 * Třída představuje oblast <i>(lokaci, místo, místnost, prostor)</i> ve scénáři
 * hry. Každá oblast má název, který ji jednoznačně identifikuje. Může mít sousední
 * oblasti, do kterých z ní lze odejít. Odkazy na všechny sousední oblasti jsou
 * uložené v množině. Oblast také může obsahovat předměty. Odkazy na všechny
 * předměty v oblasti jsou uložené v mapě.
 *
 * @author Jan Říha
 * @version ZS-2022, 2022-12-14
 */
public class Area implements Comparable<Area>
{
    private String name;
    private String description;

    private Set<Area> exits;
    private Map<String, Item> items;
    private boolean hasCharacter;
    //private boolean hasEnemy;
    private Character currentCharacter;

    /**
     * Konstruktor třídy, vytvoří oblast se zadaným názvem a popisem.
     *
     * @param name název oblasti <i>(jednoznačný identifikátor, musí se jednat o text bez mezer)</i>
     * @param description podrobnější popis oblasti
     */
    public Area(String name, String description)
    {
        this.name = name;
        this.description = description;

        exits = new TreeSet<>();
        items = new TreeMap<>();
    }

    /**
     * Metoda vrací název oblasti, který byl zadán při vytváření instance jako
     * parametr konstruktoru. Jedná se o jednoznačný identifikátor oblasti
     * <i>(ve hře nemůže existovat více oblastí se stejným názvem)</i>. Aby
     * hra správně fungovala, název oblasti nesmí obsahovat mezery, v případě
     * potřeby můžete více slov oddělit pomlčkami, použít camel-case apod.
     *
     * @return název oblasti
     */
    public String getName()
    {
        return name;
    }

    /**
     * Metoda vrací popis oblasti.
     *
     * @return popis oblasti
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Metoda nastaví popis oblasti.
     *
     * @param description nový popis oblasti
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Metoda vrací kompletní informace o oblasti. Výsledek volání obsahuje:
     * <ul>
     *     <li>název oblasti</li>
     *     <li>popis oblasti</li>
     *     <li>seznam sousedních oblastí, do kterých lze odejít</li>
     *     <li>seznam předmětů v oblasti</li>
     * </ul>
     *
     * @return kompletní informace o oblasti
     */
    public String getFullDescription()
    {
        String exitNames = "Východy:";
        for (Area exit : exits) {
            exitNames += " " + exit.getName();
        }

        String itemNames = "Předměty:";
        for (String itemName : items.keySet()) {
            itemNames += " " + itemName;
        }

        return "Jsi v oblasti '" + name + "'.\n"
                + description + "\n\n"
                + exitNames + "\n"
                + itemNames;
    }

    /**
     * Metoda přidá další východ z této oblasti do oblasti předané v parametru.
     * <p>
     * Vzhledem k tomu, že pro uložení sousedních oblastí se používá {@linkplain Set},
     * může být přidán pouze jeden východ do každé oblasti <i>(tzn. nelze mít dvoje
     * 'dveře' do stejné sousední oblasti)</i>. Druhé volání metody se stejnou
     * oblastí proto nebude mít žádný efekt.
     * <p>
     * Lze zadat též cestu do sebe sama.
     *
     * @param exit oblast, do které bude vytvořen východ z aktuální oblasti
     */
    public void addExit(Area exit)
    {
        exits.add(exit);
    }

    /**
     * Metoda zkontroluje, zda oblast sousedí s oblastí s daným názvem.
     *
     * @param areaName název oblasti
     * @return {@code true}, pokud oblast sousedí s oblastí s daným názvem; jinak {@code false}
     */
    public boolean hasExit(String areaName)
    {
        for (Area exit : exits) {
            if (exit.getName().equals(areaName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Metoda vyhledá sousední oblast s daným názvem a vrátí na ní odkaz.
     *
     * @param areaName název oblasti
     * @return oblast s daným názvem; {@code null}, pokud oblast s takto pojmenovanou oblastí nesousedí
     */
    public Area getExit(String areaName)
    {
        for (Area exit : exits) {
            if (exit.getName().equals(areaName)) {
                return exit;
            }
        }

        return null;
    }

    public Set<Area> getExits() {
        return exits;
    }

    /**
     * Metoda přidá předmět <i>(objekt třídy {@link Item})</i> do oblasti.
     *
     * @param item předmět, který bude do oblasti přidán
     */
    public void addItem(Item item)
    {
        items.put(item.getName(), item);
    }

    /**
     * Metoda zkontroluje, zda oblast obsahuje předmět s daným názvem.
     *
     * @param itemName název předmětu
     * @return {@code true}, pokud oblast obsahuje předmět s daným názvem; jinak {@code false}
     */
    public boolean containsItem(String itemName)
    {
        return items.containsKey(itemName);
    }

    /**
     * Metoda vyhledá v oblasti předmět s daným názvem a vrátí na něj odkaz.
     *
     * @param itemName název předmětu
     * @return předmět s daným názvem; {@code null}, pokud v oblasti není
     */
    public Item getItem(String itemName)
    {
        return items.get(itemName);
    }

    /**
     * Metoda vyhledá v oblasti předmět s daným názvem, odstraní ho z oblasti a vrátí na něj odkaz.
     *
     * @param itemName název předmětu
     * @return odstraněný předmět; {@code null}, pokud předmět v oblasti není
     */
    public Item removeItem(String itemName)
    {
        return items.remove(itemName);
    }

    /**
     * Metoda přidá charakter <i>(objekt třídy {@link Character})</i> do oblasti.
     *
     * @param character charakter, který bude do oblasti přidán
     */
    
    public void addCharacter(Character character) 
    {
       hasCharacter=true;
       currentCharacter=character;
    }
    
    /**
     * Metoda vyhledá v oblasti charakter s daným názvem, odstraní ho z oblasti a vrátí na něj odkaz.
     *
     * @return odstraněný charakter; {@code null}, pokud charakter v oblasti není
     */
    public Character removeCharacter(Character character){
        hasCharacter=false;
        currentCharacter=null;
        
        return character;
    }
    
    /**
     * Metoda vyhledá, ci oblast ma v sebe aktualne charakter.
     *
     *
     * @return hladany charakter; {@code null}, pokud charakter v oblasti není
     */
    public boolean hasCharacter()
    {
        return hasCharacter;
    }
    
    /**
     * Metoda vyhledá v oblasti charakter a vrati ho.
     *
     *
     * @return hladany charakter; {@code null}, pokud charakter v oblasti není
     */
    public Character getCharacter()
    {
        return currentCharacter;
    }
    
    /**
     * Metoda porovnává dvě oblasti <i>(objekty)</i>. Oblasti jsou shodné,
     * pokud mají stejný název <i>(atribut {@link #name})</i>. Tato metoda
     * je důležitá pro správné fungování seznamu východů do sousedních
     * oblastí.
     * <p>
     * Podrobnější popis metody najdete v dokumentaci třídy {@linkplain Object}.
     *
     * @param o objekt, který bude porovnán s aktuálním
     * @return {@code true}, pokud mají obě oblasti stejný název; jinak {@code false}
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

        if (o instanceof Area) {
            Area area = (Area) o;

            return name.equals(area.getName());
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
     * Metoda porovná oblast s druhou oblastí předané v parametru abecedně
     * dle jejich názvů a vrátí kladné číslo, nulu, nebo záporné číslo
     * v závislosti na tom, zda je název této oblasti větší, stejný, nebo
     * menší než název druhé oblasti.
     * <p>
     * Metoda se používá pro řazení sousedních oblastí v atributu {@link #exits}.
     *
     * @param area oblast, jejíž název bude porovnán s názvem této oblasti
     * @return kladné číslo, nula, nebo záporné číslo v závislosti na porovnání názvů oblastí
     */
    @Override
    public int compareTo(Area area)
    {
        return name.compareTo(area.getName());
    }

    @Override
    public String toString() {
        return getName();
    }
}
