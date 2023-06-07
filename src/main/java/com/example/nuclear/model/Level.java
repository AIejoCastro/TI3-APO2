package com.example.nuclear.model;

import com.example.nuclear.HelloApplication;
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
    String path0 = "file:" + HelloApplication.class.getResource("Misc/FLoorL1.png").getPath();
    String path1= "file:" + HelloApplication.class.getResource("Misc/FLoorL2.png").getPath();
    String path2 = "file:" + HelloApplication.class.getResource("Misc/FLoorL3.png").getPath();

    private ArrayList<Grenade> grenadesInTheFloor;
    private ArrayList<Weapon> weaponsInTheFloor;
    private ArrayList<ThrowGrenade> throwGrenades;

    public Level(int id){
        this.id = id;
        switch (id) {
            case 0 -> img = new Image(path0);
            case 1 -> img = new Image(path1);
            case 2 -> img = new Image(path2);
        }
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        paredes = new ArrayList<>();
        weaponsInTheFloor = new ArrayList<>();
        grenadesInTheFloor= new ArrayList<>();
        throwGrenades = new ArrayList<>();
    }


    public void generarArmaAleatoriaEnSuelo(String name,double mapaAncho, double mapaAlto) {
        int randomX = (int) (Math.random() * mapaAncho);
        int randomY = (int) (Math.random() * mapaAlto);

        Vector posicion = new Vector(randomX, randomY);


        Weapon armaEnSuelo = new Weapon(name,randomX ,randomY);



        weaponsInTheFloor.add(armaEnSuelo);
    }


    public void generarGranadasEnElSuelo(String name,double mapaAncho, double mapaAlto) {
        int randomX = (int) (Math.random() * mapaAncho);
        int randomY = (int) (Math.random() * mapaAlto);

        Vector posicion = new Vector(randomX, randomY);


        Grenade grenade = new Grenade(name,randomX ,randomY);



        grenadesInTheFloor.add(grenade);
    }


    public ArrayList<Grenade> getGrenadesInTheFloor() {
        return grenadesInTheFloor;
    }

    public void setGrenadesInTheFloor(ArrayList<Grenade> grenadesInTheFloor) {
        this.grenadesInTheFloor = grenadesInTheFloor;
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

    public ArrayList<ThrowGrenade> getThrowGranades() {
        return throwGrenades;
    }

    public void setThrowGranades(ArrayList<ThrowGrenade> throwGranades) {
        this.throwGrenades = throwGranades;
    }
}