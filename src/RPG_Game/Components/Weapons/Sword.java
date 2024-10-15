package RPG_Game.Components.Weapons;

public class Sword extends Weapons
{

    private double durability;

    public Sword()
    {
        super("Basic", 1, Rarity.COMMON,100,5);
        this.durability = 100;
    }

    public Sword(Sword x)
    {
        this(x.getName(), x.getAmountOfItem(), x.getRarity(), x.getDamage(), x.durability, x.getRate());
    }

    public Sword(Sword x, Rarity rarity)
    {
        this(x.getName(), x.getAmountOfItem(), rarity, x.getDamage(), x.durability, x.getRate());
    }


    public Sword(String name, int amount, Rarity rarity, double damage, double durability, double dropRate) {
        super(name, amount, rarity, dropRate, damage);
        this.durability = durability;
        basicPermutation();
    }

    public Sword(String name, int amount, double damage, double durability, double dropRate) {
        super(name, amount,Rarity.COMMON, dropRate,damage);
        this.durability = durability;
    }

    public void basicPermutation() {
        switch (getRarity()) {
            case BROKEN:
                setDamage(getDamage()/2);
                durability /= 2;
                break;
            case UNCOMMON:
                setDamage(getDamage()*1.2);
                durability *= 1.5;
                break;
            case RARE:
                setDamage(getDamage()*1.5);
                durability *= 1.7;
                break;
            case EPIC:
                setDamage(getDamage()*1.7);
                durability *= 2;
                break;
            case LEGENDARY:
                setDamage(getDamage()*2);
                durability *= 2.5;
                break;

        }
    }

    public void setDurability(int i)
    {
        durability+=(i*0.7);
    }

    public double getDurability()
    {
        return durability;
    }

}
