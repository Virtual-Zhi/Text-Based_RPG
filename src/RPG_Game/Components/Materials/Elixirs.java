package RPG_Game.Components.Materials;

import RPG_Game.Components.Items;
import RPG_Game.Components.dropRates;

import java.util.Timer;

public class Elixirs extends Items
{
    //Specific for only Potions
    Timer timer = new Timer();

    public Elixirs(Elixirs elixirs) {
        this(elixirs.getName(), elixirs.getAmountOfItem(), elixirs.rarity, elixirs.getRate());
    }

    public enum Rarity {
        LESSER, NORMAL, BETTER, POWERFUL, EXTREME, ULTRA
    }
    Rarity rarity;
    //Ends Here

    public Elixirs(String name, int amount, Rarity rarity, double dropRate) {
        super(name, amount,dropRate);
        this.rarity = rarity;
    }

    public Elixirs(String name, int amount, double dropRate) {
        super(name, amount,dropRate);
    }
    public void Wait(double k)
    {
        long time0, time1;
        time0 = System.currentTimeMillis();
        do //Checks
        {
            time1=System.currentTimeMillis();
        }
        while ((time1-time0) <k *1000); //Repeat
    }

    public void deleteAmount(int amount)
    {
        this.amountOfItem-=amount;
        if (amountOfItem<0)
        {
            amountOfItem=0;
        }
    }


}
