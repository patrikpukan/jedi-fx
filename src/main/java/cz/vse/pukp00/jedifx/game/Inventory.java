package cz.vse.pukp00.jedifx.game;
import cz.vse.pukp00.jedifx.PredmetPozorovani;

import java.util.*;
import cz.vse.pukp00.jedifx.PredmetPozorovani;
import cz.vse.pukp00.jedifx.Pozorovatel;
import cz.vse.pukp00.jedifx.ZmenaHry;

/**
 * Třída implementující inventar, jeho kapacitu, aktualne pridane predmety.
 *
 * @author Patrik Pukan
 * @version ZS-2022, 2023-01-19
 */
public class Inventory implements PredmetPozorovani
{
    private String name;
    private Map<String, Item> contents;
    int currentSize;

    private Map<ZmenaHry, Set<Pozorovatel>> seznamPozorovatelu = new HashMap<>();
    /**
     * Konstruktor třídy, vytvoří inventar se zadaným názvem.
     *
     * @param name název inventara <i>(jednoznačný identifikátor, musí se jednat o text bez mezer)</i>
     * 
     */
    
    public Inventory(String name) {
        this.name=name;
        this.contents = new TreeMap<>();
        this.currentSize=0;
        for (ZmenaHry zmena : ZmenaHry.values()) {
            seznamPozorovatelu.put(zmena, new HashSet<>());
        }
    }
    /**
     * Metoda přidá předmět <i>(objekt třídy {@link Item})</i> do inventara.
     *
     * @param item předmět, který bude do inventara pridany
     */
    public void addItem(Item item)
    {   
        if (this.currentSize<5) {
        this.contents.put(item.getName(), item);
        currentSize+=1;
        upozorniPozorovatele(ZmenaHry.ZMENA_INVENTARE);
        }
        else {
            System.out.println("Inventar je plny, nejprve musis neco vyhodit.");
        }
    }
    
    /**
     * Metoda vyhledá v inventari předmět s daným názvem, odstraní ho z inventare a vrátí na něj odkaz.
     *
     * @param itemName název předmětu
     * @return odstraněný předmět; {@code null}, pokud předmět v oblasti není
     */
    public Item removeItem(String itemName)
    {
        this.currentSize-=1;
        return this.contents.remove(itemName);
    }
    /**
     * Metoda vyhledá v inventari předmět s daným názvem, odstraní ho z inventare a vrátí na něj odkaz.
     *
     * @return meno predmetu
     */
    public String getName()
    {
        return this.name;
    }
    
     /**
     * Metoda zistuje pocet aktualnych predmetov v inventari.
     *
     * @return pocet predmetov
     */
    public int getSize()
    {
        return this.currentSize;
    }
    
    /**
     * Metoda vrati obsah inventara a pocet predmetov
     *
     * @return vymenovane predmety v inventari a pocet
     */
    public String getInventoryContent() {
        String itemNames = "Predmety v inventari: ";
        for (String itemName : this.contents.keySet()) {
            itemNames += " " + itemName;
        }
        return itemNames + " \n V inventari je " + this.currentSize + " predmet(ov).";
    }
    
    /**
     * Metoda vyhledá v inventari předmět s daným názvem a vrati ho.
     * @return vrateny predmet předmět; {@code null}, pokud předmět v oblasti není
     */
    public Collection<Item> getItems() {
        Collection<Item> items = new HashSet<>(contents.values());
        return items;
    }

    public Item getItem(String itemName)
    {
        return this.contents.get(itemName);
    }
    
    /**
     * Metoda zisti, ci je v inventari předmět s daným názvem.
     *
     * @param itemName název předmětu
     * @return vrateny predmet předmět; {@code null}, pokud předmět v oblasti není
     */
    public boolean containsItem(String itemName)
    {
        return this.contents.containsKey(itemName);
    }

    public void registruj(ZmenaHry zmena, Pozorovatel pozorovatel) {
        seznamPozorovatelu.get(zmena).add(pozorovatel);
    }

    private void upozorniPozorovatele(ZmenaHry zmena) {
        for (Pozorovatel pozorovatel : seznamPozorovatelu.get(zmena)) {
            pozorovatel.aktualizuj();
        }
    }
}
