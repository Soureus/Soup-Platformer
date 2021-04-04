package com.sour;

import com.states.statePanels.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    int width, height;

    public Frame(int width, int height){
        this.width = width;
        this.height = height;


        this.setSize(this.width,this.height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Title");

        this.setVisible(true);

    }

    public void removeComponents(JPanel panel){
        this.getContentPane().remove(panel);
        this.repaint();
    }

    public void removeAllComponents(){
        this.getContentPane().removeAll();
        this.repaint();
    }



}
