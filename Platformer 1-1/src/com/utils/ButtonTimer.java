package com.utils;

import com.sour.Game;
import com.states.State;
import com.states.statePanels.MainMenuPanel;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class ButtonTimer {

    Timer timerDown;
    JPanel panel;
    ImageIcon iconNormal;
    ImageIcon iconPressed;
    Handler handler;
    JButton button;

    public ButtonTimer(Handler handler, JPanel panel,JButton button, ImageIcon iconNormal, ImageIcon iconPressed){
        this.panel = panel;
        this.iconNormal = iconNormal;
        this.iconPressed = iconPressed;
        this.handler = handler;
        this.button = button;

    }
    public void buttonAnimation(){
        timerDown = new Timer();

        timerDown.schedule(new TimerTask() {
            @Override
            public void run() {
                button.setIcon(iconNormal);
            }
        }, 500);
    }

    public void toGameState(){
        timerDown.schedule(new TimerTask() {
            @Override
            public void run() {
                State.setCurrentState(handler.getGame().getGameState());
            }
        }, 660);
    }

    public void removePanel(){
            timerDown.schedule(new TimerTask() {
                @Override
                public void run() {
                    State.getCurrentState().reset(panel);
                }
            }, 650);
        }

    public void exit(){
        timerDown.schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 600);
    }
    }


