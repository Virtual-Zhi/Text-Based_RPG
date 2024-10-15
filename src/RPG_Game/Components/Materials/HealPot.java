package RPG_Game.Components.Materials;

import RPG_Game.Chars.Player;

import java.util.Timer;
import java.util.TimerTask;

public class HealPot extends Elixirs
{
    private double healAmount;

    private int coolDown;

    public HealPot(HealPot x)
    {
        this(x.getName(), x.getAmountOfItem(), x.rarity, x.healAmount, x.getRate());
    }

    public HealPot(HealPot x, Rarity rarity)
    {
        this(x.getName(), x.getAmountOfItem(), rarity, x.healAmount, x.getRate());
    }

    public HealPot(String name, int amount, Rarity rarity, double effectAmount, double dropRate)
    {
        super(name, amount, rarity,dropRate);
        healAmount = effectAmount;
        basicPermutation();
    }

    public HealPot(String name, int amount, double effectAmount, double dropRate)
    {
        super(name, amount,dropRate);
        healAmount = effectAmount;
    }

    public void basicPermutation() {
        switch (rarity) {
            case LESSER:
                healAmount /= 1.5;
                break;
            case NORMAL:
                healAmount *= 1;
                break;
            case BETTER:
                healAmount *= 1.2;
                break;
            case POWERFUL:
                healAmount *= 1.5;
                break;
            case EXTREME:
                healAmount *= 1.7;
                break;
            case ULTRA:
                healAmount *= 2;
                break;

        }
    }

    public String use(Player player)
    {
        if (getAmountOfItem()>0 && coolDown <= 0) {
            Wait(1);
            coolDown = 5;
            deleteAmount(1);
            player.getCharacterStats().addHP(healAmount);
            timer = new Timer();
            TimerTask t = new TimerTask() {
                @Override
                public void run() {
                    if (coolDown <= 0) {
                        timer.cancel();
                        timer.purge();
                    } else {
                        coolDown--;
                    }
                }
            };
            timer.scheduleAtFixedRate(t, 1000, 1000);
            return "Used Successfully!";
        } else if (getAmountOfItem()<=0)
        {
            return "You ran out Buddy!";
        } else
        {
            return "Calm down dude, cool down right now " + coolDown + " seconds remaining.";
        }
    }



}
