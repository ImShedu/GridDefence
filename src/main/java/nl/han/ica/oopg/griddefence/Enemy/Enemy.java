package nl.han.ica.oopg.griddefence.Enemy;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Enemy extends GameObject {
    private int speed, hp, damage;

    public Enemy(int speed, int hp, int damage) {
        this.speed = speed;
        this.hp = hp;
        this.damage = damage;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(PGraphics g) {
        // TODO Auto-generated method stub

    }
}