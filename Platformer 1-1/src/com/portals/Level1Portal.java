package com.portals;

import com.gfx.Assets;
import com.utils.Handler;
import com.worlds.World;
import com.worlds.level5;


public class Level1Portal extends Portal{
    public Level1Portal(int x, int y, int width, int height, Handler handler) {
        super(Assets.portal, x, y, width, height, handler);
    }

    @Override
    public void onCollision() {
        World.getCurrentWorld().reset();
        World.setCurrentWorld(handler.getGame().getLevel2World());
        World.getCurrentWorld().reset();
    }
}
