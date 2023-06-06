package com.example.nuclear.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Level {

    private int id;
    private Color color;
    private Image img;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private ArrayList<Paredes> paredes;

    private ArrayList<Weapon> weaponsInTheFloor;

    public Level(int id){
        this.id = id;
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        paredes = new ArrayList<>();
        weaponsInTheFloor = new ArrayList<>();
    }


    public void generarArmaAleatoriaEnSuelo(String name,double mapaAncho, double mapaAlto) {
        int randomX = (int) (Math.random() * mapaAncho);
        int randomY = (int) (Math.random() * mapaAlto);

        Vector posicion = new Vector(randomX, randomY);


        Weapon armaEnSuelo = new Weapon(name,randomX ,randomY);



        weaponsInTheFloor.add(armaEnSuelo);
    }


    public ArrayList<Weapon> getWeaponsInTheFloor() {
        return weaponsInTheFloor;
    }

    public void setWeaponsInTheFloor(ArrayList<Weapon> weaponsInTheFloor) {
        this.weaponsInTheFloor = weaponsInTheFloor;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public ArrayList<Paredes> getParedes() {
        return paredes;
    }

    public void setParedes(ArrayList<Paredes> paredes) {
        this.paredes = paredes;
    }
}
