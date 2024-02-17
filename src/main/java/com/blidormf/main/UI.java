package com.blidormf.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.blidormf.main.GamePanel.*;

public class UI {
    GamePanel gamePanel;
    Font font;
    BufferedImage keyImage;
    boolean notificationOn;
    int notificationCounter;
    String notification;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.font = new Font("Arial", Font.PLAIN, 40);

        try {
            this.keyImage = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(Color.DARK_GRAY);

        g2.fillRect(0, 0, TILE_SIZE / 2 + 20, WINDOW_HEIGHT);

        g2.setColor(Color.WHITE);

        int i = 0, x = 10, y = 10;
        while (i < gamePanel.player.numberOfKeys) {
            g2.drawImage(this.keyImage, x, y, TILE_SIZE / 2, TILE_SIZE / 2, null);
            y += TILE_SIZE / 2;
            i++;
        }

        if (this.notificationOn) {
            int notificationLength = (int) g2.getFontMetrics().getStringBounds(this.notification, g2).getWidth();

            g2.drawString(this.notification, WINDOW_WIDTH / 2 - notificationLength / 2, WINDOW_HEIGHT / 2 - TILE_SIZE);

            this.notificationCounter++;

            if (this.notificationCounter > 60) {
                this.notificationCounter = 0;
                this.notificationOn = false;
            }
        }
    }

    public void displayNotification(String notification) {
        this.notification = notification;
        this.notificationOn = true;
    }
}
