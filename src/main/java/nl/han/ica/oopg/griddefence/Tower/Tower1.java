package nl.han.ica.oopg.griddefence.Tower;

import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PGraphics;

/**
 * Tower1 is the core of the game.
 * <p>
 * This file is created by: Wyman Chau.
 */

public class Tower1 extends Tower {

    private GridDefence world;
    private EnemyDetection eDetect;
    private Projectile proj;
    private int offSet = 15;
    private int offSet2 = 10;

    public Tower1(float x, float y, float width, float height, GridDefence world, int cost, int range, int damage,
            int rate) {
        super(x, y, width, height, cost, range, damage, rate, world);
        // TODO Auto-generated constructor stub

        this.eDetect = new EnemyDetection(((x - (range * world.getTileSize())) + offSet),
                ((y - (range * world.getTileSize())) + offSet), (((range * world.getTileSize()) * 2) + offSet2),
                (((range * world.getTileSize()) * 2) + offSet2));
        world.addGameObject(eDetect);
    }

    //start point center tower // endpoint center enemy > collision enemy object = remove projectile
    public void shootProjectile() {
        eDetect.getEnemyInAreaList();
        // int testX = eDetect.getEnemyInAreaList().get(0);
        
        // proj = new Projectile(x, y, 20, 20, xxxxxxx, 1);

    }
}