package com.blidormf.main;

import com.blidormf.entity.Player;
import com.blidormf.object.SuperObject;
import com.blidormf.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Screen settings
    private static final int ORIGINAL_TILE_SIZE = 256;
    public static final double SCALE = 0.5;
    public static final int TILE_SIZE = (int) (ORIGINAL_TILE_SIZE * SCALE);
    public static final int NUMBER_OF_ROWS = 6;
    public static final int NUMBER_OF_COLUMNS = 8;
    public static final int WINDOW_WIDTH = NUMBER_OF_COLUMNS * TILE_SIZE;
    public static final int WINDOW_HEIGHT = NUMBER_OF_ROWS * TILE_SIZE;
    private static final int FPS = 60;

    // World settings
    public static final int NUMBER_OF_WORLD_COLUMNS = 50;
    public static final int NUMBER_OF_WORLD_ROWS = 50;

    // System
    public boolean inTitleState;
    public final KeyHandler keyHandler = new KeyHandler();
    public final TileManager tileManager = new TileManager(this);
    public final CollisionChecker collisionChecker = new CollisionChecker(this);
    public final AssetSetter assetSetter = new AssetSetter(this);
    public final Player player = new Player(this, keyHandler);
    public final SuperObject[] objects = new SuperObject[10];
    public final UI ui = new UI(this);
    public Thread gameThread;

    public GamePanel() {
        this.inTitleState = true;
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;   // How many "intervals" of drawInterval have occurred between the last frame and the current frame.
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {   // Can use .isAlive() as well, probably
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Draw title screen
        if (inTitleState) {
            if (this.keyHandler.isEnterPressed())
                inTitleState = false;

            ui.drawTitleScreen(g2);
            return;
        }

        // Tiles
        tileManager.draw(g2);

        // Objects
        for (SuperObject object : objects) {
            if (object != null)
                object.draw(g2, this);
        }

        // Player
        player.draw(g2);

        // UI
        ui.draw(g2);

        g2.dispose();   // Dispose of this graphics context and release any system resources it's using
    }
}
