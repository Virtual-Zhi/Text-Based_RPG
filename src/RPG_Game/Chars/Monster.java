package RPG_Game.Chars;

import RPG_Game.Chars.Character;
import RPG_Game.Combat.MonsterBehavior;
import RPG_Game.Components.Items;
import RPG_Game.RPG_Server;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Monster extends Character implements Runnable {
    Random r = new Random();
    double exp;
    private double HP;
    private int maxHP, level;
    Items[] lootTable;
    private Player targetPlayer;
    private int combatXPos, combatYPos;

    public Monster() {
        super();
        this.lootTable = new Items[10];
        this.targetPlayer = null;
    }

    public Monster (Monster a)
    {
        this(a.getName(), a.getCharacterStats().getLevel(), a.exp, a.lootTable);

    }

    public Monster(String name, int level, double exp, Items[] loot)
    {
        super(name, level);
        getCharacterStats().setDefense(Math.round(level * 1.47 * 100) / 100);
        getCharacterStats().setStrength(Math.round(level * 1.6 * 100) / 100);
        getCharacterStats().setAgility(Math.round(level*1.025*100)/100);
        this.exp = exp;
        this.lootTable = loot;
        this.targetPlayer = null;
    }

    public Monster(String name, int level, double exp, Items[] loot, Player p) {
        this(name, level, exp, loot);
        this.targetPlayer = p;
    }

    public void run() {

    }

    public void giveExp(Player player)
    {
        player.addExp(exp);
    }

    public double expDrop()
    {
        return exp;
    }


    public void setCombatPos(int x, int y) {
        this.combatXPos = x;
        this.combatYPos = y;
    }

    public void setTargetPlayer(Player p) {
        this.targetPlayer = p;
    }

    public int getCombatXPos() { return combatXPos; }

    public int getCombatYPos() { return combatYPos; }

}