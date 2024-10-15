package RPG_Game.Components.Materials;

import RPG_Game.Chars.Player;

import java.util.Timer;
import java.util.TimerTask;

public class DefenseBoost extends Elixirs
{
    private double defenseBoostAmount;

    private int coolDown;

    public DefenseBoost(DefenseBoost x)
    {
        this(x.name, x.amountOfItem, x.rarity, x.defenseBoostAmount, x.getRate());
    }

    public DefenseBoost(DefenseBoost x, Rarity rarity)
    {
        this(x.name, x.amountOfItem, rarity, x.defenseBoostAmount, x.getRate());
    }

    public DefenseBoost(String name, int amount, Rarity rarity, double effectAmount, double dropRate)
    {
        super(name, amount, rarity, dropRate);
        defenseBoostAmount = effectAmount;
        basicPermutation();
    }

    public DefenseBoost(String name, int amount, double effectAmount, double dropRate)
    {
        super(name, amount, dropRate);
        defenseBoostAmount = effectAmount;
    }

    public void basicPermutation() {
        switch (rarity) {
            case LESSER:
                defenseBoostAmount /= 1.5;
                break;
            case NORMAL:
                defenseBoostAmount *= 1;
                break;
            case BETTER:
                defenseBoostAmount *= 1.2;
                break;
            case POWERFUL:
                defenseBoostAmount *= 1.5;
                break;
            case EXTREME:
                defenseBoostAmount *= 1.7;
                break;
            case ULTRA:
                defenseBoostAmount *= 2;
                break;

        }
    }

    public String use(Player player)
    {
        if (coolDown==0 && getAmountOfItem()>0) {
            Wait(1);
            coolDown = 10;
            player.getCharacterStats().addDefense((Math.floor(defenseBoostAmount*100))/100);
            deleteAmount(1);
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (coolDown==5) {
                        deleteEffect(player);
                    }
                    coolDown--;
                    if (coolDown<=0)
                    {
                        timer.cancel();
                        timer.purge();
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 1000, 1000);
            return "Used Successfully!";
        } else if (getAmountOfItem()<=0)
        {
            return "You ran out buddy!";
        } else
        {
            return "Calm down dude, cool down right now " + coolDown + " seconds remaining.";
        }
    }

    public void deleteEffect(Player player)
    {
        player.getCharacterStats().addDefense(-((Math.floor(defenseBoostAmount*100))/100));
    }



}
