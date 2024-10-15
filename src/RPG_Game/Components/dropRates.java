package RPG_Game.Components;

public interface dropRates 
{
    public enum Rarity {
        BROKEN, COMMON, UNCOMMON, RARE, EPIC, LEGENDARY
    }
    
    Rarity rarity = null;

    double rate = 0;

    public double getRate();

}

