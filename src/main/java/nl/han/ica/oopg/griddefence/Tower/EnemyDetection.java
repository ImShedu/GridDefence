package nl.han.ica.oopg.griddefence.Tower;

import java.util.ArrayList;
import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class EnemyDetection extends GameObject implements ICollidableWithGameObjects {

    private ArrayList<GameObject> enemyInArea = new ArrayList<>();

    public EnemyDetection(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Enemy && !enemyInArea.contains(g)) {
                enemyInArea.add(g);
            }
        }
    }

    public Enemy getEnemy() {
        if (!enemyInArea.isEmpty()) {
            return (Enemy) enemyInArea.get(0);
        } else {
            return null;
        }

        // Enemy enemy = (Enemy) getEnemyInAreaList().get(0);
        // if (enemy.getEnemyIsAlive()) { // >> shooting multiple projectile because
        // enemy survives 1, but 1> proj are bugged
        // shootProjectile(enemy);
        // }
        // // enemyDetection.getEnemyInAreaList().remove(enemy);
        // emptyList();
        // }
    }

    public void emptyList() {
        enemyInArea.clear();
    }

    public ArrayList<GameObject> getEnemyInAreaList() {
        return enemyInArea;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
        g.fill(0, 250, 0, 50);
        g.rect(getX(), getY(), getWidth(), getHeight());
        g.fill(255, 255, 255);
    }
}