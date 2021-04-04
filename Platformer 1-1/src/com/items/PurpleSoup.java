package com.items;

import com.gfx.Assets;
import com.tiles.Tile;
import com.utils.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.TimerTask;

public class PurpleSoup extends Soup {

    private static final int DURATION = 3000;
    private long activatedAt = Long.MAX_VALUE;
    private boolean notUsed = true;
    private boolean do1 = true;
    int newX;

    public PurpleSoup(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, Assets.purpleSoup, handler);
    }

    @Override
    public void tick(){
        if (isActive() && notUsed)
            if (handler.getMouseManager().pressed) {

                handler.getPlayer().setyVelocity(0);
                newX = handler.getMouseManager().e.getX();
                System.out.println(newX);
                int newY = (int) (handler.getMouseManager().e.getY() - handler.getPlayer().getHitBox().getHeight() - 32);
                System.out.println(newY);

                for (Tile tile: handler.getCurrentWorld().getTiles()) {
                    handler.getPlayer().getHitBox().x = newX;
                    if (handler.getPlayer().getHitBox().intersects(tile.getHitBox())) newX = -1;
                }

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (newX != -1) {
                            handler.getCamera().setxOffset(handler.getCamera().getxOffset() + newX - handler.getPlayer().getX());
                            System.out.println(handler.getPlayer().getX());
                            handler.getPlayer().setY(newY);
                            System.out.println(handler.getPlayer().getY());

                            try {
                                new Robot().mouseMove((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - 50, handler.getGame().getFrame().getY() + handler.getPlayer().getY() + handler.getPlayer().getHitBox().height/2);
                            }catch (AWTException e){

                            }

                        }
                    }
                },1);

                notUsed = false;

            }
        x = (int)(startX - handler.getCamera().getxOffset());
        if (hitBox != null) hitBox.x = x;
        if (!render) hitBox = null;
    }

    @Override
    public void reset(Graphics2D gtd) {
        notUsed = true;
        do1 = false;
        setRender(true);
        render(gtd);
        hitBox = new Rectangle(x,y+height/4 + 3,width,height/2 - 6);
    }

    @Override
    public void onCollision() {
        activate();
        render = false;
    }

    public void activate() {
        do1 = true;
        activatedAt = System.currentTimeMillis();
    }

    public boolean isActive() {
        if (do1){
            long activeFor = System.currentTimeMillis() - activatedAt;

            return activeFor >= 0 && activeFor <= DURATION;
        }
        return false;
    }
}

