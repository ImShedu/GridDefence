package nl.han.ica.oopg.griddefence.Projectile;

import nl.han.ica.oopg.griddefence.Currency;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.griddefence.Enemy.EnemySpawner;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;

/**
 * Projectile is an object from the game, used by the Tower. The projectile
 * spawns from the center of the tower and ends up colliding with an enemy.
 * <p>
 * This class is created by: Wyman Chau.
 */
public class Projectile extends GameObject {
    protected GridDefence world;
    private Enemy enemy;
    private int towerNumber, upgradeNumber;
    protected Sprite objectSprite;

    /**
     * 
     * @param world         GridDefence The world for the projectile to be in.
     * @param x             float X coordinate of the projectile.
     * @param y             float Y coordinate of the projectile.
     * @param size          int size of the projectile.
     * @param enemy         Enemy The enemy the projectile shoots at.
     * @param towerNumber   int Towernumber of the tower.
     * @param upgradeNumber int Upgradenumber of the tower.
     */
    public Projectile(GridDefence world, float x, float y, int size, Enemy enemy, int towerNumber, int upgradeNumber) {
        super(x, y, size, size);
        this.world = world;
        this.enemy = enemy;
        this.towerNumber = towerNumber;
        this.upgradeNumber = upgradeNumber;
        objectSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Pokeball.png");
        changeProjectileSprite();
        objectEffect();
    }

    public Enemy getEnemyTarget() {
        return enemy;
    }

    public void despawnObject(GameObject object) {
        // if (enemy.getEnemyIsAlive()) {
        // System.out.println("TEST0");
        // world.deleteGameObject(this);
        // }

        if (EnemySpawner.getEnemyList().contains(enemy)) {
            setDirectionSpeed(getAngleFrom(enemy), 20);
            // System.out.println("TEST1");
            // world.deleteGameObject(this);
        } else {
            // System.out.println("TEST23");
            enemy = null;
            world.deleteGameObject(this);
        }

    }

    /**
     * The effect of the projectile based on the towerNumber.
     */
    protected void objectEffect() {
        if (upgradeNumber == 4) {
            switch (towerNumber) {
                case 1: // Enemy speed 50%
                    enemy.setEnemySpeed(enemy.getEnemySpeed() / 2);
                    break;
                case 2: // Enemy speed 0%
                    enemy.setEnemySpeed(0);
                    break;
                case 3: // Enemy currency per shot
                    Currency.addCurrency(enemy.getEnemyCurrency());
                    break;
                case 4: // Enemy speed 25%
                    enemy.setEnemySpeed(enemy.getEnemySpeed() / 4);
                    break;
                case 5: // Enemy speed 300% & currency per shot
                    enemy.setEnemySpeed(enemy.getEnemySpeed() * 3);
                    Currency.addCurrency(enemy.getEnemyCurrency() * 3);
                    break;
                case 6: // Enemy speed 0% & currency per shot
                    enemy.setEnemySpeed(0);
                    Currency.addCurrency(enemy.getEnemyCurrency());
                    break;
            }
        }
    }

    /**
     * Changes the current projectile sprite based on the towerNumber.
     */
    private void changeProjectileSprite() {
        if (upgradeNumber == 4) {
            switch (towerNumber) {
                case 1:
                    objectSprite.setSprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/ProjUpgrade1.png");
                    break;
                case 2:
                    objectSprite.setSprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/ProjUpgrade2.png");
                    break;
                case 3:
                    objectSprite.setSprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/ProjUpgrade3.png");
                    break;
                case 4:
                    objectSprite.setSprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/ProjUpgrade4.png");
                    break;
                case 5:
                    objectSprite.setSprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/ProjUpgrade5.png");
                    break;
                case 6:
                    objectSprite.setSprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/ProjUpgrade6.png");
                    break;
            }
        }
    }

    /**
     * Updates the projectile angle towards the give enemy. If there are no enemies
     * there will be no projectile.
     */
    @Override
    public void update() {
        despawnObject(this);
    }

    /**
     * Draws the projectile sprite on the given X and Y position.
     * 
     * @param g PGraphics the draw tool we use to draw.
     */
    @Override
    public void draw(PGraphics g) {
        g.image(objectSprite.getPImage(), getCenterX(), getCenterY());
    }
}