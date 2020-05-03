package nl.han.ica.oopg.griddefence.Tower;

import nl.han.ica.oopg.griddefence.ClickableObject;
import nl.han.ica.oopg.griddefence.Currency;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.griddefence.Projectile.ProjUpgrade1;
import nl.han.ica.oopg.griddefence.Projectile.ProjUpgrade2;
import nl.han.ica.oopg.griddefence.Projectile.ProjUpgrade3;
import nl.han.ica.oopg.griddefence.Projectile.Projectile;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * Tower is an object in the game that you can build, upgrade and sell. The
 * tower you place on the grid will automaticly shoot enemies within it's
 * detection range.
 * <p>
 * This class is created by: Wyman Chau.
 */
public class Tower extends ClickableObject {

    private GridDefence world;
    private EnemyDetection enemyDetection;
    private Sprite towerSprite;
    private int towerNumber, upgradeNumber, startTime;

    /**
     * 
     * @param x           int X coordinate for the enemy
     * @param y           int Y coordinate for the enemy
     * @param width       int width for the enemy
     * @param height      int height of the enemy
     * @param GridDefence World the world for the enemy to be in
     * @param towerNumber int towerNumber of the tower
     */
    public Tower(GridDefence world, float x, float y, float width, float height, int towerNumber) {
        super(x, y, width, height);
        this.world = world;
        this.towerNumber = towerNumber;
        upgradeNumber = 1;
        enemyDetection();
        towerSprite();
        startTime = 0;
    }

    /**
     * Selects the sprite with the given towerNumber and upgradeNumber.
     */
    public void towerSprite() {
        towerSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower" + towerNumber + "upgrade"
                + upgradeNumber + ".png");
    }

    /**
     * Draws the enemyDetection box around the tower.
     */
    private void enemyDetection() {
        float range = TowerStatistics.getTowerStats(towerNumber, upgradeNumber).get("range");
        int tileSize = world.getTileSize();

        this.enemyDetection = new EnemyDetection(((x - range * tileSize) + 15), ((y - range * tileSize) + 15),
                ((range * tileSize * 2) + 10), ((range * tileSize * 2) + 10));
        world.addGameObject(enemyDetection);
    }

    public EnemyDetection getEnemyDetection() {
        return enemyDetection;
    }

    /**
     * Shoots a projectile towards the enemy.
     * 
     * @param enemy Enemy that we shoot a projectile at
     */
    private void shootProjectile(Enemy enemy) {
        // X & Y position, Size, GameObject, Damage, World
        Projectile projectile = new Projectile(world, x, y, 10, enemy);

        if (upgradeNumber == 4) {
            switch (towerNumber) {
                case 1:
                    projectile = new ProjUpgrade1(world, x, y, 10, enemy);
                    break;

                case 2:
                    projectile = new ProjUpgrade2(world, x, y, 10, enemy);
                    break;

                case 3:
                    projectile = new ProjUpgrade3(world, x, y, 10, enemy);
                    break;
            }
        }
        world.addGameObject(projectile);
        enemy.enemyTakeDamage(Math.round((TowerStatistics.getTowerStats(towerNumber, upgradeNumber).get("damage"))));
    }

    /**
     * Selects an enemy that is within the list and is alive, calls the method
     * shootProjectile and give the enemy as target.
     */
    private void targetEnemy() {
        for (int i = 0; i < enemyDetection.getEnemyInAreaList().size(); i++) {
            Enemy target = (Enemy) enemyDetection.getEnemyInAreaList().get(i);
            if (target.getEnemyIsAlive() && globalCD()) {
                shootProjectile(target);
                startTime = world.millis();
                break;
            }
        }
    }

    /**
     * Get the upgradeNumber of the tower.
     */
    public int getUpgradeNumber() {
        return upgradeNumber;
    }

    /**
     * Get the towerNumber of the tower.
     */
    public int getTowerNumber() {
        return towerNumber;
    }

    public int towerCost() {
        return Math.round(TowerStatistics.getTowerStats(towerNumber, upgradeNumber).get("cost"));
    }

    /**
     * Upgrades the tower to the next level.
     */
    public void upgradeTower() {
        float cost = TowerStatistics.getTowerStats(towerNumber, upgradeNumber).get("cost");
        int nextUpgrade = ((int) getUpgradeNumber() + 1);

        if (upgradeNumber <= 4) {
            if (Currency.getCurrency() >= cost) {
                Currency.decreaseCurrency((int) cost);
                this.upgradeNumber = nextUpgrade;

                towerSprite();
                world.deleteGameObject(enemyDetection);
                enemyDetection();

                if (upgradeNumber == 4) {
                    System.out.println("You have achieved max upgrade.");
                } else {
                    System.out.println("You have upgraded your tower to level " + nextUpgrade);
                }
            } else {
                System.out.println("You need €" + (cost - Currency.getCurrency()) + " more.");
            }
        } else {
            System.out.println("You have already reached max upgrade.");
        }
    }

    /**
     * Refunds 40% of the paid amount and deletes the tower.
     */
    public void sellTower() {
        int refund = 0;

        // Refund amount
        refund = Math.round(TowerStatistics.getTowerStats(towerNumber, upgradeNumber).get("refund"));

        // Add refund amount to currency
        Currency.addCurrency(refund);

        world.deleteGameObject(this);
        world.deleteGameObject(enemyDetection);

        System.out.println("You have sold your tower for €" + refund + ".");
    }

    /**
     * Cooldown timer for tower firerate.
     */
    private boolean globalCD() {
        return world.millis()
                - startTime > (1000 / (TowerStatistics.getTowerStats(towerNumber, upgradeNumber).get("rate")));
    }

    @Override
    public void update() {
        if (!enemyDetection.getEnemyInAreaList().isEmpty()) {
            targetEnemy();
        }
        enemyDetection.emptyList();

        enemyDetection.setVisible(false);

        if (world.getTowerClicked() != null) {
            isVisible();
        }
    }

    // Draw towerSprite according to towerNumber & upgradeNumber
    public void draw(PGraphics g) {
        g.image(towerSprite.getPImage(), x, y);
    }

    // Return towersprite
    public PImage getImage() {
        return towerSprite.getPImage();
    }
}