package com.states;

import com.sour.Game;
import com.states.statePanels.GamePanel;
import com.states.statePanels.MainMenuPanel;
import com.utils.ButtonTimer;
import com.utils.Handler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

public class MainMenuState extends State implements MouseListener {

    MainMenuPanel menuPanel;
    Rectangle bounds;
    Timer timerDown;
    Timer timerUp;
    ButtonTimer buttonTimer;
    Graphics g;

    public MainMenuState(Handler handler) {
        super(handler);
        menuPanel = new MainMenuPanel(handler.getGame().getFrame().getSize());
        menuPanel.getPlayBtn().addMouseListener(this);
        menuPanel.getQuitBtn().addMouseListener(this);
    }



    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        start();
        menuPanel.render();
    }

    @Override
    public GamePanel getGamePanel() {
        return null;
    }

    @Override
    public void stop() {
        handler.getGame().getFrame().remove(menuPanel);
    }

    @Override
    public void start() {
        handler.getGame().getFrame().add(menuPanel);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == menuPanel.getPlayBtn()){
            menuPanel.getPlayBtn().setIcon(menuPanel.getPlayIconClicked());
            buttonTimer = new ButtonTimer(handler, menuPanel,menuPanel.getPlayBtn(), menuPanel.getPlayIcon(), menuPanel.getPlayIconClicked());
            buttonTimer.buttonAnimation();
            buttonTimer.removePanel();
            buttonTimer.toGameState();
        }
        if (e.getSource() == menuPanel.getQuitBtn()){
            menuPanel.getQuitBtn().setIcon(menuPanel.getQuitIconClicked());
            buttonTimer = new ButtonTimer(handler, menuPanel, menuPanel.getQuitBtn(), menuPanel.getQuitIcon(), menuPanel.getQuitIconClicked());
            buttonTimer.buttonAnimation();
            buttonTimer.exit();
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
