package RPG_Game.Chars;

import RPG_Game.Components.Armor.*;
import RPG_Game.Components.Items;
import RPG_Game.Components.MapGeneration.Chunk;
import RPG_Game.Components.Weapons.Weapons;
import RPG_Game.menuFolder.Inventory;
import RPG_Game.menuFolder.Stats;

public class Player extends Character
{

    private final Inventory playerInv;
    private final Armor[] armorsEquipped;

    private final Weapons[] weaponsEquipped;
    private boolean isDuelWield;

    private int worldLevel;

    private int xPos, yPos;
    private int combatXPos, combatYPos;



    public Player()
    {
        this("The Chosen One", 69420, 50,0,0);
    }

    public Player(String name, double HP, int level, int xPos, int yPos)
    {
        super(name, HP, level);
        playerInv = new Inventory();
        armorsEquipped = new Armor[4];
        weaponsEquipped = new Weapons[2];
        isDuelWield = false;
        worldLevel = 1;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    //returning player
    public Player (Player x)
    {
        super(x);
        this.playerInv = x.getPlayerInv();
        this.armorsEquipped = x.armorsEquipped;
        this.weaponsEquipped = x.weaponsEquipped;
        this.isDuelWield = x.isDuelWield;
        this.worldLevel = x.getWorldLevel();
        this.xPos = x.getXPos();
        this.yPos = x.getYPos();
    }

    public void attack(Monster monster) {}

    public void equip(Items obj) {
        if (obj instanceof Helmet) {
            armorsEquipped[0] = (Armor) obj;
        } else if (obj instanceof Chestplate) {
            armorsEquipped[1] = (Armor) obj;
        } else if (obj instanceof Legging) {
            armorsEquipped[2] = (Armor) obj;
        } else if (obj instanceof Boots) {
            armorsEquipped[3] = (Armor) obj;
        } else if (obj instanceof Weapons)
        {
            if (isDuelWield && weaponsEquipped[0]!=null)
            {
                weaponsEquipped[1] = (Weapons) obj;
            } else if (weaponsEquipped[1]!=null || weaponsEquipped[0] == null) {
                weaponsEquipped[0] = (Weapons) obj;
                characterStats.addStrength(((Weapons) obj).getDamage());
            }
        }

    }
    public void addExp(double amount)
    {
        characterStats.addExp(amount);
        if (characterStats.getExp()>=characterStats.getRequiredExp())
        {
            characterStats.addExp(-characterStats.getRequiredExp());
            characterStats.levelUP();
        }
    }

    public void setDuelWield(boolean x)
    {
        isDuelWield = x;
    }

    public void setCurrentLocation(int x, int y)
    {
        this.xPos = x;
        this.yPos = y;
    }

    public void setWorldLevel(int upDown)
    {
        if (upDown==1)
        {
            worldLevel++;
        } else
        {
            worldLevel--;
        }
    }

    public void setCombatPos(int x, int y) {
        this.combatXPos = x;
        this.combatYPos = y;
    }

    public int getWorldLevel()
    {
        return worldLevel;
    }

    public int getXPos()
    {
        return xPos;
    }

    public int getYPos()
    {
        return yPos;
    }



    public int getCombatXPos() { return combatXPos; }

    public int getCombatYPos() { return combatYPos; }


    public Inventory getPlayerInv() {
        return playerInv;
    }
}