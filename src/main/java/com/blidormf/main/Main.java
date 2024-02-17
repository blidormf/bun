package com.blidormf.main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Initialize window
        JFrame window = new JFrame();
        window.setTitle("bun.");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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