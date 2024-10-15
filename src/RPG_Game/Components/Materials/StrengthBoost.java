package RPG_Game.Components.Materials;

import RPG_Game.Chars.Player;

import java.util.Timer;
import java.util.TimerTask;

public class StrengthBoost extends Elixirs
{
    private double strengthBoostAmount;

    private int coolDown;

    public StrengthBoost()
    {
        super("null", 0, Rarity.NORMAL,100);
    }

    public StrengthBoost(StrengthBoost x)
    {
        this(x.name, x.amountOfItem, x.rarity, x.strengthBoostAmount,x.getRate());
    }

    public StrengthBoost(StrengthBoost x, Rarity rarity)
    {
        this(x.name, x.amountOfItem, rarity, x.strengthBoostAmount,x.getRate());
    }

    public StrengthBoost(String name, int amount, Rarity rarity, double effectAmount,double dropRate)
    {
        super(name, amount, rarity, dropRate);
        strengthBoostAmount = effectAmount;
        basicPermutation();
    }

    public StrengthBoost(String name, int amount, double effectAmount,double dropRate)
    {
        super(name, amount, dropRate);
        strengthBoostAmount = effectAmount;
    }

    public void basicPermutation() {
        switch (rarity) {
            case LESSER:
                strengthBoostAmount /= 2;
                break;
            case NORMAL:
                strengthBoostAmount *= 1;
                break;
            case BETTER:
                strengthBoostAmount *= 1.2;
                break;
            case POWERFUL:
                strengthBoostAmount *= 1.5;
                break;
            case EXTREME:
                strengthBoostAmount *= 1.7;
                break;
            case ULTRA:
                strengthBoostAmount *= 2;
                break;

        }
    }

    public String use(Player player)
    {
        if (coolDown<=0 && getAmountOfItem()>0) {
            Wait(1);
            coolDown = 10; //Cool down
            player.getCharacterStats().addStrength((Math.floor(strengthBoostAmount*100)/100));
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
        player.getCharacterStats().addStrength(-((Math.floor(strengthBoostAmount*100))/100));
    }



}
