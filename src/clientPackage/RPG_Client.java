package clientPackage;

import RPG_Game.Chars.Player;
import RPG_Game.Combat.Combat;
import RPG_Game.Components.MapGeneration.Map;
import RPG_Game.RPG_Server;

import javax.swing.*;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Style;
import java.awt.*;
import java.awt.event.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.*;
import java.util.TimerTask;
import javax.swing.Timer;

public class RPG_Client
{
    private static RPG_Server server;
    private JFrame frame;
    private final JTextPane client;
    //private final JTextField input;
    private final JScrollPane scrollPane;

    private final StyledDocument document;
    private String nextString;

    //private boolean trace = false;
    //private boolean isPerformed = false;

    private static boolean isMainGame = true;


    private final KeyListener keyConsole = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            setKeyConsole(false, true);
            Player p = server.getUser();
            Map m = server.getMap();
            if (keyEvent.getKeyCode()==KeyEvent.VK_UP || keyEvent.getKeyCode()==KeyEvent.VK_W)
            {
                print("up arrow!");
                m.updatePlayerIndexOnMap(1);
                server.openWorld();
                //Update chunk in player
                //Move player in the map
            } else if (keyEvent.getKeyCode()==KeyEvent.VK_DOWN || keyEvent.getKeyCode()==KeyEvent.VK_S)
            {
                m.updatePlayerIndexOnMap(2);
                server.openWorld();
            } else if (keyEvent.getKeyCode()==KeyEvent.VK_LEFT || keyEvent.getKeyCode()==KeyEvent.VK_A) {

                m.updatePlayerIndexOnMap(3);
                server.openWorld();
            } else if (keyEvent.getKeyCode()==KeyEvent.VK_RIGHT || keyEvent.getKeyCode()==KeyEvent.VK_D)
            {
                m.updatePlayerIndexOnMap(4);
                server.openWorld();
            } else
            {
                setKeyConsole(true, true);
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    };




    private final KeyListener SecondKeyConsole = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            setKeyConsole(false, false);
            Player p = server.getUser();
            Map m = server.getMap();
            if (keyEvent.getKeyCode()==KeyEvent.VK_ESCAPE)
            {
                server.getMenu().openMenu();
                setKeyConsole(true, false);
                //Open Menu
            } else if (keyEvent.getKeyCode()==KeyEvent.VK_P)
            {
                m.openMap(2);
                setKeyConsole(true, false);
                //Open Map
            }else
            {
                setKeyConsole(true, false);
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    };

    /*private final ActionListener actionConsole = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent isTyping) {
            String text="";
            try {
                text = new Scanner(input.getText()).nextLine();
            } catch (NoSuchElementException ignore){}
            if (text.length()>0) {
                println(text);
                doCommand(text);
                input.setText("");
                nextString = text;
                //isPerformed = true;
                scrollDown();
            }
        }
    };*/


    public static void main(String[] args)
    {

        server = new RPG_Server();
        server.start();
    }

    public RPG_Client()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {}

        frame = new JFrame();
        frame.setTitle("Main Game");
        if (isMainGame) {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setUndecorated(false);
            frame.setIconImage(new ImageIcon("Images/Icon.png").getImage());
            isMainGame = false;
        } else {
            frame.setUndecorated(true);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        client = new JTextPane();
        client.setEditable(false);
        client.setFont(new Font("Gumdrop", Font.PLAIN, 18));
        client.setOpaque(false);
        document = client.getStyledDocument();

        /*input = new JTextField();
        input.setEditable(true);
        input.setForeground(Color.BLACK);
        input.setCaretColor(Color.GREEN);
        input.setFont(new Font("Gumdrop", Font.PLAIN, 16));

        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });*/

        scrollPane = new JScrollPane(client);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);


        //frame.add(input, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.getContentPane().setBackground(new Color(30, 30, 32));



        //frame.setSize(800,600); //Must use this first before setting location
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        //client.setSize(client.getWidth(), client.getHeight()-200);


    }

    public void addKeyConsole(KeyListener x, boolean on)
    {
        if (on)
        {
            client.addKeyListener(x);
        } else
        {
            client.removeKeyListener(x);
        }
    }

    public void setKeyConsole(boolean x, boolean first)
    {
        if (x && first)
        {
            client.addKeyListener(keyConsole);
        } else if (!x && first)
        {
            client.removeKeyListener(keyConsole);
        } else if (x)
        {
            client.addKeyListener(SecondKeyConsole);
        } else {
            client.removeKeyListener(SecondKeyConsole);
        }
    }

    public void addHealthBar() //W.I.P
    {
        JPanel healthBarPane1 = new JPanel();
        healthBarPane1.setBounds(50,500,300,15);
        healthBarPane1.setBackground(Color.GREEN);
        frame.add(healthBarPane1);
        JProgressBar bar = new JProgressBar(0,20);
        bar.setPreferredSize(new Dimension(200,15));
        healthBarPane1.add(bar);
    }

    public void Wait(double k)
    {
        long time0, time1;
        time0 = System.currentTimeMillis();
        do //Checks
        {
            time1=System.currentTimeMillis();
        }
        while ((time1-time0) <k *1000); //Repeat
    }

    public void scrollUp()
    {
        client.setCaretPosition(0);
    }

    public void scrollDown()
    {
        client.setCaretPosition(client.getDocument().getLength());
    }

    public void print(String x)
    {
        print(x,false,new Color(255,255,255));
    }

    public void slowPrint(String x)
    {
        for (int i = 0; i<x.length(); i++)
        {
            print(x.charAt(i)+"",false,new Color(255,255,255));
            try {
                Thread.sleep(15);
            } catch (InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void print(int x)
    {
        print(String.valueOf(x));
    }
    public void print(String x, boolean trace, Color c)
    {
        Style style = client.addStyle("Style", null);
        StyleConstants.setForeground(style, c);

        if (trace)
        {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String caller = elements[0].getClassName();

            x = caller + " -> " + x;
        }
        try
        {
            document.insertString(document.getLength(), x, style);
        } catch (Exception ex) {}
        scrollDown();
    }

    public void println() {
        println("");
    }
    public void println(String x)
    {
        print(x+"\n");
    }

    public void println(int x)
    {
        print(String.valueOf(x)+"\n");
    }
//    public void println(String x, boolean trace, Color c)
//    {
//        for (int i = 0; i<x.length(); i++)
//        {
//            print(x.charAt(i)+"",false,new Color(255,255,255));
//            try {
//                Thread.sleep(25);
//            } catch (InterruptedException ex)
//            {
//                Thread.currentThread().interrupt();
//            }
//        }
//        print("\n",false,new Color(255,255,255));
//    }

    public void clear()
    {
        try
        {
            document.remove(0, document.getLength());
        } catch (Exception ex) {}
    }

    /*public void doCommand(String x)
    {
        final String[] commands = x.split(" ");

        try
        {
            if (commands[0].equalsIgnoreCase("clear") || commands[0].equalsIgnoreCase("cls"))
            {
                clear();

            } else if (commands[0].equalsIgnoreCase("popup"))
            {
                String message = "";
                for (int i = 0; i<commands.length; i++)
                {
                    message+=commands[i];
                    if(i!=commands.length-1)
                    {
                        message+=" ";
                    }
                }
                JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
            } else if (commands[0].equalsIgnoreCase("deactivate"))
            {

            } else if (commands[0].equalsIgnoreCase("up"))
            {

            } else if (commands[0].equalsIgnoreCase("down"))
            {

            } else if (commands[0].equalsIgnoreCase("left"))
            {

            } else if (commands[0].equalsIgnoreCase("right"))
            {

            }
        }
        catch(Exception ex)
        {
            println("Error -> " + ex.getMessage(), trace, new Color(255,100,100)) ;
        }
    }
     */

    public String next(String question)
    {
        return JOptionPane.showInputDialog(null,question,"InputBox",JOptionPane.PLAIN_MESSAGE);
    }

    public boolean nextOption(String question)
    {
        return JOptionPane.showConfirmDialog(null,question,"OptionBox",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE)==JOptionPane.YES_OPTION;
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

    public int getFrameHeight() {
        return this.frame.getHeight();
    }

    public int getFrameWidth() {
        return this.frame.getWidth();
    }

    public void addComponent(Component c) {
        this.frame.getContentPane().add(c);
    }

    public void close()
    {
        frame.dispose();
    }

    public void exit()
    {
        WindowEvent windowEventClosing = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowEventClosing);
    }

    public void setVisible(boolean x)
    {
        frame.setVisible(x);
    }

    public void replaceFrame(JFrame y)
    {
        this.frame = y;
    }

}