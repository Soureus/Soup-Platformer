package com.states.statePanels;

import com.sour.Player;
import com.utils.Handler;
import com.worlds.TutorialLevel0;
import com.worlds.World;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GamePanel extends JPanel{

    private Player player;
    private World tutorial;
    private Handler handler;
    Timer timer = new Timer();
    Graphics g;
    Graphics2D gtd;
    private JLabel levelLabel;

    Pattern p = Pattern.compile("\\d+");
    Matcher matcher;
    private String labelText;

    public GamePanel(Dimension d, Handler handler){

        this.setLocation(0,0);
        this.setSize(d);
        this.setBackground(Color.lightGray);
        this.setFocusable(true);
        this.handler = handler;
        this.setLayout(null);

        levelLabel = new JLabel("S",SwingConstants.CENTER);
        levelLabel.setFont(new Font("Georgia", Font.BOLD, 25));
        levelLabel.setOpaque(true);
        levelLabel.setBackground(new Color(40,40,40));
        levelLabel.setForeground(new Color(245,245,245));
        levelLabel.setSize(150,50);
        levelLabel.setLocation(15,15);

        player = new Player(this, handler);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                makeWorld1();
            }
        },1);
        this.add(levelLabel);

    }

    public void render(){
        repaint();
    }

    public void paint(Graphics g){

        gtd = (Graphics2D) g;
        super.paint(gtd);
        this.gtd = gtd;

        if (!(World.getCurrentWorld() == null)){
            World.getCurrentWorld().render(gtd);
            matcher = p.matcher(World.getCurrentWorld().toString());
            if (matcher.find())labelText = "Level: " + matcher.group(0);
            levelLabel.setText(labelText);

        }
        player.render();
        player.draw(gtd);
    }

    public void makeWorld1(){
        tutorial = new TutorialLevel0(handler);
        World.setCurrentWorld(tutorial);
    }

    public void setHitBox(){

    }

    //GETTERS & SETTERS

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
