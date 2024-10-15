package RPG_Game;

import RPG_Game.Chars.*;
import RPG_Game.Combat.*;
import RPG_Game.Components.Items;
import RPG_Game.Components.MapGeneration.Chunk;
import RPG_Game.Components.MapGeneration.Map;
import RPG_Game.Components.Materials.DefenseBoost;
import RPG_Game.Components.Materials.Elixirs;
import RPG_Game.Components.assetData;
import RPG_Game.Sound.*;
import RPG_Game.menuFolder.Menu;
import clientPackage.RPG_Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class RPG_Server {
    private final RPG_Client client;
    private final Player user;
    private Menu menu;
    private final SoundHandler musicHandler;

    private String userName;
    private Map m;

    private boolean login;


    public RPG_Server() {
        client = new RPG_Client();
        user = new Player();
        musicHandler = new SoundHandler();
    }

    public void start() {
        loader();
        startScreen();
        client.print("""
                1.) Open World
                2.) Story Mode
                                    
                --------------------------------------------------------------------------------------------------------
                """);
        String s = client.next("Choose one");
        if (s.equals("1")) {
            musicHandler.stopMusic();
            musicHandler.runMusic("Music/backMusicCalmTone.wav");
            client.clear();
            user.equip(assetData.getWeaponDictionary().get(1.4));
            //give instructions
            String v = """
                    Disclaimer: This is open world mode. You must use your arrow keys to move when prompted to.
                    Each time you move, you will be prompted with either a item to pick up or a monster to fight.
                    Good luck!
                    --------------------------------------------------------------------------------------------------------
                    """;
            //client.println(v.charAt(1));
            client.println(v);
            client.println("");
            client.println("You woke up in the " + m.getMap()[user.getYPos()][user.getXPos()].getName());
            client.println(m.getMap()[user.getYPos()][user.getXPos()].getDescription());
            client.println("\n--------------------------------------------------------------------------------------------------------");
            client.println("Choose an direction to go. (use arrow keys, escape for menu, p for map)");
            client.setKeyConsole(true, true);
            client.setKeyConsole(true, false);
        } else {
            storyMode();
        }

        //menu.getMenuText();
        //Run Music
        //getMap
        //DefenseBoost boost = new DefenseBoost("defensePot", 2, Elixirs.Rarity.LESSER, 10,1);
        //System.out.println(client.nextOption("Eat some food?"));
        //client.println("" + Math.floor(ThreadLocalRandom.current().nextDouble(1.1, 1.5)*10)/10);
        //enterCombat(user, assetData.getMapDictionary().get(1.1).getMonster());
        //enterCombat(user, new Monster());
        //client.setKeyConsole(false);
        //new Combat(user, new Monster());
    }

    private boolean startScreen() {
        boolean x = true;
        String startScreen = """
                --------------------------------------------------------------------------------------------------------
                                             
                1.) Start Game
                2.) Quit Game
                                             
                --------------------------------------------------------------------------------------------------------
                """;
        musicHandler.runMusic("Music/FantasyBackMusic1.wav");
        client.print(startScreen);
        String response = client.next("Choose one");
        if (response == null) {
            client.exit();
            return false;
        }
        if (response.equals("1") && !login) {
            x = startScreen();
        } else if (response.equals("2")) {
            client.exit();
            return false;
        }
        return x;
    }

    public void openWorld() {
        client.clear();
        Chunk chunk = m.getMap()[user.getYPos()][user.getXPos()];
        client.println("\nYou arrive at the " + chunk.getName());
        client.println(chunk.getDescription());
        int test = (int) (Math.random() * 3);
        if (test == 0 && chunk.getMonster() != null && user.getCharacterStats().getHP()>0) {
            //fight
            System.out.print(chunk.getMonster().getName());
            musicHandler.pauseMusic(true);
            client.println("FIGHT!");
            String result = enterCombat(user, chunk.getMonster());
            if (result.equals("Fleed")) {
                client.println("You have successfully ran away!");
            } else if (result.equals("playerDed")) {
                //Game over. Restart
                //DO SOMETHING  
            } else if (result.equals("Won")) {
                chunk.monsterKilled();
                //Drop loots
            }
            /*Combat fight = new Combat(user, chunk.getMonster());
            if (fight.isDead(user))
            {
                fight=null;
            } else if (fight.isDead(chunk.getMonster()))
            {
                fight=null;
            }*/

            musicHandler.pauseMusic(false);
        } else if (test == 2 && chunk.getItem() != null) {
            //Pick up item
            client.println("PICK UP ITEM!");
            if (chunk.getItem() instanceof Elixirs) {
                user.getPlayerInv().add(new Elixirs((Elixirs)chunk.getItem()));
            } else {
                user.getPlayerInv().add(new Items(chunk.getItem()));
            }
        } else {
            //Add another else and prints funny descriptions. Immobilizes the player so they can't just spam the move key without reading it.
            client.println();
            client.println(assetData.getDescriptionDictionary().get((int) (Math.random() * 51) + 1));

        }

        client.println("\n--------------------------------------------------------------------------------------------------------");
        client.println("Choose an direction to go. (use arrow keys, escape for menu, p for map)\n\nYou can move again in 5 seconds");
        Timer t = new Timer(0, null);
        t.setInitialDelay(5000);
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                client.setKeyConsole(true, true);
                t.stop();
            }
        });
        t.start();
    }

    private void storyMode() {

    }

    private void loader() {
        assetData.load();
        //Load Map
        m = new Map(user);
        //Load Menu
        menu = new Menu(user, client, m);
        // When load menu, you must set menu equal to null therefore deleting the object after unPausing. Pause main game when open menu.
        login = login();
    }

    private boolean login() {
        //Logins in player
        return true;
    }

    private String enterCombat(Player player, Monster monster) {
        SoundHandler battleMusic = new SoundHandler();
        battleMusic.runMusic("Music/fightMusic.wav");
        boolean winner = false; //false == player turn, true == monster attack
        RPG_Client battle = new RPG_Client();
        //Combat battle = new Combat(player, monster);

        do {
            String playerGui = "|\tPLAYER\n" +
                    "| Level: " + player.getCharacterStats().getLevel() + ", " + player.getName() + "\n" +
                    "| Health: " + player.getCharacterStats().getHP() + "/" + player.getCharacterStats().getMaxHP() + "\n" +
                    "-------------------------------------------------------------------------------------------------------";
            String monsterGui = "|\tMONSTER\n" +
                    "| Level: " + monster.getCharacterStats().getLevel() + ", " + monster.getName() + "\n" +
                    "| Health: " + monster.getCharacterStats().getHP() + "/" + monster.getCharacterStats().getMaxHP() + "\n" +
                    "-------------------------------------------------------------------------------------------------------";
            battle.println("-------------------------------------------------------------------------------------------------------");
            battle.println(monsterGui);
            battle.println(playerGui);
            battle.println("""
                    | 1.) Fight             2.) Run
                    |-------------------------------------------------------------------------------------------------------
                    | 3.) Inventory      4.) Menu
                    """);
            String response;
            do {
                response = battle.next("Choose an option");
                switch (response) {
                    case "1" -> {
                        //Round off Error
                        double damage = Math.round(((player.getAttack() - (player.getAttack() * (monster.getArmor() / 100))) * 100) / 100); //Random value between
                        damage *= 1.0 + Math.min(0.5, new Random().nextDouble());
                        damage = 1.5 + new Random().nextDouble(damage);
                        damage = (double) Math.round(damage * 100) / 100;
                        monster.takeDMG(Math.min(damage, monster.getCharacterStats().getHP())); //Values in between
                        battle.showMessage("You've hit the monster with a dazzling attack for " + damage);
                    }
                    case "2" -> {
                        double percentage = player.getCharacterStats().getAgility() / monster.getCharacterStats().getAgility();
                        int x = new Random().nextInt(101);
                        if ((x / 100.0) * 100 >= percentage || percentage >= 100) {
                            battle.showMessage("You have fled away from the monster");
                            battle.close();
                            battleMusic.stopMusic();
                            return "Fleed";
                        } else {
                            battle.showMessage("You couldn't get away unfortunately...");
                        }
                    }
                    case "3" -> {
                        menu.openInventory(1);
                    }
                    case "4" -> {
                        menu.openMenu();
                    }
                }
            } while ((Integer.parseInt(response) >= 4 || Integer.parseInt(response)==3));
            battle.clear();
            battle.println("-------------------------------------------------------------------------------------------------------");
            playerGui = "|\tPLAYER\n" +
                    "| Level: " + player.getCharacterStats().getLevel() + ", " + player.getName() + "\n" +
                    "| Health: " + player.getCharacterStats().getHP() + "/" + player.getCharacterStats().getMaxHP() + "\n" +
                    "-------------------------------------------------------------------------------------------------------";
            monsterGui = "|\tMONSTER\n" +
                    "| Level: " + monster.getCharacterStats().getLevel() + ", " + monster.getName() + "\n" +
                    "| Health: " + monster.getCharacterStats().getHP() + "/" + monster.getCharacterStats().getMaxHP() + "\n" +
                    "-------------------------------------------------------------------------------------------------------";
            battle.println(monsterGui);
            battle.println(playerGui);
            if (monster.getCharacterStats().getHP() > 0) {
                double damage = Math.round(((monster.getAttack() - (monster.getAttack() * (player.getArmor() / 100))) * 100) / 100); //Random value between
                damage *= 1.0 + Math.min(0.5, new Random().nextDouble());
                damage -= 1 + Math.random() * 2;
                damage = (double) Math.round(damage * 100) / 100;
                player.takeDMG(Math.min(damage, player.getCharacterStats().getHP())); //Values in between
                battle.showMessage(monster.getName() + " straight up smacked you. Damage: " + damage);
            }

            battle.clear();
            if (player.getCharacterStats().getHP() <= 0 || monster.getCharacterStats().getHP() <= 0) {
                winner = true;
            }
        } while (!winner);
        String playerGui = "|\tPLAYER\n" +
                "| Level: " + player.getCharacterStats().getLevel() + ", " + player.getName() + "\n" +
                "| Health: " + player.getCharacterStats().getHP() + "/" + player.getCharacterStats().getMaxHP() + "\n" +
                "-------------------------------------------------------------------------------------------------------";
        String monsterGui = "|\tMONSTER\n" +
                "| Level: " + monster.getCharacterStats().getLevel() + ", " + monster.getName() + "\n" +
                "| Health: " + monster.getCharacterStats().getHP() + "/" + monster.getCharacterStats().getMaxHP() + "\n" +
                "-------------------------------------------------------------------------------------------------------";
        battle.println("-------------------------------------------------------------------------------------------------------");
        battle.println(monsterGui);
        battle.println(playerGui);
        battleMusic.stopMusic();
        battle.showMessage(player.getCharacterStats().getHP() <= 0 ? "You died..." : "You have defeated the monster.");
        battle.close();
        if (player.getCharacterStats().getHP() <= 0) {
            return "playerDed";
        } else {
            return "Won";
        }

        //"\t\t\t|\tLevel: "+monster.getCharacterStats().getLevel()+", "+monster.getName()+

    }

    public Player getUser() {
        return user;
    }

    public Map getMap() {
        return m;
    }

    public Menu getMenu() {
        return menu;
    }

}
