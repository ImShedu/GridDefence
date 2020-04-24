package nl.han.ica.oopg.griddefence.Tower;

import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.objects.GameObject;

/**
 * Tower1 is the core of the game.
 * <p>
 * This file is created by: Wyman Chau.
 */

public class Tower1 extends Tower {

    private EnemyDetection eDetect;
    private int offSet = 15;
    private int offSet2 = 10;

    public Tower1(float x, float y, float width, float height, GridDefence world, int cost, int range, int damage,
            int rate) {
        super(x, y, width, height, cost, range, damage, rate, world);
        // TODO Auto-generated constructor stub

        this.eDetect = new EnemyDetection(((x - (range * world.getTileSize())) + offSet),
                ((y - (range * world.getTileSize())) + offSet), (((range * world.getTileSize()) * 2) + offSet2),
                (((range * world.getTileSize()) * 2) + offSet2));
        world.addGameObject(eDetect);
    }

    // Shoot projectile at enemy >>>> check Projectile class!! Projectile needs a full rewrite together with Tower.
    public void shootProjectile(Enemy enemy) {
        // X position, Y position, Width, Height, GameObject, Damage, World
        Projectile proj = new Projectile(x, y, 20, 20, enemy, 1, world);
        super.world.addGameObject(proj);
    }

    @Override
    public void update() {
        if (!eDetect.getEnemyInAreaList().isEmpty()) {
            // GameObject enemy = eDetect.getEnemyInAreaList().get(0);
            // shootProjectile(enemy);

            GameObject enemyGO = eDetect.getEnemyInAreaList().get(0);
            Enemy enemy = (Enemy)enemyGO;
            shootProjectile(enemy);

            eDetect.emptyList();
        }
    }
}