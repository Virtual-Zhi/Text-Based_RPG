package RPG_Game.Components.MapGeneration;

import RPG_Game.Chars.Player;
import RPG_Game.Components.assetData;
import clientPackage.RPG_Client;

import java.awt.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Map {

    /**
     * Ideas:
     * 2D Array of Items (Best Option so far) 50/50
     * Map
     * Point Class
     * Coordinate Point System
     */
    private Chunk[][] map;
    private HashMap<Double, Chunk> mapData;
    private Player p;

    private RPG_Client mapGui;


    // See if you can make one static assetData and use it across all classes without having to pass it. (Global Object/class)

    public Map(Player p) {
        map = new Chunk[30][30];
        mapData = assetData.getMapDictionary();
        this.p = p;
        addRandomElements();
        //data = a; //Loads data
    }

    public Map(Chunk[][] a) {
        map = a;
    }

    public void addRandomElements() {
        //Check player level
        int worldLevel = p.getWorldLevel();
        double maxCap = worldLevel+0.9;
        double random = new Random().nextDouble();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (i> map.length-4 && j>map[0].length-4)
                {
                    maxCap = worldLevel+1;
                }
                double x = Math.floor(ThreadLocalRandom.current().nextDouble(worldLevel, maxCap)*10)/10;
                map[i][j]=new Chunk(mapData.get(x));
            }
        }
        //ShuffleMap
        int i, position1, position2;
        Chunk[] temp;
        for (i=1; i<=1000; i++)
        {   // pick 2 random positions to swap
            position1 = (int) (Math.random() * (map.length-1) + 1);
            position2 = (int) (Math.random() * (map.length-1) + 1);

            temp = map[position1];
            map[position1] = map[position2];
            map[position2] = temp;
        }
    }

    public void updatePlayerIndexOnMap(int direction)
    {
        if (direction==1)
        {
            p.setCurrentLocation(p.getXPos(), p.getYPos()-1);
            if (p.getYPos()<0)
            {
                p.setCurrentLocation(p.getXPos(),29);
            }
            //Check out of bounds
        } else if (direction==2)
        {
            //south
            p.setCurrentLocation(p.getXPos(), p.getYPos()+1);
            if (p.getYPos()>=30)
            {
                p.setCurrentLocation(p.getXPos(), 0);
            }
            //Check out of bounds
        } else if (direction==3)
        {
            //left
            p.setCurrentLocation(p.getXPos()-1, p.getYPos());
            if (p.getXPos()<0)
            {
                p.setCurrentLocation(29,p.getYPos());
            }
            //Check out of bounds
        } else if (direction==4)
        {
            //right
            p.setCurrentLocation(p.getXPos()+1, p.getYPos());
            if (p.getXPos()>=30)
            {
                p.setCurrentLocation(0, p.getYPos());
            }
            //Check out of bounds
        }
    }

    public void openMap(int zoom)
    {
        mapGui = new RPG_Client();
        String s = "";
        for (int i = p.getYPos()-zoom; i<= p.getYPos()+zoom; i++)
        {
            int tempX = i;
            if (i>=map.length)
            {
                tempX-=map.length;
            } else if (i<0)
            {
                tempX+=map.length;
            }
            for (int j = p.getXPos()-zoom; j<=p.getXPos()+zoom; j++)
            {
                int tempY = j;
                if (j>=map[0].length)
                {
                    tempY-=map[0].length;
                } else if (j<0)
                {
                    tempY+=map[0].length;
                }
                mapGui.print("[");
                if (i==p.getYPos() && j==p.getXPos())
                {
                    mapGui.print("Player", false, new Color(37, 234, 73));
                } else if (map[tempX][tempY]==mapData.get(p.getWorldLevel()+0.9))
                {
                    mapGui.print(map[tempX][tempY].getName(), false, new Color(190, 0, 0));
                }
                else
                {
                    mapGui.print(map[tempX][tempY].getName());
                }
                mapGui.print("] ");
            }
            mapGui.println();
        }
        mapGui.println("------------------------------------------------------------------------------------------------");
        mapGui.println("Your current location is X: " + p.getXPos() + ", Y: " + p.getYPos());
        mapGui.println("You are in the " + map[p.getYPos()][p.getXPos()].getName()); //(Row, Col)
        boolean choice;
        do {
            choice = mapGui.nextOption("Close map?"); //Quick fix
        } while (!choice);
        closeMap();
    }

    public void closeMap()
    {
        mapGui.close();
    }

    public Chunk[][] getMap() {
        return map;
    }
}
