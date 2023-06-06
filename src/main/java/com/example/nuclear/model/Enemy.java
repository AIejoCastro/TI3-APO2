package com.example.nuclear.model;

import com.example.nuclear.HelloApplication;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Enemy extends Drawing implements Runnable{

    public Vector pos;

    public Enemy(Vector pos){
        this.pos = pos;
    }

    @Override
    public void draw(GraphicsContext gc) {
        Image image = new Image("file:" + HelloApplication.class.getResource("Enemy Rat/Idle Rat 1.png").getPath());

        // Especificar el ancho y alto deseados
        double width = 30;  // Ancho deseado
        double height = 30; // Alto deseado

        // Dibujar la imagen en el GraphicsContext con el tamaño especificado
        gc.drawImage(image, pos.getX(), pos.getY(), width, height);
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
}