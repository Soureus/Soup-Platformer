package com.worlds;

import com.gfx.Assets;
import com.items.PurpleSoup;
import com.items.Soup;
import com.items.Soup1;
import com.items.Soup2;
import com.tiles.Tile;
import com.tiles.Wall;
import com.utils.Handler;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;

public class level5 extends World {

    private int xOffset;

    public level5(Handler handler) {
        super(handler);
        handler.getPlayer().setStartX(300);
    }

    Graphics2D gtd;

    @Override
    public void makeTiles(int offset) {
        for (int i = 50; i < 1500; i += 50) {
            tiles.add(new Wall(0, (int) (i), (int) (600), handler, false));
        }
        for (int j = 550; j > 350; j -= 50) {
            tiles.add(new Wall(0, (int) (50), (int) (j), handler, false));
        }
        for (int i = 550; i > 200; i -= 50) {
            tiles.add(new Wall(0, (int) (600), (int) (i), handler, false));
        }

        tiles.add(new Wall(0, (int) (450), (int) (550), handler, false));
    }

    @Override
    public void makeSoup() {
        soups.add(0, new Soup1(50, 350, 50, 50, handler));
        soups.add(1, new Soup2(450, 500, 50, 50, handler));
        soups.add(2, new PurpleSoup(500, 350, 50, 50, handler));
    }

    @Override
    public void reset() {

                handler.getPlayer().setX(handler.getPlayer().getDEFAULT_X());
                handler.getPlayer().setY(handler.getPlayer().getDEFAULT_Y());
                handler.getPlayer().setxVelocity(0);
                handler.getPlayer().setyVelocity(0);
                handler.getPlayer().setJumpSpeed(handler.getPlayer().getDefaultJumpSpeed());
                handler.getPlayer().setMaxSpeed(handler.getPlayer().getDefaultMaxSpeed());

                handler.getPlayer().setCanMove(false);
                tiles.clear();
                soups.clear();
                handler.getCamera().setxOffset(0);

        System.out.println("Reset run");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int offset = 50;
                makeTiles(offset);
                makeSoup();
            }
        }, 1);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.getPlayer().setCanMove(true);
            }
        }, 700);

            }
    }


