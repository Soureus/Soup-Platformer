package com.portals;

import com.gfx.Assets;
import com.utils.Handler;
import com.worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Portal {

    BufferedImage texture;
    private int x,y,width,height;
    private int startX,startY;
    Handler handler;
    Rectangle hitBox;

    Portal(BufferedImage texture, int x, int y, int width , int height, Handler handler){
        this.texture = texture;
        this.startX = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.handler = handler;

        hitBox = new Rectangle(x,y+height/4,width/2,height/2);
    }

    public void tick() {
        x = (int)(startX - handler.getCamera().getxOffset());
        hitBox.x = x+width/4;
        if (handler.getPlayer().getHitBox().intersects(hitBox)){
            onCollision();
        }
    }
    public void render(Graphics2D gtd) {
        gtd.drawImage(Assets.portal, x, y, width,height,null);
    }

    public abstract void  onCollision();

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
