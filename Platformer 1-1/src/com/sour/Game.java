package com.sour;

import com.gfx.GameCamera;
import com.states.GameState;
import com.states.MainMenuState;
import com.states.State;
import com.gfx.Assets;
import com.utils.Handler;
import com.utils.KeyManager;
import com.utils.MouseManager;
import com.worlds.*;

import java.awt.*;

public class Game implements Runnable{

    //OBJECTS
    private Frame frame;
    private int width, height;
    private String title;
    private Handler handler;

    //INPUT
    private KeyManager keyManager = new KeyManager();
    private MouseManager mouseManager = new MouseManager();

    //CAMERA
    private GameCamera camera;

    //THREAD
    private Thread thread;
    boolean running = false;

    //STATES
    private GameState gameState;
    private State mainMenuState;

    //WORLDS
    World tutorialWorld;
    World level1World;
    World level2World;
    World level3World;
    World level5World;

    private Graphics g;
    Graphics2D gtd;

   private Player player;

    //METHODS
        //START/SETUP
    Game(String title, int width, int height){

        this.width = width;
        this.height = height;
        this.title = title;
        handler = new Handler(this);

    }

    public void start(){
        if (!running){
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public void init(){
        frame = new Frame(800,800);

        Assets.init();
        camera = new GameCamera(handler,0,0);

        frame.addKeyListener(keyManager);
        frame.addMouseListener(mouseManager);

        gameState = new GameState(handler);
        mainMenuState = new MainMenuState(handler);
        State.setCurrentState(mainMenuState);

        level1World = new Level1(handler);
        level2World = new Level2(handler);
        level3World = new Level3(handler);
        level5World = new level5(handler);

        gtd = (Graphics2D) g;
    }

    //RUNNING
    @Override
    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000){

                timer = 0;
                ticks = 0;

            }
        }
    }

    public void render(){

        if (State.getCurrentState() != null) {
            State.getCurrentState().render(g);
        }

    }

    public void tick(){

        if (State.getCurrentState() != null){
            State.getCurrentState().tick();
        }

    }

    public void paint(){


    }

    //GETTERS AND SETTERS


    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public State getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public State getMainMenuState() {
        return mainMenuState;
    }

    public void setMainMenuState(State mainMenuState) {
        this.mainMenuState = mainMenuState;
    }

    public Thread getThread() {
        return thread;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public void setKeyManager(KeyManager keyManager) {
        this.keyManager = keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public void setMouseManager(MouseManager mouseManager) {
        this.mouseManager = mouseManager;
    }

    public GameCamera getCamera() {
        return camera;
    }

    public Graphics2D getGtd() {
        return gtd;
    }

    public World getLevel5World() {
        return level5World;
    }

    public void setLevel5World(World level5World) {
        this.level5World = level5World;
    }

    public World getTutorialWorld() {
        return tutorialWorld;
    }

    public World getLevel1World() {
        return level1World;
    }

    public void setLevel1World(World level1World) {
        this.level1World = level1World;
    }

    public World getLevel2World() {
        return level2World;
    }

    public World getLevel3World() {
        return level3World;
    }
}
