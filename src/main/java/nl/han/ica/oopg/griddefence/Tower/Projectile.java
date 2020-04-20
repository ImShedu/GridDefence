package nl.han.ica.oopg.griddefence.Tower;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Projectile extends GameObject {

    private int damage;
    private GameObject endpoint;

    public Projectile(int x, int y, int width, int height, GameObject endpoint, int damage) {
        super(x, y, width, height);
        this.endpoint = endpoint;
        this.damage = damage;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(PGraphics g) {
        // TODO Auto-generated method stub
        g.rect(x, y, width, height);
    }

}