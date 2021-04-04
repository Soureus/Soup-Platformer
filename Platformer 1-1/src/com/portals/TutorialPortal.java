package com.portals;

import com.gfx.Assets;
import com.utils.Handler;
import com.worlds.World;

import java.awt.image.BufferedImage;

public class TutorialPortal extends Portal{
    public TutorialPortal(int x, int y, int width, int height, Handler handler) {
        super(Assets.portal, x, y, width, height, handler);
    }

    @Override
    public void onCollision() {
        World.getCurrentWorld().reset();
        handler.getGame().getLevel5World().changeStartX(50);
        World.setCurrentWorld(handler.getGame().getLevel1World());
        World.getCurrentWorld().reset();
    }


}
