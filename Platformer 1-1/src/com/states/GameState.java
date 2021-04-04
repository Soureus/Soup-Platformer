package com.states;

import com.sour.Game;
import com.states.statePanels.GamePanel;
import com.utils.Handler;
import com.utils.KeyManager;

import javax.swing.*;
import java.awt.*;

public class GameState extends State{

    private static GamePanel gamePanel;
    Graphics g;

    public GameState(Handler handler) {
        super(handler);
        gamePanel = new GamePanel(handler.getGame().getFrame().getSize(), handler);
        gamePanel.addKeyListener(handler.getKeyManager());
    }


    @Override
    public void tick() {
        handler.getKeyManager().tick();
        if (handler.getCurrentWorld() !=null) handler.getCurrentWorld().tick();
        gamePanel.getPlayer().tick();
    }

    @Override
    public void render(Graphics g) {
        start();
        gamePanel.render();
    }

    @Override
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    @Override
    public void stop() {
        handler.getGame().getFrame().remove(gamePanel);
    }

    @Override
    public void start() {
        handler.getGame().getFrame().add(gamePanel);
    }

}
