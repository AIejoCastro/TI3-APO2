package com.example.nuclear.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends Drawing {

    private Vector dir;
    public Bullet(Vector pos, Vector dir){
        this.pos = pos;
        this.dir = dir;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.DARKGRAY);
        gc.fillOval(pos.getX()-5, pos.getY()-5, 10,10);

        pos.setX( pos.getX() + dir.getX() );
        pos.setY( pos.getY() + dir.getY() );
    }
}
