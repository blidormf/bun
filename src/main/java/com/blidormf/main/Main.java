package com.blidormf.main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class Main {
    public static void main(String[] args) {
        // Initialize window
        JFrame window = new JFrame();
        window.setTitle("bun.");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            BufferedImage icon = ImageIO.read(Main.class.getResourceAsStream("/icon/icon.png"));
            window.setIconImage(icon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create GamePanel and add it to window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();   // Responsible for resizing the window to fit its contents

        // Show window
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Setup game
        gamePanel.setupGame();

        // Start game thread
        gamePanel.startGameThread();
    }
}