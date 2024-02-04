package com.blidormf.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private boolean upPressed, rightPressed, downPressed, leftPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {   // Uses 'Switch Expressions' feature introduced in Java 12
            case KeyEvent.VK_UP -> this.upPressed = true;
            case KeyEvent.VK_RIGHT -> this.rightPressed = true;
            case KeyEvent.VK_DOWN -> this.downPressed = true;
            case KeyEvent.VK_LEFT -> this.leftPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {   // Uses 'Switch Expressions' feature introduced in Java 12
            case KeyEvent.VK_UP -> this.upPressed = false;
            case KeyEvent.VK_RIGHT -> this.rightPressed = false;
            case KeyEvent.VK_DOWN -> this.downPressed = false;
            case KeyEvent.VK_LEFT -> this.leftPressed = false;
        }
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }
}
