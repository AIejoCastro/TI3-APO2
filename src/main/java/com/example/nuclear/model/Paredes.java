package com.example.nuclear.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Paredes extends Drawing {
    private Canvas canvas;
    private GraphicsContext gc;
    private int x, y;
    private Rectangle rectangle;
    private Image image;

    public Paredes(Canvas canvas, String imagePath, int x, int y) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        image = new Image(imagePath);
        this.x = x;
        this.y = y;
        rectangle = new Rectangle(x - 20, y - 20, 40, 40);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x - 20, y - 20, 40, 40);
    }

    public Shape getBoundary() {
        return rectangle;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getWidth() {
        return rectangle.getWidth();
    }

    public double getHeight() {
        return rectangle.getHeight();
    }
}
