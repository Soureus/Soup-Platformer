package com.worlds;

import com.tiles.Tile;
import com.utils.Handler;

import java.util.TimerTask;

public class Level3 extends World{
    public Level3(Handler handler) {
        super(handler);
    }

    @Override
    public void reset() {
        for (Tile tile : tiles) tile.getTimer().cancel();
        handler.getPlayer().setX(handler.getPlayer().getStartX());
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

    @Override
    public void makeTiles(int offset) {

    }

    @Override
    public void makeSoup() {

    }
}
