package com.gfx;

import com.sour.Player;
import com.tiles.MovingPlatform;
import com.tiles.Tile;
import com.utils.Handler;

public class GameCamera {

    private float DEFAULT_X_OFFSET;
    private float xOffset, yOffset;
    private Handler handler;

    public GameCamera(Handler handler, float xOffset, float yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.handler = handler;
    }

    public void move(float xAmt, float yAmt){
        xOffset +=xAmt;
        yOffset -=yAmt;
    }

    public void centerOnPlayer(Player player){
        if (!handler.getPlayer().collision() && handler.getPlayer().getX() == 300) xOffset += (float)(player.getxVelocity());
        yOffset = (float)(player.getyVelocity());
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float newxOffset) {
        this.xOffset = newxOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float newyOffset) {
        this.yOffset = newyOffset;
    }

}
