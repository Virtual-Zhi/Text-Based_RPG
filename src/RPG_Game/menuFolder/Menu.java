package RPG_Game.menuFolder;


import RPG_Game.Chars.Player;
import RPG_Game.Components.MapGeneration.Map;
import RPG_Game.RPG_Server;
import clientPackage.RPG_Client;

public class Menu {

    private String menuText;
    private Player player;

    private RPG_Client menuBase, main;

    private Map map;

    private Inventory inventory;
    private Option option;
    private Stats stats;

    public Menu() {
    }

    ;

    public Menu(Player user, RPG_Client mainGame, Map m) {
        this.player = user;
        main = mainGame;
        map = m;
        menuText = "Hello, " + player.getName() + "\n" +
                """
                        Game Paused, Menu open
                        --------------------------------------------------------------------------------------------------------

                        1.) Unpause
                        2.) Inventory
                        3.) Stats
                        4.) Options
                        5.) NextWorld
                        6.) OpenWorld Map
                        7.) Quit
                                        
                        --------------------------------------------------------------------------------------------------------""";
        inventory = user.getPlayerInv();
        stats = user.getCharacterStats();
        option = new Option();
        menuBase = new RPG_Client();
        menuBase.setVisible(false);
        menuBase.println(menuText);
    }

    public void openMenu() {
        menuBase.setVisible(true);
        String x = menuBase.next("Choose an option...");
        if (x == null) {
            openMenu();
        } else if (x.equalsIgnoreCase("1")) {
            menuBase.close();
        } else if (x.equalsIgnoreCase("2")) {
            openInventory(1);
            openMenu();
        } else if (x.equalsIgnoreCase("3")) {
            openStats();
        } else if (x.equalsIgnoreCase("4")) {
            openOptions();
        } else if (x.equalsIgnoreCase("5")) {
            //player world upgrade, map = new map
        } else if (x.equalsIgnoreCase("6")) {
            map.openMap(2);
            openMenu();
        } else if (x.equalsIgnoreCase("7")) {
            main.exit();
        } else {
            openMenu();
        }
    }

    public void openInventory(int loadPage) {
        RPG_Client inventoryPage = new RPG_Client();
        String nextOption;
        int currentPage = loadPage;
        do
        {
            inventoryPage.println(player.getPlayerInv().toString(currentPage));
            //What player wants to do in this page function here.
            inventoryPage.println("--------------------------------------------------------------------------------------------------------");
            inventoryPage.println("""
                1.) Next Page
                2.) Previous Page
                3.) Exit
                """);
            nextOption = inventoryPage.next("Choose");
            if (nextOption.equals("1"))
            {
                if (currentPage>=4)
                {
                    currentPage = 4;
                } else
                {
                    currentPage++;
                }
            } else if (nextOption.equals("2"))
            {
                if (currentPage<=1)
                {
                    currentPage=1;
                } else
                {
                    currentPage--;
                }
            }
            inventoryPage.clear();
        } while (nextOption.equals("1") || nextOption.equals("2"));
        inventoryPage.close();
    }

    public void openStats() {
        //prompt to use skillpoints
        RPG_Client statsPage = new RPG_Client();
        statsPage.println(stats.toString());
        statsPage.println("""
                --------------------------------------------------------------------------------------------------------
                1.) Use skill points
                2.) Exit
                """);
        String s = statsPage.next("Pick an option");
        if (s.equalsIgnoreCase("1")) {
            int amount;
            do {
                amount = Integer.parseInt(statsPage.next("Enter amount of skill points."));
            } while (amount>stats.getSkillPoints() && stats.getSkillPoints()>0);
            do {
                statsPage.clear();
                statsPage.println(stats.toString());
                statsPage.println("""
                    --------------------------------------------------------------------------------------------------------
                    1.) (+) Strength
                    2.) (+) Defense
                    3.) (+) Agility
                    4.) (+) Vitality
                    5.) Cancel
                    """);
                s = statsPage.next("Use skill points?");
                if (s.charAt(0)==('1'))
                {
                    stats.addStrength(amount*0.1);
                    stats.addSkillPoints(-amount);
                } else if (s.charAt(0)=='2')
                {
                    stats.addDefense(amount*0.1);
                    stats.addSkillPoints(-amount);
                } else if (s.charAt(0)=='3')
                {
                    stats.addAgility(amount*0.1);
                    stats.addSkillPoints(-amount);
                } else if (s.charAt(0)=='4')
                {
                    stats.addMaxHP(amount*5);
                    stats.addSkillPoints(-amount);
                }
            } while (!s.equals("5"));
            statsPage.close();
            openStats();
        } else
        {
            statsPage.close();
            openMenu();
        }
    }

    public void openOptions() {

    }

    public void newWorld() {
        //Remake map and everything
    }

    public void Quit() {
        main.exit();
    }
}
