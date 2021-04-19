package com.tiles;

import com.gfx.Assets;
import com.utils.Handler;

import java.awt.*;
import java.util.Timer;

public class Wall extends Tile{

    private Rectangle hitbox;

    public Wall(int id, int x, int y, Handler handler, boolean intractable) {
        super(Assets.wall, id, x, y, handler, intractable);
        tileWidth = 50;
        tileHeight = 50;
    }

    @Override
    public boolean isSolid(){
        return true;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

}
