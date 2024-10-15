package RPG_Game.Combat;

import RPG_Game.Chars.Monster;
import RPG_Game.Chars.Player;
import RPG_Game.Components.MapGeneration.Chunk;
import RPG_Game.Components.MapGeneration.Map;

public class CombatPlane {
    private int x;
    private int y;
    private int[][] grid;

    public CombatPlane() {
        x = 0;
        y = 0;
        grid = new int[5][5];
    }

    public CombatPlane(int size) {
        x = 0;
        y = 0;
        grid = new int[size][size];
    }

    public CombatPlane(Chunk w) {

    }

    public int[][] getGrid() {
        return grid;
    }


}
