package com.utils;

import com.gfx.GameCamera;
import com.sour.Game;
import com.sour.Player;
import com.states.statePanels.GamePanel;
import com.worlds.World;

import java.awt.*;

public class Handler {

private Game game;

    public Handler(Game game){
            this.game = game;
        }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public GamePanel getGamePanel(){return game.getGameState().getGamePanel();}

    public World getCurrentWorld(){return World.getCurrentWorld();}

    public Player getPlayer(){return getGamePanel().getPlayer();}

    public void setGame(Game game) {
        this.game = game;
    }

    public KeyManager getKeyManager(){return game.getKeyManager();}

    public MouseManager getMouseManager(){return game.getMouseManager();}

    public GameCamera getCamera(){return game.getCamera();}

    public Graphics2D getGraphics(){return game.getGtd();}

}
