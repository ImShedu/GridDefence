package nl.han.ica.oopg.griddefence.Tower;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.griddefence.Currency;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.griddefence.Enemy.EnemySpawner;
import nl.han.ica.oopg.griddefence.UpgradeStone.UpgradeStone;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;
import processing.core.PImage;

public class Projectile extends GameObject implements ICollidableWithGameObjects {

    private GridDefence world;
    private Enemy enemy;
    private Sprite projectileSprite;

    public Projectile(float x, float y, int size, Enemy enemy, GridDefence world) {
        super(x, y, size, size);
        this.enemy = enemy;
        this.world = world;
        projectileSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Pokeball.png");
    }

    // TODO Implement projectile accuracy outside of tower range (Possibility)
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g == enemy) {
                if (!enemy.getEnemyIsAlive()) {
                    world.deleteGameObject(enemy);
                    EnemySpawner.handleEnemyDeath(enemy);
                    UpgradeStone.stoneDropRate(); // >> Testing purpose
                    Currency.setCurrency(Currency.getCurrency() + enemy.getEnemyCurrency()); 
                } else {
                    world.deleteGameObject(this);
                }
            }
        }
    }

    @Override
    public void update() {
        if (EnemySpawner.getEnemyList().contains(enemy)) {
            setDirectionSpeed(getAngleFrom(enemy), 10);
        } else {
            enemy = null;
            world.deleteGameObject(this);
        }
    }

    @Override
    // Draw projectileSprite according to towerNumber & upgradeNumber
    public void draw(PGraphics g) {
        g.image(projectileSprite.getPImage(), getCenterX(), getCenterY());
    }

    // Return projectileSprite
    public PImage getImage() {
        return projectileSprite.getPImage();
    }
}