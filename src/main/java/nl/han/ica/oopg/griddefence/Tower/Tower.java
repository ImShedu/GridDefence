package nl.han.ica.oopg.griddefence.Tower;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PGraphics;

public class Tower extends SpriteObject {

    private int cost, range, damage, rate;

    public Tower(int cost, int range, int damage, int rate) {
        super(new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Tower1.jpg"));
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