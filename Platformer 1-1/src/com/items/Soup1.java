package com.items;

import com.gfx.Assets;
import com.sour.Player;
import com.utils.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.TimerTask;

public class Soup1 extends Soup{

    public Soup1(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, Assets.greenSoup, handler);
    }

    @Override
    public void onCollision() {
        handler.getPlayer().setMaxSpeed(14);
        render = false;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.getPlayer().setMaxSpeed(handler.getPlayer().getDefaultMaxSpeed());
            }
        }, 3000);
    }
}
