package RPG_Game.Components;

import RPG_Game.Chars.Monster;
import RPG_Game.Components.Armor.*;
import RPG_Game.Components.MapGeneration.Chunk;
import RPG_Game.Components.Materials.*;
import RPG_Game.Components.Weapons.*;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class assetData {
    private static HashMap<Double, Armor> armorDictionary;
    private static HashMap<Double, Weapons> weaponDictionary;
    private static HashMap<Integer, Elixirs> elixirsDictionary;
    private static HashMap<Integer, Items> itemsDictionary;
    private static HashMap<Double, Monster> monsterDictionary;

    private static HashMap<Double, Chunk> chunkDictionary;

    private static HashMap<Integer, String> descriptionDictionary;

    public static void load()
    {
        loadAllTables();
    }

    private static void loadAllTables() {
        /* Armor (5 each part)
         *  Two for Purchase
         *  Three for Drop (Insanely low rates)
         * */
        armorDictionary = new HashMap<Double, Armor>();
        weaponDictionary = new HashMap<Double, Weapons>();
        elixirsDictionary = new HashMap<Integer, Elixirs>();
        itemsDictionary = new HashMap<Integer, Items>();
        monsterDictionary = new HashMap<Double, Monster>();
        chunkDictionary = new HashMap<Double, Chunk>();
        descriptionDictionary = new HashMap<Integer, String>();
        /* Part 1 (Humanoids)*/

        //Helmet
        armorDictionary.put(1.00, new Helmet("basicHelmet", 1, 1.5, 15, 100));
        armorDictionary.put(1.01, new Helmet("advancedHelmet", 1, 4, 25, 100));
        //Drops
        armorDictionary.put(1.02, new Helmet());
        armorDictionary.put(1.03, new Helmet());
        armorDictionary.put(1.04, new Helmet());

        //ChestPlate
        armorDictionary.put(1.10, new Chestplate("basicChestPlate", 1, 2.5, 25, 100));
        armorDictionary.put(1.11, new Chestplate("advancedChestPlate", 1, 5, 35, 100));
        //Drops
        armorDictionary.put(1.12, new Chestplate());
        armorDictionary.put(1.13, new Chestplate());
        armorDictionary.put(1.14, new Chestplate());

        //Legging
        armorDictionary.put(1.20, new Legging("basicLeggings", 1, 2, 20, 100));
        armorDictionary.put(1.21, new Legging("advancedLeggings", 1, 4.5, 30, 100));
        //Drops
        armorDictionary.put(1.22, new Legging());
        armorDictionary.put(1.23, new Legging());
        armorDictionary.put(1.24, new Legging());

        //Boots
        armorDictionary.put(1.30, new Boots("basicBoots", 1, 1.5, 10, 100));
        armorDictionary.put(1.31, new Boots("advancedBoots", 1, 4, 20, 100));
        //Drops
        armorDictionary.put(1.32, new Boots());
        armorDictionary.put(1.33, new Boots());
        armorDictionary.put(1.34, new Boots());

        /** Part 2 (Insect Kingdom)*/

        //Helmet
        armorDictionary.put(2.00, new Helmet());
        armorDictionary.put(2.01, new Helmet());
        //Drops
        armorDictionary.put(2.02, new Helmet());
        armorDictionary.put(2.03, new Helmet());
        armorDictionary.put(2.04, new Helmet());

        //ChestPlate
        armorDictionary.put(2.10, new Chestplate());
        armorDictionary.put(2.11, new Chestplate());
        //Drops
        armorDictionary.put(2.12, new Chestplate());
        armorDictionary.put(2.13, new Chestplate());
        armorDictionary.put(2.14, new Chestplate());

        //Legging
        armorDictionary.put(2.20, new Legging());
        armorDictionary.put(2.21, new Legging());
        //Drops
        armorDictionary.put(2.22, new Legging());
        armorDictionary.put(2.23, new Legging());
        armorDictionary.put(2.24, new Legging());

        //Boots
        armorDictionary.put(2.30, new Boots());
        armorDictionary.put(2.31, new Boots());
        //Drops
        armorDictionary.put(2.32, new Boots());
        armorDictionary.put(2.33, new Boots());
        armorDictionary.put(2.34, new Boots());

        /* Part 3 (Ancient Monsters)*/

        //Helmet
        armorDictionary.put(3.00, new Helmet());
        armorDictionary.put(3.01, new Helmet());
        //Drops
        armorDictionary.put(3.02, new Helmet());
        armorDictionary.put(3.03, new Helmet());
        armorDictionary.put(3.04, new Helmet());

        //ChestPlate
        armorDictionary.put(3.10, new Chestplate());
        armorDictionary.put(3.11, new Chestplate());
        //Drops
        armorDictionary.put(3.12, new Chestplate());
        armorDictionary.put(3.13, new Chestplate());
        armorDictionary.put(3.14, new Chestplate());

        //Legging
        armorDictionary.put(3.20, new Legging());
        armorDictionary.put(3.21, new Legging());
        //Drops
        armorDictionary.put(3.22, new Legging());
        armorDictionary.put(3.23, new Legging());
        armorDictionary.put(3.24, new Legging());

        //Boots
        armorDictionary.put(3.30, new Boots());
        armorDictionary.put(3.31, new Boots());
        //Drops
        armorDictionary.put(3.32, new Boots());
        armorDictionary.put(3.33, new Boots());
        armorDictionary.put(3.34, new Boots());


        /* Weapon */
        // Part 1 (Humanoids)
        weaponDictionary.put(1.0, new Sword("basicSword", 1, 5, 20, 100)); // Shop Item
        // Boar Knight
        weaponDictionary.put(1.1, new Sword("sharpSword", 1, 7.5, 25, 21));
        weaponDictionary.put(1.2, new Sword("polishedSword", 1, 11.25, 30, 10));
        // Snake Cannibal
        weaponDictionary.put(1.3, new Sword("glowingSword", 1, 16.5, 40, 20));
        weaponDictionary.put(1.4, new Sword("scaleSword", 1, 24.75, 45, 9));
        // Bear Tribesman
        weaponDictionary.put(1.5, new Sword("sharpBranch", 1, 14, 20, 25));
        weaponDictionary.put(1.6, new Sword("plusSharpUltraSharperBranch", 1, 21, 30, 18));
        // Kobold
        weaponDictionary.put(1.7, new Sword("steelSword", 1, 18, 50, 15));
        weaponDictionary.put(1.8, new Sword("darkSteelSword", 1, 27, 55, 8));
        // Kobold Mutant
        weaponDictionary.put(1.9, new Sword("knightsEdge", 1, 42, 70, 5));

        // Part 2 (Insect Kingdom)
        weaponDictionary.put(2.0, new Sword("adventurerSword", 1, 15, 25, 100));  // Shop Item
        // Killer Hornet
        weaponDictionary.put(2.1, new Sword("needleSword", 1, 24, 60, 16));
        weaponDictionary.put(2.2, new Sword("stingSword", 1, 36, 65, 8));
        // Cicadean Devil
        weaponDictionary.put(2.3, new Sword("numSword", 1, 27, 75, 14));
        weaponDictionary.put(2.4, new Sword("dumSword", 1, 40.5, 80, 7));
        // Big Mosquito
        weaponDictionary.put(2.5, new Sword("bloodSeekerSword", 1, 32, 90, 13));
        weaponDictionary.put(2.6, new Sword("prickleSword", 1, 48, 95, 7));
        // Elder Centipede
        weaponDictionary.put(2.7, new Sword("twoProngSword", 1, 46, 105, 12));
        weaponDictionary.put(2.8, new Sword("threeProngSword", 1, 69, 110, 6));
        // DA Debugginator
        weaponDictionary.put(2.9, new Sword("bugsCaliber", 1, 128, 150, 3));

        // Part 3 (Ancient Monsters)
        weaponDictionary.put(3.0, new Sword("enchantedSword", 1, 34, 35, 100));
        // Skeletal Terror
        weaponDictionary.put(3.1, new Sword("brittleBoneSword", 1, 47, 80, 20));
        weaponDictionary.put(3.2, new Sword("enchantedCalciumSword", 1, 58, 90, 12));
        // The Unwoven (Mummy)
        weaponDictionary.put(3.3, new Sword("wrappedSword", 1, 53, 95, 11));
        weaponDictionary.put(3.4, new Sword("tombSword", 1, 68, 100, 7));
        // Bone Centaur
        weaponDictionary.put(3.5, new Sword("boneScaleSword", 1, 62, 125, 10));
        weaponDictionary.put(3.6, new Sword("boneSlayerSword", 1, 93, 130, 5));
        // Riddled Sphinx
        weaponDictionary.put(3.7, new Sword("mysteriousSword", 1, 76, 140, 9));
        weaponDictionary.put(3.8, new Sword("theGuardianSword", 1, 114, 150, 5));
        // Unforgiven Banshee
        weaponDictionary.put(3.9, new Sword("GhostBlade", 1, 171, 250, 3));


        /* Elixirs */

        elixirsDictionary.put(1, new HealPot("basicHealthPot", 1, 5, 100));
        elixirsDictionary.put(2, new StrengthBoost("basicStrengthPot", 1, 1.5, 100));
        elixirsDictionary.put(3, new DefenseBoost("basicDefensePot", 1, 2.5, 100));



        /* Items */
        itemsDictionary.put(1, new Items("wood", 1, 95));
        itemsDictionary.put(2, new Items("rock", 1, 92));
        itemsDictionary.put(3, new Items("stick", 1, 89));
        itemsDictionary.put(4, new Items("graphite", 1, 86));
        itemsDictionary.put(5, new Items("dirt", 1, 83));
        itemsDictionary.put(6, new Items("dust", 1, 99));

        /* Monsters */
        // Part 1
        monsterDictionary.put(1.1, new Monster("Boar Knight", 2, 5, new Items[]{weaponDictionary.get(1.1), weaponDictionary.get(1.2), elixirsDictionary.get(1), itemsDictionary.get(1)}));
        monsterDictionary.put(1.2, new Monster("Snake Cannibal", 6, 15, new Items[]{weaponDictionary.get(1.3), weaponDictionary.get(1.4), elixirsDictionary.get(2)}));
        monsterDictionary.put(1.3, new Monster("Bear Tribesman", 10, 30, new Items[]{weaponDictionary.get(1.5), weaponDictionary.get(1.6), elixirsDictionary.get(3)}));
        monsterDictionary.put(1.4, new Monster("Kobold", 18, 50, new Items[]{weaponDictionary.get(1.7), weaponDictionary.get(1.8), elixirsDictionary.get(2)}));
        monsterDictionary.put(1.5, new Monster("Mutant Kobold", 25, 90, new Items[]{weaponDictionary.get(1.9),elixirsDictionary.get(1),elixirsDictionary.get(2),elixirsDictionary.get(3)}));
        /* ChunkData*/
        chunkDictionary.put(1.0, new Chunk("Dark Cave", "it's dark, like your love life. There is also nothing here, just leave..."));
        //Part 1
        chunkDictionary.put(1.1, new Chunk("Whispering Forest", "You have arrived at an enchanted place full of wonders; perhaps death too.", new Monster(monsterDictionary.get(Math.floor(ThreadLocalRandom.current().nextDouble(1.1, 1.2)*10)/10)), new Items(itemsDictionary.get(ThreadLocalRandom.current().nextInt(1, 3))))); //Random monster per floor unless last level, then boss
        chunkDictionary.put(1.2, new Chunk("Valley", "You have found a valley", new Monster(monsterDictionary.get(Math.floor(ThreadLocalRandom.current().nextDouble(1.1, 1.2)*10)/10)), new Items(itemsDictionary.get(ThreadLocalRandom.current().nextInt(1, 3))))); //Fix later
        chunkDictionary.put(1.3, new Chunk("Temple of Ruins", "An abandoned temple. You can see eons of weathering at a glance.", new Monster(monsterDictionary.get(Math.floor(ThreadLocalRandom.current().nextDouble(1.1, 1.3)*10)/10)), new Items(itemsDictionary.get(ThreadLocalRandom.current().nextInt(1, 4)))));
        chunkDictionary.put(1.4, new Chunk("Monstrait", "European-like region, will have dangerous monsters; perhaps good loot?", new Monster(monsterDictionary.get(Math.floor(ThreadLocalRandom.current().nextDouble(1.1, 1.3)*10)/10)), new Items(itemsDictionary.get(ThreadLocalRandom.current().nextInt(1, 4)))));
        chunkDictionary.put(1.5, new Chunk("River", "You have reached a river. Something seems to be in the waters...", new Monster(monsterDictionary.get(Math.floor(ThreadLocalRandom.current().nextDouble(1.1, 1.4)*10)/10)), new Items(itemsDictionary.get(ThreadLocalRandom.current().nextInt(1, 5)))));
        chunkDictionary.put(1.6, new Chunk("Descending Plains", "An endless horizon of grass ahead. It almost seems like a desert", new Monster(monsterDictionary.get(Math.floor(ThreadLocalRandom.current().nextDouble(1.1, 1.4)*10)/10)), new Items(itemsDictionary.get(ThreadLocalRandom.current().nextInt(1, 5)))));
        chunkDictionary.put(1.7, new Chunk("Bewildering Marsh", "One of the best wetlands you can find. Maybe there will be some goods under that treacherous water", new Monster(monsterDictionary.get(Math.floor(ThreadLocalRandom.current().nextDouble(1.1, 1.5)*10)/10)), new Items(itemsDictionary.get(ThreadLocalRandom.current().nextInt(1, 6)))));
        chunkDictionary.put(1.8, new Chunk("Rocky Cliffs", "A rocky terrain we have here. Passing through here would be difficult. Watch out for the monsters!", new Monster(monsterDictionary.get(Math.floor(ThreadLocalRandom.current().nextDouble(1.1, 1.5)*10)/10)), new Items(itemsDictionary.get(ThreadLocalRandom.current().nextInt(1, 7)))));
        chunkDictionary.put(1.9, new Chunk("Arena of Mutant", "You have entered a arena full of mutants. Be careful, these guys are powerful compared to the previous monsters!", new Monster(monsterDictionary.get(1.5)), new Elixirs(elixirsDictionary.get(ThreadLocalRandom.current().nextInt(1, 4)))));

        /* descriptionData */
        descriptionDictionary.put(1, "You were walking and exploring the area, but for some reason, you saw a hole and you decided to jump right in. Not only did you break some bones, but it also seems like you just turned into a dummy. Good Luck NPC, thank me later!");
        descriptionDictionary.put(2, "A monkey just ran into you. Imagine sharing one brain cell with a money. Couldn't be me honestly, but hey I won't judge. Oh yeah you are also stuck here for a while so let's chat for a bit, or not.");
        descriptionDictionary.put(3, "Look where you are going before you go. Better be safe than sorry. Who knows when you will walk into the next hole lol");
        descriptionDictionary.put(4, "Dev Log #1: I HATE YOUR COMBAT SYSTEM!");
        descriptionDictionary.put(5, "Colby's haunting message to Phillip: \"That $10 pencil be looking nice though. Only if you still had it, that is...\"");
        descriptionDictionary.put(6, "What goes up, but doesn't go down? Probably you, in a few minutes. Wish you good luck where ever you are best buddy!");
        descriptionDictionary.put(7, "What are you doing playing a text rpg and reading this? Go hit the damn gym bro. You are stilling down and letting your muscles rot just like your life-story. Just leave already you twig!");
        descriptionDictionary.put(8, "Hey! Yeah you! With that slouch back staring into the screen hoping that something happens? Well there is nothing here. Keep reading and maybe you will find something. It can't be that bad right? Oh you are still here, leave already wimbo!");
        descriptionDictionary.put(9, "The rising dawn can make one wonder sometimes on what they are doing. Go outside and touch some grass and maybe you will understand this message nerd.");
        descriptionDictionary.put(10, "Closing in on this place, you wonder what the brim of existence really means. Well who knows?");
        descriptionDictionary.put(11, "Dev Log #2: Kill yourself plz");
        descriptionDictionary.put(12, "Dev Log #3: This is why we get are single. We need a life.");
        descriptionDictionary.put(13, "");
        descriptionDictionary.put(14, "");
        descriptionDictionary.put(15, "");
        descriptionDictionary.put(16, "");
        descriptionDictionary.put(17, "");
        descriptionDictionary.put(18, "");
        descriptionDictionary.put(19, "");
        descriptionDictionary.put(20, "");
        descriptionDictionary.put(21, "");
        descriptionDictionary.put(22, "");
        descriptionDictionary.put(23, "");
        descriptionDictionary.put(24, "");
        descriptionDictionary.put(25, "");
        descriptionDictionary.put(26, "");
        descriptionDictionary.put(27, "");
        descriptionDictionary.put(28, "");
        descriptionDictionary.put(29, "");
        descriptionDictionary.put(30, "");
        descriptionDictionary.put(31, "");
        descriptionDictionary.put(32, "");
        descriptionDictionary.put(33, "");
        descriptionDictionary.put(34, "");
        descriptionDictionary.put(35, "");
        descriptionDictionary.put(36, "");
        descriptionDictionary.put(37, "");
        descriptionDictionary.put(38, "");
        descriptionDictionary.put(39, "");
        descriptionDictionary.put(40, "");
        descriptionDictionary.put(41, "");
        descriptionDictionary.put(42, "");
        descriptionDictionary.put(43, "");
        descriptionDictionary.put(44, "");
        descriptionDictionary.put(45, "");
        descriptionDictionary.put(46, "");
        descriptionDictionary.put(47, "");
        descriptionDictionary.put(48, "");
        descriptionDictionary.put(49, "");
        descriptionDictionary.put(50, "");
    }


    public static HashMap<Double, Armor> getArmorDictionary() {
        return armorDictionary;
    }

    public static HashMap<Double, Weapons> getWeaponDictionary() {
        return weaponDictionary;
    }

    public static HashMap<Double, Monster> getMonsterDictionary() {
        return monsterDictionary;
    }
    public static HashMap<Double, Chunk> getMapDictionary() {
        return chunkDictionary;
    }



    public static HashMap<Integer, Elixirs> getElixirDictionary() {
        return elixirsDictionary;
    }

    public static HashMap<Integer, Items> getItemsDictionary() {
        return itemsDictionary;
    }

    public static HashMap<Integer, String> getDescriptionDictionary()
    {
        return descriptionDictionary;
    }
}