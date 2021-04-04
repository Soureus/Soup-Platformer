package com.items;

import com.utils.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;

public abstract class Soup {

    int x, y, width, height;
    protected int startX;
    BufferedImage texture;
    Rectangle hitBox;
    protected boolean render = true;
    Handler handler;
    protected Timer timer;

    public Soup(int x, int y, int width, int height, BufferedImage texture, Handler handler){
        this.startX = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        hitBox = new Rectangle(x,y+height/4 + 3,width,height/2 - 6);
        this.handler = handler;
        timer = new Timer();
    }

    public Soup(int x, int y, int width, int height, BufferedImage texture) {
    }

    public void tick(){
        x = (int)(startX - handler.getCamera().getxOffset());
        if (hitBox != null) hitBox.x = x;
        if (!render) hitBox = null;

    }

    public void render(Graphics2D gtd){
        //gtd.setColor(Color.BLUE);
        //if (hitBox != null) gtd.fill(hitBox);
        if (render) gtd.drawImage(texture, this.x,this.y,width,height,null);
    }

    public void reset(Graphics2D gtd){
        setRender(true);
        render(gtd);
        hitBox = new Rectangle(x,y+height/4 + 3,width,height/2 - 6);
    };

    public abstract void onCollision();

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

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
