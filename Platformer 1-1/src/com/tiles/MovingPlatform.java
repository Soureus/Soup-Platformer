package com.tiles;

import com.gfx.Assets;
import com.utils.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MovingPlatform extends Tile{

    private int startMoveX, endMoveX;
    private int newSX, newEX;
    private boolean movingForward, movingBackward;


    public MovingPlatform(int id, int x, int y, Handler handler, boolean intractable) {
        super(Assets.movingPlatform, id, x, y, handler, intractable);
        platformxVelocity = 3;
        startMoveX = startX;
        endMoveX = startMoveX + 100;
        movingForward = true;
        movingBackward = false;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public void move(){

        newSX = startMoveX += handler.getCamera().getxOffset();
        newEX = endMoveX += handler.getCamera().getxOffset();
        System.out.println(newSX);
        System.out.println("x: " + x);

        if (x < newEX && movingForward) {
            startX += platformxVelocity;
        }else {
            movingForward = false;
            movingBackward = true;
        }
        if (movingBackward) {
            startX -= platformxVelocity;
        }
        if (x < newSX){
            movingBackward = false;
            movingForward = true;
            System.out.println(newSX);
            System.out.println("x: " + x);
        }

    }

    @Override
    public boolean isSolid(){
        return true;
    }

    @Override
    public void render(Graphics2D gtd){
        gtd.drawImage(texture,this.x,this.y,100,50,null);
        //gtd.fill(hitBox);
    }



}
