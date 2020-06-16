package nl.han.ica.oopg.griddefence.Projectile;

import java.util.Random;

import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;

public class Wall extends Projectile {

    private int hp, number;
    private boolean wallIsAlive = true;
    private final int MAXHP;

    public Wall(GridDefence world, float x, float y, int size, Enemy enemy, int towerNumber, int upgradeNumber) {
        super(world, x, y, size, enemy, towerNumber, upgradeNumber);
        objectSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Wall.png");
        // wallType(towerNumber);
        this.hp = number;
        this.MAXHP = number;
    }

    @Override
    protected void objectEffect() {
        // RNG WALL HP 
        Random random = new Random();
        number = random.nextInt(200);

        System.out.println("WallHP: "+number);
    }
    
    public boolean getWallIsAlive() {
        return wallIsAlive;
    }

    public void wallDamage(int damage) {
        if (this.hp - damage <= 0) {
            wallIsAlive = false;
            despawnObject(this);
        } else {
            this.hp -= damage;
        }
    }

    @Override
    public void despawnObject(GameObject object) {
        if (!wallIsAlive) {
            world.deleteGameObject(this);
        }
    }

    @Override
    public void draw(PGraphics g) {
        g.image(objectSprite.getPImage(), getCenterX(), getCenterY());

        g.fill(250, 0, 0);
        g.rect(getCenterX(), getCenterY() - 5, 40, 8);

        g.fill(0, 250, 0);
        // g.rect(x, y - 5, Math.round((40.0 * this.MAXHP) / this.hp), 8);
        g.rect(getCenterX(), getCenterY() - 5, Math.round((40.0 * this.hp) / this.MAXHP), 8);

        g.fill(255, 255, 255);
    }
}