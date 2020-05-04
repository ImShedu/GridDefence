package nl.han.ica.oopg.griddefence.Tower;

import java.util.ArrayList;
import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

/**
 * EnemyDetection is an object in the game, used by the Tower.
 * <p>
 * This class is created by: Wyman Chau.
 */
public class EnemyDetection extends GameObject implements ICollidableWithGameObjects {

    private ArrayList<GameObject> enemyInArea = new ArrayList<>();

    /**
     * 
     * @param x    float X coordinate of the enemy detection box.
     * @param y    float Y coordinate of the enemy detection box.
     * @param size float Size of the enemy detection box.
     */
    public EnemyDetection(float x, float y, float size) {
        super(x, y, size, size);
    }

    /**
     * Contains a list with all the GameObjects the enemy detection box collides
     * with. If the GameObject enemy collides with the box, it will add this
     * specific enemy to the list (enemyInArea).
     * 
     * @param collidedGameObjects list The list of the GameObjects that collides
     *                            with this enemy detection box.
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Enemy && !enemyInArea.contains(g)) {
                enemyInArea.add(g);
            }
        }
    }

    /**
     * Gets teh first object (enemy) in the list (enemyInArea).
     * 
     * @return The first object (enemy) in the list (enemyInArea).
     */
    public Enemy getEnemy() {
        if (!enemyInArea.isEmpty()) {
            return (Enemy) enemyInArea.get(0);
        } else {
            return null;
        }
    }

    /**
     * Empties the list (enemyInArea).
     */
    public void emptyList() {
        enemyInArea.clear();
    }

    /**
     * Gets the list (enemyInArea).
     * 
     * @return The list (enemyInArea).
     */
    public ArrayList<GameObject> getEnemyInAreaList() {
        return enemyInArea;
    }

    /**
     * Implement this method to update the objects that need to be drawn.
     */
    @Override
    public void update() {

    }
    /**
     * Draws the enemy detection box on the given X and Y position with the given
     * width and height.
     * 
     * @param g PGraphics the draw tool we use to draw.
     */
    @Override
    public void draw(PGraphics g) {
        g.fill(0, 250, 0, 50);
        g.rect(getX(), getY(), getWidth(), getHeight());
        g.fill(255, 255, 255);
    }
}