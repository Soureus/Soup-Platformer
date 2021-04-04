package com.sour;

import com.gfx.Assets;
import com.items.Soup;
import com.states.statePanels.GamePanel;
import com.tiles.Tile;
import com.utils.Handler;
import com.utils.KeyManager;
import com.worlds.World;

import javax.swing.*;
import java.awt.*;
import java.util.IllegalFormatCodePointException;
import java.util.Timer;

public class Player {

    private GamePanel panel;

    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 100;
    private static int START_X = 200, START_Y = 200;
    private static final int DEFAULT_MAX_SPEED = 7, DEFAULT_JUMP_SPEED = -6;

    private Timer timer = new Timer();

    private float acc =1f;
    private float dcc = 0.5f;


    private int x, y, playerX;
    int width, height;

    private Boolean crouched = false;
    private  boolean downClicked = false;
    private boolean lookingRight = true;
    private boolean canMove = true;
    private boolean yCollision = false;

    private KeyManager keyManager;

    private static float xVelocity, yVelocity;
    private static float maxSpeed = DEFAULT_MAX_SPEED;
    private static float jumpSpeed = DEFAULT_JUMP_SPEED;
    private static Rectangle hitBox;



    Action upAction;

    Handler handler;

    public Player(GamePanel panel, Handler handler){

        this.panel = panel;
        x = START_X;
        y = START_Y;

        this.handler = handler;

        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        hitBox = new Rectangle(x + 4,y,width - 8,height);

    }

    public void render(){

        if (x < 300 && x < 310) x += xVelocity;
        else x = 300;
        y += yVelocity;
        playerX = x - 31;

        hitBox.x = x + 4;
        hitBox.y = y;

    }

    public void tick(){
        if (handler.getCurrentWorld() !=null) {

            getInput();
            handler.getCamera().centerOnPlayer(this);
            collision();
            itemCollision();
            checkFrameCollision();

            if (xVelocity > 0 && xVelocity < 0.75) xVelocity = 0;
            if (xVelocity < 0 && xVelocity > -0.75) xVelocity = 0;

            if (xVelocity > maxSpeed) xVelocity = maxSpeed;
            if (xVelocity < maxSpeed * -1) xVelocity = -maxSpeed;
        }
    }

    public void draw(Graphics2D gtd){

        //gtd.drawImage(Assets.player,x,y,50,100,null);
//
//        gtd.setColor(Color.RED);
//        gtd.fill(getBounds());
//
//        gtd.setColor(Color.BLUE);
//        gtd.fill(getBounds2());
//
//        gtd.setColor(Color.GRAY);
//        gtd.fill(hitBox);

          gtd.drawImage(Assets.player,(int)(playerX),y,width*2,height,null);

//        else if (xVelocity < 0) {gtd.drawImage(Assets.player,x - 31, y, -width*2, height,null); lookingRight = false;}
//        else if (lookingRight = true) gtd.drawImage(Assets.player,x - 31,y,width*2,height,null);
//        else if (lookingRight = false) gtd.drawImage(Assets.player,x - 31, y, -width*2, height,null);



    }


    //INPUT
    public void getInput() {
        //MOVEMENT
        if (canMove) {
            if (handler.getKeyManager().right && !handler.getKeyManager().left) xVelocity += acc;
            else if (!handler.getKeyManager().right && handler.getKeyManager().left) xVelocity -= acc;
            else if (handler.getKeyManager().right && handler.getKeyManager().left || !handler.getKeyManager().right && !handler.getKeyManager().left) {
                if (xVelocity > 0) xVelocity -= dcc;
                else if (xVelocity < 0) xVelocity += dcc;
            }


            if (handler.getKeyManager().up) {
                if (!(hitBox.y > y)) hitBox.y++;
                for (Tile tile : handler.getCurrentWorld().getTiles()) {
                    if (tile.getHitBox().intersects(hitBox)) {
                        yVelocity = jumpSpeed;
                    }
                }
                if ((hitBox.y > y)) hitBox.y--;
            }

            if (handler.getKeyManager().down) {
                height = DEFAULT_HEIGHT / 2;
                if (!downClicked) y += DEFAULT_HEIGHT / 2;
                downClicked = true;
                crouched = true;
                hitBox.height = DEFAULT_HEIGHT / 2;
            } else {
                height = DEFAULT_HEIGHT;
                if (crouched) {
                    y = y - DEFAULT_HEIGHT / 2;
                    crouched = false;
                    downClicked = false;
                    hitBox.height = DEFAULT_HEIGHT;
                }
            }

            //OTHER THINGS
            if (handler.getKeyManager().keyR) {
                World.getCurrentWorld().reset();
            }
        }
        if (!(yVelocity > 12)) yVelocity += 0.3;
    }

    //CHECK COLLISION
        //FRAME
    public void checkFrameCollision(){
        if (hitBox.getX()+ width+width/3 >= handler.getWidth()){
            if (xVelocity > 0){
                xVelocity = 0;
            }
        }
        if (hitBox.getX() <= 0 ){
            if (xVelocity < 0){
                xVelocity = 0;
            }
        }
        if (hitBox.y<= 0 ){
            if (yVelocity < 0){
                yVelocity = 0;
            }
        }if (hitBox.y + hitBox.height > handler.getHeight()){
            y = handler.getHeight()/2 - height/2;
            x = handler.getWidth()/2 - width/2;
            World.getCurrentWorld().reset();
        }
    }

        //HORIZONTAL HITBOX
    public Rectangle getBounds(){
        float bx = x + xVelocity;
        float by = y + 3;
        float bw = width + 0.78125f;
        float bh = height - 6;
        return new Rectangle((int)bx,(int)by,(int)bw,(int)bh);
    }
        //VERTICAL HITBOX
    public Rectangle getBounds2(){
        float bx = x + 4;
        float by = y + yVelocity;
        float bw = width - 8;
        float bh = height;
        return new Rectangle((int)bx,(int)by,(int)bw,(int)bh);
    }
        //TILES COLLISION
    public boolean collision() {
        for (Tile tile : handler.getCurrentWorld().getTiles()) {
            if (tile.isSolid()) {
                if (getBounds().intersects(tile.getHitBox())) {
                    if (xVelocity > 0) {

                        xVelocity = 0;
                        handler.getCamera().setxOffset(handler.getCamera().getxOffset() + (int)(x - getBounds().x));

                    } else if (xVelocity < 0) {
                        xVelocity = 0;
                        handler.getCamera().setxOffset(handler.getCamera().getxOffset() + (int)(x - getBounds().x));
                    }
                    return true;
                }
                if (getBounds2().intersects(tile.getHitBox())) {
                    yCollision = true;
                    if (yVelocity > 0) {
                        yVelocity = 0;
                        y = (int) tile.getHitBox().getY() - height;
                    } else if (yVelocity < 0) {
                        yVelocity = 0;
                        y = (int) tile.getHitBox().y + tile.getHitBox().height;
                    }
                }yCollision = false;
            }
        }
        return false;
    }

    //ITEM COLLISION
    public void itemCollision() {
        for (Soup soup : handler.getCurrentWorld().getSoups()) {
            if (soup.isRender())
            if (hitBox.intersects(soup.getHitBox())) soup.onCollision(); soup.tick();
        }
    }


    //GETTERS & SETTERS

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public GamePanel getPanel() {
        return panel;
    }

    public Boolean getCrouched() {
        return crouched;
    }

    public void setCrouched(Boolean crouched) {
        this.crouched = crouched;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        Player.maxSpeed = maxSpeed;
    }

    public float getJumpSpeed() {
        return jumpSpeed;
    }

    public void setJumpSpeed(float jumpSpeed) {
        Player.jumpSpeed = jumpSpeed;
    }

    public int getDEFAULT_X() {
        return START_X;
    }

    public int getDEFAULT_Y() {
        return START_Y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDefaultMaxSpeed() {
        return DEFAULT_MAX_SPEED;
    }

    public int getDefaultJumpSpeed() {
        return DEFAULT_JUMP_SPEED;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getStartX() {
        return START_X;
    }

    public int getStartY() {
        return START_Y;
    }

    public void setStartX(int startX) {
        START_X = startX;
    }

    public void setStartY(int startY) {
        START_Y = startY;
    }

    public boolean isyCollision() {
        return yCollision;
    }

    public void setyCollision(boolean yCollision) {
        this.yCollision = yCollision;
    }
}
