package com.tiles;

import com.gfx.Assets;
import com.utils.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.TimerTask;

public class Box extends Tile{

    private Rectangle hitBox;

    public Box(int id, int x, int y, Handler handler) {
        super(Assets.box, id, x, y, handler, true);
    }

    @Override
    public void onCollision(int i){
        this.setInteracted(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.getCurrentWorld().getTiles().remove(i);
            }
        },100);
        //System.out.println("Working");
    }


    @Override
    public boolean isSolid(){
        return true;
    }

    public Rectangle getHitbox() {
        return hitBox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitBox = hitbox;
    }
}
