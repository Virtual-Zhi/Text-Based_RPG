package RPG_Game.menuFolder;

import RPG_Game.Components.Armor.Armor;
import RPG_Game.Components.Items;
import RPG_Game.Components.Materials.Elixirs;
import RPG_Game.Components.Weapons.Weapons;
import clientPackage.RPG_Client;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Items> pInv;
    private ArrayList<Weapons> wInv;
    private ArrayList<Armor> aInv;

    public Inventory() {
        pInv = new ArrayList<Items>();
        wInv = new ArrayList<Weapons>();
        aInv = new ArrayList<Armor>();

    }

    public void add(Elixirs thing) {
        for (Items items : pInv) {
            if (items instanceof Elixirs && items == thing) {
                ((Elixirs) items).addAmount(thing.getAmountOfItem());
                return;
            }
        }
        pInv.add(thing);
    }

    public void add(Items thing) {
        for (Items items : pInv) {
            if (items instanceof Elixirs)
            {
                add((Elixirs) thing);
            } else if (items instanceof Weapons)
            {
                add((Weapons) thing);
            } else if (items instanceof Armor)
            {
                add((Armor) thing);
            } else
            {
                items.addAmount(thing.getAmountOfItem());
                return;
            }
        }
        pInv.add(thing);
    }

    public void add(Armor thing) {
        for (Armor items : aInv) {
            if (items == thing) {
                items.addAmount(thing.getAmountOfItem());
                return;
            }
        }
        aInv.add(thing);
    }

    public void add(Weapons thing) {
        for (Weapons items : wInv) {
            if (items == thing) {
                items.addAmount(thing.getAmountOfItem());
                return;
            }
        }
        wInv.add(thing);
    }

    /**
     * Inventory template: (new JFrame)
     * Category (Page 1/?)
     * <-- Prev             Next -->
     * (Esc to exit)
     * w/ Equip and Use functions on the side
     * <p>
     * <p>
     * Menu template:
     * 1) Stats
     * 2) Open Inventory
     * 3) Options
     * (Esc to exit)
     */

    public void deleteOne(ArrayList<Object> objects, String name) {
        for (Object x : objects) {
            if (x instanceof Armor && ((Armor) x).getName().equalsIgnoreCase(name)) {
                ((Armor) x).deleteAmount(1);
            }
            if (x instanceof Elixirs && ((Elixirs) x).getName().equalsIgnoreCase(name)) {
                ((Elixirs) x).deleteAmount(1);
            }
            if (x instanceof Weapons && ((Weapons) x).getName().equalsIgnoreCase(name)) {
                ((Weapons) x).deleteAmount(1);
            }
        }
    }

    public void massDelete(ArrayList<Object> objects, String name, int amount) {
        for (Object x : objects) {
            if (x instanceof Armor && ((Armor) x).getName().equalsIgnoreCase(name)) {
                ((Armor) x).deleteAmount(amount);
            }
            if (x instanceof Elixirs && ((Elixirs) x).getName().equalsIgnoreCase(name)) {
                ((Elixirs) x).deleteAmount(amount);
            }
            if (x instanceof Weapons && ((Weapons) x).getName().equalsIgnoreCase(name)) {
                ((Weapons) x).deleteAmount(amount);
            }
        }

    }

    /**
     * page 1 = potions
     * page 2 = items
     * page 3 = armor
     * page 4 = weapons
     */
    public String toString(int pageNumber) {
        StringBuilder inv1 = new StringBuilder();
        StringBuilder inv2 = new StringBuilder();
        StringBuilder inv3 = new StringBuilder();
        StringBuilder inv4 = new StringBuilder();

        inv1.append("\t\t------------>\nElixirs\n");
        inv1.append("\n| Name | Description | Amount\n----------------------------------------------------------------\n");
        for (Items item : pInv) {
            if (item instanceof Elixirs) {
                inv1.append("\n| ").append(item.getName()).append(" | ").append(item.getAmountOfItem());
            }
        }


        inv4.append("<------------\nWeapons\n");
        inv4.append("\n| Name | Description (Attack or defense) | Amount\n----------------------------------------------------------------\n");
        for (Weapons item : wInv) {
            inv4.append("\n| ").append(item.getName()).append(" | Attack: ").append(item.getDamage()).append(" | ").append(item.getAmountOfItem());
        }


        inv2.append("<------------\t\t------------>\nItems\n");
        inv2.append("\n| Name | Amount\n------------------------------------------------------------\n");

        for (Items item : pInv) {
            if (!(item instanceof Elixirs)) {
                inv2.append("| ").append(item.getName()).append(" | ").append(item.getAmountOfItem());
            }
        }
        inv3.append("<------------\t\t------------>\nArmor\n");
        inv3.append("\n| Name | Description (Attack or defense) | Amount\n----------------------------------------------------------------\n");
        for (Armor item : aInv) {
            inv3.append("\n| ").append(item.getName()).append(" | Defense: ").append(item.getDefense()).append(" | ").append(item.getAmountOfItem());
        }
        if (pageNumber==1)
        {
            return inv1.toString();
        } else if (pageNumber==2)
        {
            return inv2.toString();
        } else if (pageNumber==3)
        {
            return inv3.toString();
        } else
        {
            return inv4.toString();
        }
    }

}
