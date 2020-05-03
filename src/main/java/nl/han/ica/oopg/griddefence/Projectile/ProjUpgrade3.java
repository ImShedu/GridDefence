package nl.han.ica.oopg.griddefence.Projectile;

import nl.han.ica.oopg.griddefence.Currency;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.objects.Sprite;

/**
 * ProjUpgrade2 is a child of the parent Projectile.
 * <p>
 * This class is created by: Wyman Chau.
 */
public class ProjUpgrade3 extends Projectile {

    Sprite newProjectile;

    public ProjUpgrade3(float x, float y, int size, Enemy enemy, GridDefence world) {
        super(x, y, size, enemy, world);
        changeProjectileSprite();
        projectileEffect();
    }
    
    @Override
    public void changeProjectileSprite() {
        super.getProjectileSprite().setSprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Dot.png");
    }

    @Override
    public void projectileEffect() {
        Currency.setCurrency((Currency.getCurrency() + enemy.getEnemyCurrency()));
    }
}