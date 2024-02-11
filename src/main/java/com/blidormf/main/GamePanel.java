package com.blidormf.main;

import com.blidormf.entity.Player;
import com.blidormf.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Screen settings
    private static final int ORIGINAL_TILE_SIZE = 256;
    private static final double SCALE = 0.5;
    public static final int TILE_SIZE = (int) (ORIGINAL_TILE_SIZE * SCALE);
    public static final int NUMBER_OF_ROWS = 6;
    public static final int NUMBER_OF_COLUMNS = 8;
    public static final int WINDOW_WIDTH = NUMBER_OF_COLUMNS * TILE_SIZE;
    public static final int WINDOW_HEIGHT = NUMBER_OF_ROWS * TILE_SIZE;
    private static final int FPS = 60;

    // World settings
    public static final int NUMBER_OF_WORLD_COLUMNS = 50;
    public static final int NUMBER_OF_WORLD_ROWS = 50;
    public static final int WORLD_WIDTH = TILE_SIZE * NUMBER_OF_WORLD_COLUMNS;
    public static final int WORLD_HEIGHT = TILE_SIZE * NUMBER_OF_WORLD_ROWS;

    private final KeyHandler keyHandler = new KeyHandler();
    private TileManager tileManager = new TileManager(this);
    private Thread gameThread;
    public Player player = new Player(this, keyHandler);

    public GamePanel() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
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

        tileManager.draw(g2);
        player.draw(g2);

        g2.dispose();   // Dispose of this graphics context and release any system resources it's using
    }


}
