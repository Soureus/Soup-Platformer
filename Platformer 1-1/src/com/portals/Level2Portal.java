package com.portals;

import com.gfx.Assets;
import com.utils.Handler;
import com.worlds.World;

import java.awt.image.BufferedImage;

public class Level2Portal extends Portal{
    public Level2Portal(int x, int y, int width, int height, Handler handler) {
        super(Assets.portal, x, y, width, height, handler);
    }

    @Override
    public void onCollision() {
        World.getCurrentWorld().reset();
        World.setCurrentWorld(handler.getGame().getLevel3World());
        handler.getGame().getLevel3World().changeStartX(50);
    }
}
