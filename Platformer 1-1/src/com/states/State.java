package com.states;

import com.sour.Game;
import com.states.statePanels.GamePanel;
import com.utils.Handler;

import javax.swing.*;
import java.awt.*;

public abstract class State {

    protected static State currentState = null;

    public static void setCurrentState(State state){
        currentState = state;
    }

    public static State getCurrentState(){
        return currentState;
    }

    //CLASS
    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public void reset(JPanel panel){
        currentState = null;
        handler.getGame().getFrame().removeComponents(panel);
    }

    public abstract GamePanel getGamePanel();

    public abstract void stop();

    public abstract void start();

}
