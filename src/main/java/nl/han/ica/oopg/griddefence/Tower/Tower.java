package nl.han.ica.oopg.griddefence.Tower;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Tower extends GameObject {

    private int cost, range, damage, rate;

    public Tower(int cost, int range, int damage, int rate) {
        this.cost = cost;
        this.range = range;
        this.damage = damage;
        this.rate = rate;
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