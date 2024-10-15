package RPG_Game.Components.Weapons;

import RPG_Game.Components.Items;
import RPG_Game.Components.dropRates;

public abstract class Weapons extends Items
{
    private Rarity rarity;

    private double rate;

    private double damage;


    public Weapons(String name, int amount, Rarity rarity,double dropRate, double damage) {
        super(name, amount, dropRate);
        this.rarity = rarity;
        this.damage = damage;
    }
    public Weapons(String name, int amount, double dropRate) {
        super(name, amount, dropRate);
    }

    public void deleteAmount(int amount)
    {
        this.amountOfItem-=amount;
        if (amountOfItem<0)
        {
            amountOfItem=0;
        }
    }

    public double getRate()
    {
        return rate;
    }

    public Rarity getRarity()
    {
        return rarity;
    }

    public double getDamage()
    {
        return damage;
    }

    public void setDamage(double damage)
    {
        this.damage = damage;
    }

}
