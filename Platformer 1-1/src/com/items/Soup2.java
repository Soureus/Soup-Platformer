package com.items;

import com.gfx.Assets;
import com.utils.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.TimerTask;

public class Soup2 extends Soup{

    public Soup2(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, Assets.soup, handler);
    }


    @Override
    public void onCollision() {
        if (render){
            handler.getPlayer().setJumpSpeed(-12);
            render = false;

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.getPlayer().setJumpSpeed(handler.getPlayer().getDefaultJumpSpeed());
                }
            }, 3000);

        }
    }

}
