package com.utils;

import com.sour.Player;
import com.states.statePanels.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

   public boolean keys[];
   public boolean right, left, up, down, keyR, devSkip;
   public Boolean crouched;

    public KeyManager(){
        keys = new boolean[256];

    }

   public void tick(){
       up = keys[KeyEvent.VK_W];
       down = keys[KeyEvent.VK_S];
       left = keys[KeyEvent.VK_A];
       right = keys[KeyEvent.VK_D];
       keyR = keys[KeyEvent.VK_R];
       devSkip = keys[KeyEvent.VK_P];
   }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }


}
