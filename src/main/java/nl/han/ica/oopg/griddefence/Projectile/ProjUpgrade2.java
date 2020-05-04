package nl.han.ica.oopg.griddefence.Projectile;

import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;

/**
 * ProjUpgrade2 is a child of the parent Projectile.
 * <p>
 * This class is created by: Wyman Chau.
 */
public class ProjUpgrade2 extends Projectile {

    /**
     * 
     * @param world GridDefence The world for the projectile to be in.
     * @param x     float X coordinate of the projectile.
     * @param y     float Y coordinate of the projectile.
     * @param size  int size of the projectile.
     * @param enemy Enemy The enemy the projectile shoots at.
     */
    public ProjUpgrade2(GridDefence world, float x, float y, int size, Enemy enemy) {
        super(world, x, y, size, enemy);
        changeProjectileSprite();
        projectileEffect();
    }

    /**
     * Changes the current projectile sprite.
     */
    @Override
    public void changeProjectileSprite() {
        super.getProjectileSprite().setSprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/ProjUpgrade2.png");
    }

    /**
     * The effect of the projectile.
     */
    @Override
    public void projectileEffect() {
        enemy.setEnemySpeed(0);
    }
}