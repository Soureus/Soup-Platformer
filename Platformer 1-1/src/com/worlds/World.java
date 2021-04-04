package com.worlds;

import com.portals.Portal;
import com.items.Soup;
import com.tiles.Tile;
import com.utils.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class World {

    protected ArrayList<Tile> tiles = new ArrayList<>();
    protected ArrayList<Soup> soups = new ArrayList<>();
    protected ArrayList<Portal> portals = new ArrayList<>();

    protected static World currentWorld = null;
    static Handler handler;

    Timer timer;
    protected Graphics2D gtd;

    public static World getCurrentWorld() {
        return currentWorld;
    }

    public static void setCurrentWorld(World currentWorld) {
        World.currentWorld = currentWorld;
    }

    public World(Handler handler){
        this.handler = handler;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                reset();
            }
        }, 1);


    }

    public abstract void reset();

    public abstract void makeTiles(int offset);
    public abstract void makeSoup();

    public void tick(){
        for (Soup soup: soups){
            soup.tick();
        }
        for (int i = 0; i < tiles.size(); i++) {
            tiles.get(i).tick();
        }
        for (Portal portal: portals) {
            portal.tick();
        }
    }

    public void render(Graphics2D gtd){
        this.gtd = gtd;
        for (Soup soup: soups) {
            soup.render(gtd);
        }
        for (Tile tile: tiles){
            tile.render(gtd);
        }
        for (Portal portal: portals) portal.render(gtd);
    }

    //GETTERS & SETTERS
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public ArrayList<Soup> getSoups() {
        return soups;
    }

    public void setSoups(ArrayList<Soup> soups) {
        this.soups = soups;
    }

    public static void changeStartX(int newStartX){
        handler.getPlayer().setStartX(newStartX);
    }
}
