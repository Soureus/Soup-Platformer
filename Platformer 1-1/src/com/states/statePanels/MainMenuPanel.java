package com.states.statePanels;

import com.gfx.Assets;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel{

    private ImageIcon playIcon;
    private ImageIcon playIconClicked;
    private JButton playBtn;
    private ImageIcon quitIcon;
    private ImageIcon quitIconClicked;
    private JButton quitBtn;

    public MainMenuPanel(Dimension d){
        this.setLayout(null);
        this.setLocation(0,0);
        this.setSize(d);
        this.setBackground(Color.darkGray);

        addComponents();
    }

    public void render(){
        super.repaint();
    }

    public void paint(Graphics g){

        Graphics2D gtd = (Graphics2D) g;
        super.paint(gtd);

    }

    private void addComponents(){
        playIcon = new ImageIcon(Assets.play);
        playIconClicked = new ImageIcon(Assets.playClicked);
        Image image1 = playIconClicked.getImage();
        Image newImg1 = image1.getScaledInstance(192,96,Image.SCALE_SMOOTH);
        playIconClicked = new ImageIcon(newImg1);

        playBtn = new JButton(playIcon);
        Image image = playIcon.getImage();
        Image newImg = image.getScaledInstance(192,96, Image.SCALE_SMOOTH);
        playIcon = new ImageIcon(newImg);
        playBtn.setIcon(playIcon);

        quitIconClicked = new ImageIcon(Assets.quitClicked);
        Image image3 = quitIconClicked.getImage();
        Image newImage3 = image3.getScaledInstance(192,96,Image.SCALE_SMOOTH);
        quitIconClicked = new ImageIcon(newImage3);

        quitIcon = new ImageIcon(Assets.quit);
        quitBtn = new JButton(quitIcon);
        Image image2 = quitIcon.getImage();
        Image newImage2 = image2.getScaledInstance(192,96,Image.SCALE_SMOOTH);
        quitIcon = new ImageIcon(newImage2);
        quitBtn.setIcon(quitIcon);


        playBtn.setFocusable(false);
        playBtn.setBorderPainted(false);
        playBtn.setContentAreaFilled(false);
        playBtn.setFocusPainted(false);
        playBtn.setOpaque(false);
        playBtn.setSize(136,57);
        playBtn.setLocation(800/2-playBtn.getWidth()/2,800/2-playBtn.getHeight()/2 - 64);

        quitBtn.setFocusable(false);
        quitBtn.setBorderPainted(false);
        quitBtn.setContentAreaFilled(false);
        quitBtn.setFocusPainted(false);
        quitBtn.setOpaque(false);
        quitBtn.setSize(136,57);
        quitBtn.setLocation(playBtn.getX(), playBtn.getY()+playBtn.getHeight()+32);

        this.add(playBtn);
        this.add(quitBtn);

    }

    public JButton getPlayBtn() {
        return playBtn;
    }

    public ImageIcon getPlayIcon() {
        return playIcon;
    }

    public ImageIcon getPlayIconClicked() {
        return playIconClicked;
    }

    public JButton getQuitBtn() {
        return quitBtn;
    }

    public ImageIcon getQuitIcon() {
        return quitIcon;
    }

    public ImageIcon getQuitIconClicked() {
        return quitIconClicked;
    }
}
