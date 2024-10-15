package RPG_Game.Components;

public class Items implements dropRates
{
    protected String name;
    protected int amountOfItem;

    protected double rate;

    public Items(Items x)
    {
        this(x.name, x.amountOfItem, x.rate);
    }
    public Items(String name, int amountOfItem, double dropRate)
    {
        this.name = name;
        this.amountOfItem = amountOfItem;
        this.rate = dropRate;
    }

    public String getName()
    {
        return name;
    }

    public int getAmountOfItem()
    {
        return amountOfItem;
    }

    public void addAmount(int amount)
    {
        this.amountOfItem+=amount;
    }
    @Override
    public double getRate() {
        return rate;
    }
}
