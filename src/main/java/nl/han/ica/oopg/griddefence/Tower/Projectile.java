package nl.han.ica.oopg.griddefence.Tower;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.griddefence.Enemy.EnemySpawner;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Projectile extends GameObject implements ICollidableWithGameObjects {

    private GridDefence world;
    private Enemy enemy;

    public Projectile(float x, float y, int width, int height, Enemy enemy, GridDefence world) {
        super(x, y, width, height);
        this.enemy = enemy;
        this.world = world;
    }

    // TODO Implement projectile accuracy outside of tower range (Possibility)
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g == enemy) {
                world.deleteGameObject(this);

                if (!enemy.getEnemyIsAlive()) {
                    world.deleteGameObject(enemy);
                    EnemySpawner.handleEnemyDeath(enemy); 
                } else {
                    world.deleteGameObject(this);
                }
            }
        }
    }

    @Override
    public void update() {
        setDirectionSpeed(getAngleFrom(enemy), 4);
    }

    @Override
    public void draw(PGraphics g) {
        g.fill(250, 0, 0);
        g.rect(getCenterX(), getCenterY(), getWidth(), getHeight());
        g.fill(255, 255, 255);
    }
}