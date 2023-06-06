package com.example.nuclear.model;

import com.example.nuclear.GameSceneOne;
import com.example.nuclear.HelloApplication;
import com.example.nuclear.model.Drawing;
import com.example.nuclear.model.Vector;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Avatar extends Drawing implements Runnable {
    private Image[] run;
    private Image[] idle;
    private int imageIndex = 0;
    private boolean isMoving;
    private boolean isFacingRight = true;
    public Vector pos = new Vector(100, 100);
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private Thread animationThread;

    public Avatar() {
        run = new Image[6];
        for (int i = 1; i <= 6; i++) {
            String uri = "file:" + GameSceneOne.class.getResource("W" + i + ".png").getPath();
            run[i - 1] = new Image(uri);
        }
        idle= new Image[4];
        for (int i = 1; i <= 4; i++) {
            String uri = "file:" + GameSceneOne.class.getResource("I" + i + ".png").getPath();
            idle[i - 1] = new Image(uri);
        }

        // Initialize properties
        xProperty = new SimpleDoubleProperty(pos.getX());
        yProperty = new SimpleDoubleProperty(pos.getY());

        // Start animation thread
        animationThread = new Thread(this);
        animationThread.setDaemon(true);
        animationThread.start();
    }

    private void changeImage() {
        // Change the image index and load the corresponding image
        imageIndex++;
        if (imageIndex >= run.length) {
            imageIndex = 0;
        } else {
            if (imageIndex >= idle.length) {
                imageIndex = 0;
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        // Draw the character image at the current position
        if (!isMoving) {
            gc.drawImage(idle[imageIndex],
                    isFacingRight ? pos.getX() - 25 : pos.getX() + 25,
                    pos.getY() - 25,
                    isFacingRight ? 50 : -50,
                    50);
        } else {
            gc.drawImage(run[imageIndex],
                    isFacingRight ? pos.getX() - 25 : pos.getX() + 25,
                    pos.getY() - 25,
                    isFacingRight ? 50 : -50,
                    50);
        }
    }

    public void keyPressed(String keyCode) {
        // Handle the key pressed event
        if (keyCode.equals("W") || keyCode.equals("A") || keyCode.equals("S") || keyCode.equals("D")) {
            changeImage();
        }
    }

    @Override
    public void run() {
        while (true) {
            imageIndex = (imageIndex + 1) % 3;
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void setFacingRight(boolean facingRight) {
        isFacingRight = facingRight;
    }

    public DoubleProperty getxProperty() {
        return xProperty;
    }

    public DoubleProperty getyProperty() {
        return yProperty;
    }
}
