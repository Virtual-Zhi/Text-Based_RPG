package RPG_Game.Components.MapGeneration;

import RPG_Game.Chars.Monster;
import RPG_Game.Components.Items;
import RPG_Game.Components.assetData;

import java.util.Timer;
import java.util.TimerTask;

public class Chunk {

    private String name, description;
    private Monster monster;
    private Items item;

    private int mCoolDown, iCoolDown;

    private Timer t;

    public Chunk() {
        this("Piece of Shit", "Come here little girl", null, null);
    }

    public Chunk(String name, String description) {
        this(name, description, null, null);
    }

    public Chunk(String name, String description, Monster m, Items a)
    {
        this.name = name;
        this.description = description;
        monster = m;
        item = a;
    }

    public Chunk(Chunk a)
    {
        this(a.getName(), a.getDescription(), a.getMonster(), a.getItem());
    }

    public void monsterKilled()
    {
        mCoolDown = 60; //Changes the coolDown for monster spawn
        t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (mCoolDown<=0)
                {

                    t.cancel();
                    t.purge();
                } else
                {
                    mCoolDown--;
                }
            }
        };
        t.scheduleAtFixedRate(task, 1000,1000);
    }

    public void itemPicked()
    {
        iCoolDown = 30; //Changes the coolDown for item spawn
        t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (iCoolDown<=0)
                {
                    t.cancel();
                    t.purge();
                } else
                {
                    iCoolDown--;
                }
            }
        };
        t.scheduleAtFixedRate(task, 1000,1000);
    }

    public Monster getMonster()
    {
        if (mCoolDown<=0) {
            return monster;
        }
        return null;
    }

    public Items getItem()
    {
        if (iCoolDown<=0)
        {
            return item;
        }
        return null;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }
}
