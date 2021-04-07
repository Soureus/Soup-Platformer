package com.worlds;

import com.gfx.ImageLoader;
import com.portals.Level1Portal;
import com.portals.Portal;
import com.items.Soup;
import com.items.Soup1;
import com.tiles.Box;
import com.tiles.Tile;
import com.tiles.Wall;
import com.utils.Handler;

import java.awt.*;
import java.util.TimerTask;

public class Level1 extends World{

    private boolean removeAssets = false;
    private boolean remove = true;

    public Level1(Handler handler) {
        super(handler);
        handler.getPlayer().setStartX(50);
    }

    @Override
    public void reset() {
        for (Tile tile: tiles) tile.getTimer().cancel();
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
        removeAssets = false;

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
        tiles.add(new Wall(0,50, 600,handler, false));
        tiles.add(new Box(500,350, 600,handler));
    }

    @Override
    public void makeSoup() {

    }

    public void makeAssets(Graphics2D gtd) {
        if (!removeAssets) {
            gtd.setFont(new Font("Lucida Console", Font.PLAIN, 15));
            gtd.drawString("This block will disappear", 260 - handler.getCamera().getxOffset(), 450);
            gtd.drawString("after you step on it", 285 - handler.getCamera().getxOffset(), 465);
            gtd.drawImage(ImageLoader.loadImage("/Textures/arrow.png"), (int) (325 - handler.getCamera().getxOffset()), 480, null);
            portals.add(new Level1Portal(3100, 475, 150,150,handler));
        }
    }

    @Override
    public void tick() {
        for (Soup soup: soups){
            soup.tick();
        }
        for (int i = 0; i<tiles.toArray().length; i++){
            tiles.get(i).tick();
            if (tiles.get(i).isIntractable()) {
                if (handler.getPlayer().getBounds2().intersects(tiles.get(i).getHitBox()) && !tiles.get(i).isInteracted()) {
                    switch (tiles.get(i).getId()) {
                        case 500 : {tiles.add(new Box(501, 200, 550, handler)); tileCollision(i); removeAssets = true;
                        break;}
                        case 501 :{tiles.add(new Box(502, 350, 500, handler)); tileCollision(i);
                        break;}
                        case 502 :{tiles.add(new Box(503, 100, 500, handler));tileCollision(i);
                        break;}
                        case 503 : {tiles.add(new Box(504, 400, 525, handler));soups.add(new Soup1(400,475,50,50,handler));tileCollision(i);
                        break;}
                        case 504 : {makeEnding();tileCollision(i);
                        break;}

//                    if(handler.getPlayer().getHitBox().getX() + handler.getCamera().getxOffset() > tiles.get(i+1).getStartX() && handler.getPlayer().getX() + handler.getCamera().getxOffset() < tiles.get(i+1).getStartX() + 50)
//                        handler.getCamera().setxOffset(handler.getCamera().getxOffset() + tiles.get(i).getX()- handler.getPlayer().getX());
                    }
                }
            }

        }
        for (Portal portal: portals) {
            portal.tick();
        }
    }


    @Override
    public void render(Graphics2D gtd) {
        super.render(gtd);
        makeAssets(gtd);
        if (handler.getKeyManager().devSkip) World.setCurrentWorld(new Level1(handler));
    }

    public void tileCollision(int i){
          tiles.get(i).onCollision(i);
    }

    public void makeEnding(){
        for (int i = 1100; i < 3000; i+=50) {
            tiles.add(new Wall(0,i,550,handler,false));
        }
    }
}
