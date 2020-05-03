package nl.han.ica.oopg.griddefence.Projectile;

import nl.han.ica.oopg.griddefence.Currency;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;

/**
 * ProjUpgrade2 is a child of the parent Projectile.
 * <p>
 * This class is created by: Wyman Chau.
 */
public class ProjUpgrade3 extends Projectile {

    public ProjUpgrade3(GridDefence world, float x, float y, int size, Enemy enemy) {
        super(world, x, y, size, enemy);
        changeProjectileSprite();
        projectileEffect();
    }
    
    @Override
    public void changeProjectileSprite() {
        super.getProjectileSprite().setSprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/ProjUpgrade3.png");
    }

    @Override
    public void projectileEffect() {
        Currency.addCurrency(enemy.getEnemyCurrency());
    }
}