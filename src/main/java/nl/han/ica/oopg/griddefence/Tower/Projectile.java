package nl.han.ica.oopg.griddefence.Tower;

import java.util.HashMap;
import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.griddefence.Enemy.EnemySpawner;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Projectile extends GameObject implements ICollidableWithGameObjects {

    private GridDefence world;
    private int damage;
    // private GameObject enemy;
    private Enemy enemy;

    public Projectile(float x, float y, int width, int height, /* GameObject enemy */ Enemy enemy, int damage,
            GridDefence world) {
        super(x, y, width, height);
        this.enemy = enemy;
        this.damage = damage;
        this.world = world;
    }

    //TODO Projectile needs to be completly rewritten due to not working as intended
    //TODO Calculate projectile damage at same time as animation > prevent tower from overshooting (After basegame)
    //TODO Implement projectile accuracy outside of tower range (Possibility)
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        // TODO Auto-generated method stub
        for (GameObject g : collidedGameObjects) {
            if (g == enemy) {
                if (damage > 0) {
                    enemy.doDamage(this.damage);
                    // System.out.println("Damage done to enemy "+this.damage);
                }
                world.deleteGameObject(this);
            }
            deleteProjectile();
        }
    }

    public void deleteProjectile() {
        if (enemy.getEnemyIsAlive() == false) {
            world.deleteGameObject(this);
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        setDirectionSpeed(getAngleFrom(enemy), 20);
    }

    @Override
    public void draw(PGraphics g) {
        // TODO Auto-generated method stub
        g.fill(250, 0, 0);
        g.rect(getX(), getY(), getWidth(), getHeight());
        g.fill(255, 255, 255);
    }
}