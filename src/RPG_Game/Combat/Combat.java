package RPG_Game.Combat;
import RPG_Game.Chars.Player;
import RPG_Game.Chars.Monster;
import RPG_Game.Components.MapGeneration.Chunk;
import RPG_Game.Sound.SoundHandler;
import clientPackage.RPG_Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;


public class Combat implements Runnable {
    private Player player;
    private ImageIcon playerModel;
    private JLabel playerLabel;
    private JLabel playerHitBox;
    private Monster monster;
    private ImageIcon monsterModel;
    private JLabel monsterLabel;
    private JLabel monsterHitBox;

    private RPG_Client combatClient;
    private CombatPlane battleZone;
    private boolean isPlayersTurn;

    private SoundHandler sound;

    private final KeyListener combatInput = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            int moveDistance;
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                    moveDistance = Math.min(5, player.getCombatYPos());
                    if (player.getCombatYPos() > 0)
                        player.setCombatPos(player.getCombatXPos(), player.getCombatYPos() - moveDistance);
                }
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                    moveDistance = Math.min(5, player.getCombatXPos());
                    if (player.getCombatXPos() > 0)
                        player.setCombatPos(player.getCombatXPos() - moveDistance, player.getCombatYPos());
                }
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                    moveDistance = Math.min(5, combatClient.getFrameHeight() - playerModel.getIconHeight() - player.getCombatYPos());
                    if (player.getCombatYPos() < combatClient.getFrameHeight() - playerModel.getIconHeight())
                        player.setCombatPos(player.getCombatXPos(), player.getCombatYPos() + moveDistance);
                }
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                    moveDistance = Math.min(5, combatClient.getFrameWidth() - playerModel.getIconWidth() - player.getCombatXPos());
                    if (player.getCombatXPos() < combatClient.getFrameWidth() - playerModel.getIconWidth())
                        player.setCombatPos(player.getCombatXPos() + moveDistance, player.getCombatYPos());
                }
            }
            //combatClient.print();
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    };

    public Combat(Player player, Monster monster) {
        this.battleZone = new CombatPlane();
        this.player = player;
        this.monster = monster;
        this.combatClient = new RPG_Client();
        this.sound = new SoundHandler();
        initializeComponents();
        battle();
    }

    public Combat(Player player, Monster monster, Chunk biome) {
        this.battleZone = new CombatPlane(biome);
        this.player = player;
        this.monster = monster;
        this.combatClient = new RPG_Client();
        this.sound = new SoundHandler();
        initializeComponents();
        battle();
    }

    public void run() {
        Thread monsterAttack = new Thread(monster);

        Timer update = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //System.out.println("player pos: (" + targetPlayer.getCombatXPos() + "," + targetPlayer.getCombatYPos() + ")");
                //System.out.println("monster pos: (" + getCombatXPos() + "," + getCombatYPos() + ")");
                System.out.println(monsterDistanceToPlayer());

                if (monsterDistanceToPlayer() > 2) {
                    if (player.getCombatYPos() > monster.getCombatYPos())
                        monster.setCombatPos(monster.getCombatXPos(), monster.getCombatYPos() + 1);
                    if (player.getCombatYPos() < monster.getCombatYPos())
                        monster.setCombatPos(monster.getCombatXPos(), monster.getCombatYPos() - 1);

                    if (player.getCombatXPos() > monster.getCombatXPos())
                        monster.setCombatPos(monster.getCombatXPos() + 1, monster.getCombatYPos());
                    if (player.getCombatXPos() < monster.getCombatXPos())
                        monster.setCombatPos(monster.getCombatXPos() - 1, monster.getCombatYPos());
                } else {

                }
            }
        };
        update.scheduleAtFixedRate(task, 0,30);
    }

    public void battle() {

        //combatClient.getFrame().addKeyListener(combatInput);
        combatClient.addKeyConsole(combatInput, true);
        Thread monsterBehavior = new Thread(this);
        monsterBehavior.start();
        initialize(this.player, this.monster);
        sound.runMusic("Music/fightMusic.wav");

    }

    public boolean isDead(Player player) {
        return player.getCharacterStats().getHP() <= 0;
    }

    public boolean isDead(Monster monster) {
        return monster.getCharacterStats().getHP() <= 0;
    }

    public void playerAttack() {
        monster.takeDMG(42069);
    }

    public void monsterAttack() {
        player.takeDMG(42069);
    }

    public void initializeComponents() {
        playerModel = new ImageIcon("Images/player.png");
        monsterModel = new ImageIcon("Images/monster1.png");
        playerLabel = new JLabel();
        playerHitBox = new JLabel();
        monsterLabel = new JLabel();
        monsterHitBox = new JLabel();
        JLayeredPane pane = new JLayeredPane();

        playerLabel.setBounds(0, 0, 16, 16);
        monsterLabel.setBounds(0, 0, 16, 16);

        playerLabel.setIcon(playerModel);
        monsterLabel.setIcon(monsterModel);
        pane.add(playerLabel,2,0);
        pane.add(monsterLabel,1,0);

        combatClient.addComponent(pane);
        //combatClient.addComponent(monsterLabel);
        //combatClient.addComponent(playerLabel);



        java.util.Timer update = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                    playerLabel.setLocation(player.getCombatXPos(), player.getCombatYPos());
                    playerLabel.setLocation(playerLabel.getLocation());
                    monsterLabel.setLocation(monster.getCombatXPos(), monster.getCombatYPos());
                    monsterLabel.setLocation(monsterLabel.getLocation());
            }
        };
        update.scheduleAtFixedRate(task, 0,1);
    }

    public void initialize(Player p, Monster m) {
        p.setCombatPos((combatClient.getFrameWidth()-playerModel.getIconWidth())/2, combatClient.getFrameHeight()-playerModel.getIconHeight());
        m.setCombatPos((combatClient.getFrameWidth()-monsterModel.getIconWidth())/2, 0);
        m.setTargetPlayer(p);
    }

    /**
     *  ACCESSOR METHODS
     */

    private int monsterDistanceToPlayer() {
        return (int)(Math.sqrt(Math.pow((monster.getCombatXPos()-player.getCombatXPos()),2)+Math.pow(monster.getCombatYPos()-player.getCombatYPos(),2)));
    }

    public RPG_Client getGUI() {
        return combatClient;
    }
}
