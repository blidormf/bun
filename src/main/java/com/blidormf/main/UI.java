package com.blidormf.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.blidormf.main.GamePanel.*;

public class UI {
    private final GamePanel gamePanel;
    private final Font regularFont, largeFont, titleFont;
    private BufferedImage inventoryImage;
    private final BufferedImage[] inventory;
    private int numberOfItems;
    private boolean notificationOn;
    private int notificationCounter;
    private String notification;
    private Color notificationBackground;
    private boolean gameFinished;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.titleFont = new Font("Consolas", Font.BOLD, 150);
        this.largeFont = new Font("Consolas", Font.BOLD, 72);
        this.regularFont = new Font("Consolas", Font.BOLD, 36);
        this.notificationBackground = new Color(0f,0f,0f,0.7f);
        this.numberOfItems = 0;
        this.inventory = new BufferedImage[3];

        try {
            this.inventoryImage = ImageIO.read(getClass().getResourceAsStream("/ui/inventory.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        if (gameFinished) {
            g2.setFont(largeFont);
            int endMessageLength = (int) g2.getFontMetrics().getStringBounds("Yay!", g2).getWidth();
            int endMessageHeight = (int) g2.getFontMetrics().getStringBounds("Yay!", g2).getHeight();

            g2.setFont(regularFont);
            int notificationLength = (int) g2.getFontMetrics().getStringBounds("You found all the veggies! Yum!", g2).getWidth();
            int notificationHeight = (int) g2.getFontMetrics().getStringBounds("You found all the veggies! Yum!", g2).getHeight();

            g2.setColor(notificationBackground);
            g2.fillRoundRect(WINDOW_WIDTH / 2 - notificationLength / 2 - 10, WINDOW_HEIGHT / 2 - endMessageHeight - 10, notificationLength + 20, notificationHeight + endMessageHeight + 60, 10, 10);

            g2.setColor(Color.WHITE);
            g2.drawString("You found all the veggies! Yum!", WINDOW_WIDTH / 2 - notificationLength / 2, WINDOW_HEIGHT / 2 + 20 + notificationHeight);

            g2.setFont(this.largeFont);
            g2.drawString("Yay!", WINDOW_WIDTH / 2 - endMessageLength / 2, WINDOW_HEIGHT / 2 - 20);

            this.gamePanel.gameThread = null;
        }

        g2.setFont(regularFont);

        g2.drawImage(this.inventoryImage, 0, 0, 90 , WINDOW_HEIGHT, null);

        int x = 10, y = 10;
        for (BufferedImage itemImage : this.inventory) {
            if (itemImage != null) {
                g2.drawImage(itemImage, x, y, TILE_SIZE / 2, TILE_SIZE / 2, null);
                y += TILE_SIZE / 2;
            }
        }

        if (this.notificationOn) {
            int notificationLength = (int) g2.getFontMetrics().getStringBounds(this.notification, g2).getWidth();
            int notificationHeight = (int) g2.getFontMetrics().getStringBounds(this.notification, g2).getHeight();

            g2.setColor(notificationBackground);
            g2.fillRoundRect(WINDOW_WIDTH / 2 - notificationLength / 2 - 10, 700 - notificationHeight, notificationLength + 20, notificationHeight + 20, 10, 10);
            g2.setColor(Color.WHITE);
            g2.drawString(this.notification, WINDOW_WIDTH / 2 - notificationLength / 2, 700);

            this.notificationCounter++;

            if (this.notificationCounter > 100) {
                this.notificationCounter = 0;
                this.notificationOn = false;
            }
        }
    }

    public void drawTitleScreen(Graphics2D g2) {
        g2.setFont(titleFont);
        g2.setColor(Color.WHITE);

        String title = "bun.";
        int titleLength = (int) g2.getFontMetrics().getStringBounds(title, g2).getWidth();
        g2.drawString(title, WINDOW_WIDTH / 2 - titleLength / 2, 150);

        g2.setFont(this.regularFont);

        String topLine = "Gather the essential ingredients\nfor your favorite salad.\nSeek out the cabbage, cucumber, and carrot\nscattered across the land.";
        String[] lines = topLine.split("\n");

        int y = 300;
        for (String line : lines) {
            int lineLength = (int) g2.getFontMetrics().getStringBounds(line, g2).getWidth();
            g2.drawString(line, WINDOW_WIDTH / 2 - lineLength / 2, y);
            y += 80;
        }

        String bottomLine = "Press ENTER to embark on this delicious journey!";
        int bottomLineLength = (int) g2.getFontMetrics().getStringBounds(bottomLine, g2).getWidth();
        g2.drawString(bottomLine, WINDOW_WIDTH / 2 - bottomLineLength / 2, 700);
    }

    public void displayNotification(String notification) {
        this.notification = notification;
        this.notificationOn = true;
    }

    public void addItem(String objectName) {
        try {
            this.inventory[numberOfItems++]  = ImageIO.read(getClass().getResourceAsStream("/objects/" + objectName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setGameFinished() {
        this.gameFinished = true;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }
}
