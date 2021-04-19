package com.tiles;

import com.utils.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Tile {

    public static ArrayList<Tile> tiles = new ArrayList<>();
    private Rectangle hitBox;
    protected int x, y;
    protected int startX, startY;
    Timer timer = new Timer();
    protected int platformxVelocity;

    //CLASS
    protected BufferedImage texture;
    protected final int id;
    protected  Handler handler;
    protected boolean interacted = false;
    protected boolean intractable = false;
    protected static int tileWidth = 50;
    protected static int tileHeight = 50;

    public Tile(BufferedImage texture, int id, int x, int y, Handler handler, boolean intractable){
        this.texture = texture;
        this.id = id;
        this.startX = x;
        this.y = y;
        this.handler = handler;
        this.intractable = intractable;

        hitBox = new Rectangle(startX, y, tileWidth, tileHeight);

        //tiles.add(0, this);
    }

    public void onCollision(int i){

    }

    public boolean isSolid(){return false;}

    //GETTERS & SETTERS
    public int getId(){return id;}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public boolean isInteracted() {
        return interacted;
    }

    public void setInteracted(boolean interacted) {
        this.interacted = interacted;
    }

    public boolean isIntractable() {
        return intractable;
    }

    public void setIntractable(boolean intractable) {
        this.intractable = intractable;
    }

    //TICK & RENDER

    public int tick(){

        x = (int)(startX - handler.getCamera().getxOffset());
        if (canMove()){
            move();
        }

        hitBox.x  = x;
        hitBox.y = y;

        return x;
    }

    public void render(Graphics2D gtd){
        gtd.drawImage(texture,this.x,this.y,tileWidth,tileHeight,null);
        //gtd.fill(hitBox);
    }

    public boolean canMove() {
        return false;
    }

    public void move(){

    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getPlatformxVelocity() {
        return platformxVelocity;
    }

    public void setPlatformxVelocity(int platformxVelocity) {
        this.platformxVelocity = platformxVelocity;
    }
}
