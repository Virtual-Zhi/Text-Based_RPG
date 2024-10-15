package RPG_Game.Components.Armor;

import RPG_Game.Components.Items;
import RPG_Game.Components.dropRates;

public abstract class Armor extends Items
{
    private double defense;
    private double durability;
    private Rarity rarity;

    public Armor(String name, int amount, Rarity rarity, double dropRate, double defense, double durability) {
        super(name, amount, dropRate);
        this.rarity = rarity;
        this.defense = defense;
        this.durability = durability;
    }
    public Armor(String name, int amount, double dropRate, double defense, double durability) {
        super(name, amount, dropRate);
        this.defense = defense;
        this.durability = durability;
    }

    public void deleteAmount(int amount)
    {
        this.amountOfItem-=amount;
        if (amountOfItem<0)
        {
            amountOfItem=0;
        }
    }

    public void setDefense(double amount)
    {
        this.defense = amount;
    }

    public void setDurability(double amount)
    {
        this.durability = amount;
    }

    Rarity getRarity() {
        return rarity;
    }

    public double getDefense() {
        return defense;
    }

    public double getDurability()
    {
        return durability;
    }



}
