package nl.han.ica.oopg.griddefence.Tower;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Projectile extends GameObject implements ICollidableWithGameObjects {

    private GridDefence world;
    private int damage;
    private GameObject enemy;

    public Projectile(float x, float y, int width, int height, GameObject enemy, int damage, GridDefence world) {
        super(x, y, width, height);
        this.enemy = enemy;
        this.damage = damage;
        this.world = world;
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        // TODO Auto-generated method stub
		for (GameObject g : collidedGameObjects) {
            if (g == enemy) {
                //TODO damage
                world.deleteGameObject(this);
            }
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        setDirectionSpeed(getAngleFrom(enemy), 50);
    }

    @Override
    public void draw(PGraphics g) {
        // TODO Auto-generated method stub
        g.fill(250, 0, 0);
        g.rect(getX(), getY(), getWidth(), getHeight());
        g.fill(255, 255, 255);
    }
}