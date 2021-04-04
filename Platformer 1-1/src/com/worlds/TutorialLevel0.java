package com.worlds;

import com.gfx.Assets;
import com.items.*;
import com.portals.Portal;
import com.portals.TutorialPortal;
import com.tiles.Wall;
import com.utils.Handler;

import java.awt.*;
import java.util.TimerTask;

public class TutorialLevel0 extends World{

    public TutorialLevel0(Handler handler) {
        super(handler);
        handler.getPlayer().setStartX(50);
    }

    @Override
    public void makeTiles(int offset) {
        for (int i = 50; i < 500; i += 50) {
            tiles.add(new Wall(0, (int) (i), (int) (600), handler, false));
        }
        for (int i = 600; i > 300; i-=50) {
            tiles.add(new Wall(0,500, i,handler, false));
        }
        for (int i = 500; i < 1000; i+=50) {
            tiles.add(new Wall(0, i, 350, handler, false));
        }
        tiles.add(new Wall(0, 1800, 450, handler, false));
        tiles.add(new Wall(0, 1850, 450, handler, false));
        tiles.add(new Wall(0, 1900, 450, handler, false));
        tiles.add(new Wall(0, 1950, 400, handler, false));
        tiles.add(new Wall(0, 2000, 400, handler, false));
        tiles.add(new Wall(0, 2050, 400, handler, false));
        tiles.add(new Wall(0, 2150, 400, handler, false));

        for (int i = 0; i < 800; i+=50) {
            tiles.add(new Wall(0,2300,i,handler, false));
        }

        for (int i = 2400; i < 2800; i+=50) {
            tiles.add(new Wall(0,i,400,handler, false));
        }
        portals.add(new TutorialPortal(2850, 200, 200,200, handler));
    }

    @Override
    public void makeSoup() {
        soups.add(new Soup2(400, 550, 50, 50, handler));
        soups.add(new Soup1(900, 300,50,50,handler));
        soups.add(new PurpleSoup(2150,350,50,50,handler));
    }

    public void makeAssets(Graphics g){
        Graphics2D gtd = (Graphics2D) g;
        gtd.drawImage(Assets.jumpBoost, (int)(360 - handler.getCamera().getxOffset()), 475, null);
        gtd.drawImage(Assets.speedBoost, (int) (860 - handler.getCamera().getxOffset()), 225, null);
        gtd.drawImage(Assets.teleport, (int)(2110 - handler.getCamera().getxOffset()), 275,null);
        gtd.setFont(new Font("Arial", Font.BOLD, 40));
        gtd.drawString("Click Here", 2450 - handler.getCamera().getxOffset(), 300);
    }

    @Override
    public void render(Graphics2D gtd) {
        super.render(gtd);
        makeAssets(gtd);
        if (handler.getKeyManager().devSkip) World.setCurrentWorld(new Level2(handler));
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
