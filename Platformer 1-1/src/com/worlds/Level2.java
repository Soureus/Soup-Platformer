package com.worlds;

import com.items.Soup;
import com.items.Soup1;
import com.items.Soup2;
import com.portals.Level2Portal;
import com.portals.Portal;
import com.tiles.Box;
import com.tiles.Tile;
import com.tiles.Wall;
import com.utils.Handler;

import java.util.TimerTask;

public class Level2 extends World {


    public Level2(Handler handler) {
        super(handler);
        handler.getPlayer().setStartX(50);
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
        for (int i = 1; i < 4; i++) {
            tiles.add(new Wall(0, i * 50, 600, handler, false));
        }
        for (int i = 6; i < 20; i += 3) {
            tiles.add(new Box(0, i * 50, 600, handler));
        }
        tiles.add(new Box(500, 21 * 50, 600, handler));
    }

    @Override
    public void makeSoup() {

    }

    @Override
    public void tick() {
        for (Soup soup : soups) {
            soup.tick();
        }
        for (int i = 0; i < tiles.size(); i++) {
            tiles.get(i).tick();
            if (handler.getPlayer().getBounds2().intersects(tiles.get(i).getHitBox())) {
                if (tiles.get(i).getId() >= 500) {
                    switch (tiles.get(i).getId()) {
                        case 500 -> tiles.add(new Box(501, 23 * 50, 600, handler));
                        case 501 -> tiles.add(new Box(502, 27 * 50, 600, handler));
                        case 502 -> tiles.add(new Box(503, 24 * 50, 600, handler));
                        case 503 -> tiles.add(new Box(504, 30 * 50, 600, handler));
                        case 504 -> {
                            tiles.add(new Box(505, 24 * 50, 600, handler));
                            soups.add(new Soup1(24 * 50, 550, 50, 50, handler));
                        }
                        case 505 -> {
                            for (int j = 33; j < 40; j++) {
                                tiles.add(new Box(0, j * 50, 600, handler));
                            }
                            portals.add(new Level2Portal(45 * 50, 525, 150, 150, handler));
                        }
                    }
                }
                tiles.get(i).onCollision(i);
            }
            for (Portal portal : portals) {
                portal.tick();
            }
        }
    }
}

