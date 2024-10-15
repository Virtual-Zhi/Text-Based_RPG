package RPG_Game.Components.Armor;

import RPG_Game.Components.Materials.Elixirs;

public class Chestplate extends Armor
{

    public Chestplate()
    {
        super("basicChestPlate", 1, Rarity.BROKEN, 100,5,100);
    }

    public Chestplate(String name, int amount, Rarity rarity, double defense, double durability, double dropRate) {
        super(name, amount, rarity, dropRate,defense,durability);
        basicPermutation();
    }
    public Chestplate(String name, int amount, double defense, double durability, double dropRate) {
        super(name, amount, dropRate,defense,durability);
        basicPermutation();
    }

    public Chestplate(Chestplate other, Rarity rarity) {
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
