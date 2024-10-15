package RPG_Game.Components.Armor;

public class Helmet extends Armor
{
    public Helmet()
    {
        super("basicHelmet", 1, Rarity.BROKEN, 100,5,100);
    }

    public Helmet(String name, int amount, Rarity rarity, double defense, double durability, double dropRate) {
        super(name, amount, rarity, dropRate,defense,durability);
        basicPermutation();
    }
    public Helmet(String name, int amount, double defense, double durability, double dropRate) {
        super(name, amount, dropRate,defense,durability);
        basicPermutation();
    }

    public Helmet(Helmet other, Rarity rarity) {
        this(other.name, other.amountOfItem, rarity, other.getDefense(), other.getDurability(), other.getRate());
    }


    public void basicPermutation() {
        if (getRarity() != null) {
            switch (getRarity()) {
                case BROKEN:
                    setDefense(getDefense()/1.5);
                    setDurability(getDurability()/1.5);
                    break;
                case UNCOMMON:
                    setDefense(getDefense()*1.2);
                    setDurability(getDurability()*1.5);
                    break;
                case RARE:
                    setDefense(getDefense()*1.5);
                    setDurability(getDurability()*2);
                    break;
                case EPIC:
                    setDefense(getDefense()*1.7);
                    setDurability(getDurability()*2.25);
                    break;
                case LEGENDARY:
                    setDefense(getDefense()*2);
                    setDurability(getDurability()*2.75);
                    break;
            }

        }
    }


}
