package RPG_Game.Chars;

import RPG_Game.menuFolder.Stats;

public abstract class Character
{
    private String name;
    //private int maxHP;
    private int skillFactor;

    protected Stats characterStats;


    public Character()
    {
        this("x",100,1);
        skillFactor = 0;
    }

    public Character(String name, int level)
    {
        this.name = name;
        characterStats = new Stats(level);
    } //For Monster

    public Character(String name, double HP, int level)
    {
        this.name = name;
        characterStats = new Stats(level);
        characterStats.setHP(HP);
    }

    public Character(Character x)
    {
        this.name = x.name;
        this.characterStats = x.getCharacterStats();
        this.skillFactor = x.getSkillFactor();
    }

    //Gets
    public String getName()
    {
        return name;
    }


    public double getAttack()
    {
        return characterStats.getStrength();
    }

    public double getArmor()
    {
        return characterStats.getDefense();
    }

    public int getSkillFactor() {
        return skillFactor;
    }

    public int getLevel() { return characterStats.getLevel(); }

    public Stats getCharacterStats()
    {
        return characterStats;
    }



    //Mutators
    public void takeDMG(double amount)
    {
        characterStats.addHP(-amount);
    }



    public void setName(String s)
    {
        this.name = s;
    }


}
