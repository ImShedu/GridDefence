package nl.han.ica.oopg.griddefence.Projectile;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.griddefence.Currency;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.griddefence.Enemy.EnemySpawner;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * Projectile is an object from the game, used by the Tower. The projectile
 * spawns from the center of the tower and ends up colliding with an enemy.
 * <p>
 * This class is created by: Wyman Chau.
 */
public class Projectile extends GameObject implements ICollidableWithGameObjects {
    private GridDefence world;
    protected Enemy enemy;
    private Sprite projectileSprite;

    /**
     * 
     * @param world GridDefence The world for the projectile to be in.
     * @param x     float X coordinate of the projectile.
     * @param y     float Y coordinate of the projectile.
     * @param size  int size of the projectile.
     * @param enemy Enemy The enemy the projectile shoots at.
     */
    public Projectile(GridDefence world, float x, float y, int size, Enemy enemy) {
        super(x, y, size, size);
        this.world = world;
        this.enemy = enemy;
        projectileSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Pokeball.png");
    }

    /**
     * Contains a list with all the GameObjects the projectile collides at. If the
     * projectile collides with our enemy, it will remove the projectile and the
     * enemy if that's necessary.
     * 
     * @param collidedGameObjects list The list of the GameObjects the projectile
     *                            collides with.
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g == enemy) {
                if (!enemy.getEnemyIsAlive()) {
                    world.deleteGameObject(enemy);
                    EnemySpawner.handleEnemyDeath(enemy);
                    Currency.addCurrency(enemy.getEnemyCurrency());
                } else {
                    world.deleteGameObject(this);
                }
            }
        }
    }

    /**
     * Gets the sprite of this projectile.
     * 
     * @return The sprite of this projectile.
     */
    protected Sprite getProjectileSprite() {
        return projectileSprite;
    }

    /**
     * Changes the current projectile sprite.
     */
    protected void changeProjectileSprite() {
    }

    /**
     * The effect of the projectile.
     */
    protected void projectileEffect() {
    }

    /**
     * Updates the projectile angle towards the give enemy. If there are no enemies
     * there will be no projectile.
     */
    @Override
    public void update() {
        if (EnemySpawner.getEnemyList().contains(enemy)) {
            setDirectionSpeed(getAngleFrom(enemy), 20);
        } else {
            enemy = null;
            world.deleteGameObject(this);
        }
    }

    /**
     * Draws the projectile sprite on the given X and Y position.
     * 
     * @param g PGraphics the draw tool we use to draw.
     */
    @Override
    public void draw(PGraphics g) {
        g.image(projectileSprite.getPImage(), getCenterX(), getCenterY());
    }

    /**
     * Get the PImage object from the projectile sprite.
     */
    public PImage getImage() {
        return projectileSprite.getPImage();
    }
}