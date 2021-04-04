package com.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static final int width = 32, height = 32;

    public static BufferedImage wall, play, playClicked, quit, quitClicked, soup, player, greenSoup, purpleSoup, jumpBoost, speedBoost, teleport, portal, box;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Textures/spriteSheet.png"));

        wall = sheet.crop(width,height,width,height);
        quitClicked = sheet.crop(0,0,width*2,height);
        play = sheet.crop(width*2,0,width*2,height);
        soup = sheet.crop(0,height,width,height);
        playClicked = sheet.crop(width*2,height,width*2,height);
        quit = sheet.crop(0,height*2,width*2, height);
        player = sheet.crop(width*2,height*2,width,height);
        greenSoup = sheet.crop(width*3, height*2, width,height);
        purpleSoup = sheet.crop(0,height*3,width,height);
        jumpBoost = ImageLoader.loadImage("/Textures/Jump Boost.png");
        speedBoost = ImageLoader.loadImage("/Textures/SpeedBoost.png");
        teleport = ImageLoader.loadImage("/Textures/Teleport.png");
        portal = ImageLoader.loadImage("/Textures/Portal.png");
        box = ImageLoader.loadImage("/Textures/box.png");
    }

}
