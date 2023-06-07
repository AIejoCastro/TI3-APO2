package com.example.nuclear.model;

import com.example.nuclear.GameSceneOne;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy extends Drawing implements Runnable{

    private boolean isFacingRight = true;
    public Vector pos;
    private int imageIndex = 0;
    private Image[] run;
    private int bulletsReceived;

    public Enemy(Vector pos){
        this.pos = pos;
        run = new Image[4];
        for (int i = 1; i <= 4; i++) {
            String uri = "file:" + GameSceneOne.class.getResource("Enemy Rat/Walk Rat " + i + ".png").getPath();
            run[i - 1] = new Image(uri);
        }
    }

    public void changeImage() {
        // Change the image index and load the corresponding image
        imageIndex++;
        if (imageIndex >= run.length) {
            imageIndex = 0;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        // Especificar el ancho y alto deseados
        double width = 50;  // Ancho deseado
        double height = 50; // Alto deseado

        gc.drawImage(run[imageIndex], isFacingRight ? pos.getX() - 25 : pos.getX() + 25, pos.getY() - 25, isFacingRight ? 50 : -50, 50);
    }

    public boolean isAlive = true;
    @Override
    public void run() {
        //Tercer plano
        while (isAlive) {
            double deltaX = Math.random() * 6 - 3;
            double deltaY = Math.random() * 6 - 3;
            pos.setY(pos.getY() + deltaY);
            pos.setX(pos.getX() + deltaX);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Vector getPos() {
        return pos;
    }

    public void setPos(Vector pos) {
        this.pos = pos;
    }

    public void setFacingRight(boolean facingRight) {
        isFacingRight = facingRight;
    }

    public int getBulletsReceived() {
        return bulletsReceived;
    }

    public void setBulletsReceived(int bulletsReceived) {
        this.bulletsReceived = bulletsReceived;
    }
}