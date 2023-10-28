package cz.vse.pukp00.jedifx.game;

import java.util.*;

/**
 * Třída představuje Charakter ve scénáři
 * hry. Každy charakter má název, který ho jednoznačně identifikuje. Tak isto o nom vieme, ci je to nepriatel, alebo nie je.
 *
 * @author Patrik Pukan
 * @version ZS-2022, 2023-01-22
 */
public class Character
{
    private String name;
    private String description;
    private boolean isEnemy;
    /**
     * Konstruktor třídy, vytvoří charakter se zadaným názvem a popisem, aj ci je nepriatelsky
     *
     * @param name název charakteru <i>(jednoznačný identifikátor, musí se jednat o text bez mezer)</i>
     * @param description podrobnější popis charakter
     * @param isEnemy ci je nepriatel alebo nie je
     */
    public Character(String name, String description, boolean isEnemy)
    {
        this.name = name;
        this.description = description;
        this.isEnemy = isEnemy;

    }

    /**
     * Metoda vrací název charakteru, který byl zadán při vytváření instance jako
     * parametr konstruktoru. Jedná se o jednoznačný identifikátor charakteru
     * <i>(ve hře nemůže existovat více oblastí se stejným názvem)</i>. Aby
     * hra správně fungovala, název charakteru nesmí obsahovat mezery, v případě
     * potřeby můžete více slov oddělit pomlčkami, použít camel-case apod.
     *
     * @return název charakteru
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Metoda vrací popis charakteru
     *
     * @return popis charakteru
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Metoda zkontroluje, zda je charakter nepriatel alebo nie
     *
     * @return {@code true}, pokud je charakter nepriatel; jinak {@code false}
     */
    public boolean isCharacterAnEnemy() {
        return this.isEnemy;
    }
    /**
     * Metoda nastaví popis charakteru.
     *
     * @param description nový popis charakteru
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
}
