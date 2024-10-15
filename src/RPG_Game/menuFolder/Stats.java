package RPG_Game.menuFolder;

public class Stats
{

    private double strength, defense, agility, HP, maxHP, requiredExp, Exp;
    private int level;
    private int skillPoints;

    public Stats()
    {
        strength=defense=1;
        agility = 0.5;
        maxHP = 100;
        Exp = 0;
        level = 0;
    }
    public Stats(Stats x)
    {
        this.strength = x.getStrength();
        this.defense = x.getDefense();
        this.maxHP = x.getMaxHP();
        this.Exp = 0;
        this.agility = x.getAgility();
        this.requiredExp = x.getRequiredExp();
        this.level = x.getLevel();
        skillPoints=x.skillPoints;
    }


    //Testing, Not actual
    public Stats(int level)
    {
        this();
        for (int i = 1; i<=level; i++)
        {
            levelUP();
        }
        //skillPoints+=3;
    }
    // Testing
    public void setAgility(double amount) {
        agility=amount;
    }
    public void setStrength(double amount)
    {
        strength=amount;
    }

    public void setDefense(double amount)
    {
        defense = amount;
    }

    public void setHP(double amount){
        HP = amount;
        if (HP>=getMaxHP())
        {
            HP=getMaxHP();
        }
    }

    public void addStrength(double amount)
    {
        strength+=amount;
        fixStats();
    }

    public void addDefense(double amount)
    {
        defense+=amount;
        fixStats();
    }

    public void addAgility(double amount)
    {
        agility+=amount;
        fixStats();
    }

    public void addMaxHP(double amount)
    {
        maxHP+=amount;
        fixStats();
    }
    public void addHP(double amount)
    {
        this.HP+=amount;
        if (HP>=getMaxHP())
        {
            HP=getMaxHP();
        }
        fixStats();
    }

    public void addExp(double amount)
    {
        Exp+=amount;
    }

    public void addSkillPoints(int amount)
    {
        skillPoints+=amount;
    }
    public void levelUP()
    {
        this.level++;
        this.maxHP+=10;
        this.strength+=0.50;
        this.defense+=0.30;
        this.agility+=0.10;
        this.requiredExp = Math.round(Math.pow((level/0.27),2.15)*100)/100;
        this.Exp = 0;
        this.HP=maxHP;
        addSkillPoints(3);
        fixStats();
    }

    private void fixStats()
    {
        this.strength = (double)Math.round(strength*100)/100;
        this.defense = (double)Math.round(defense*100)/100;
        this.agility = (double)Math.round(agility*100)/100;
        this.HP = (double)Math.round(HP*100)/100;

    }

    public double getAgility() {
        return agility;
    }

    public double getDefense() {
        return defense;
    }

    public double getStrength() {
        return strength;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public double getHP()
    {
        return HP;
    }

    public double getExp()
    {
        return Exp;
    }

    public double getRequiredExp() {
        return requiredExp;
    }

    public int getSkillPoints() { return skillPoints; }

    public int getLevel()
    {
        return level;
    }

    public String toString()
    {
        return "                                    Stats\n"+
                "Strength: " + getStrength() +"\n" +
                "Defense:  " + getDefense() + "\n" +
                "Agility:  " + getAgility() + "\n" +
                "Health:   " + getHP()+"/"+getMaxHP() + "\n" +
                "Exp:      "+getExp()+"/"+getRequiredExp() +
                "\n\n\n" +
                "Available skill points:  "+getSkillPoints();
    }
}
