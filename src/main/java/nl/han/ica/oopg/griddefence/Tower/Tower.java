package nl.han.ica.oopg.griddefence.Tower;

import nl.han.ica.oopg.griddefence.ClickableObject;
import nl.han.ica.oopg.griddefence.Currency;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.griddefence.Projectile.Projectile;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;

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
     * @param world       GridDefence The world for the tower to be in.
     * @param x           float X coordinate of the tower.
     * @param y           float Y coordinate of the tower.
     * @param size        float Size of the tower.
     * @param towerNumber int TowerNumber of the tower.
     */
    public Tower(GridDefence world, float x, float y, float size, int towerNumber) {
        super(x, y, size, size);
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
    private void towerSprite() {
        towerSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower" + towerNumber + "upgrade"
                + upgradeNumber + ".png");
    }

    /**
     * Draws the enemy detection box around the tower.
     */
    private void enemyDetection() {
        float range = TowerStatistics.getTowerStats("range", towerNumber, upgradeNumber);
        int tileSize = world.getTileSize();

        this.enemyDetection = new EnemyDetection(((x - range * tileSize) + 15), ((y - range * tileSize) + 15),
                ((range * tileSize * 2) + 10));
        world.addGameObject(enemyDetection);
    }

    /**
     * Gets the enemy detection box.
     * 
     * @return The enemy detection box.
     */
    public EnemyDetection getEnemyDetection() {
        return enemyDetection;
    }

    /**
     * Shoots a projectile towards the enemy.
     * 
     * @param enemy Enemy that we shoot a projectile at.
     */
    private void shootProjectile(Enemy enemy) {
        // X & Y position, Size, GameObject, Damage, World
        Projectile projectile = new Projectile(world, x, y, 10, enemy, towerNumber, upgradeNumber);
        world.addGameObject(projectile);
        enemy.enemyTakeDamage(Math.round((TowerStatistics.getTowerStats("damage", towerNumber, upgradeNumber))));
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
     * 
     * @return upgradeNumber of the tower.
     */
    public int getUpgradeNumber() {
        return upgradeNumber;
    }

    /**
     * Get the towerNumber of the tower.
     * 
     * @return towerNumber of the tower.
     */
    public int getTowerNumber() {
        return towerNumber;
    }

    /**
     * Upgrades the tower to the next level.
     */
    public void upgradeTower() {
        int nextUpgrade = ((int) getUpgradeNumber() + 1);
        int cost = Math.round(TowerStatistics.getTowerStats("cost", towerNumber, nextUpgrade));

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
        refund = Math.round(TowerStatistics.getTowerStats("refund", towerNumber, upgradeNumber));

        // Add refund amount to currency
        Currency.addCurrency(refund);

        world.deleteGameObject(this);
        world.deleteGameObject(enemyDetection);

        System.out.println("You have sold your tower for €" + refund + ".");
    }

    /**
     * Gets the cooldown timer for tower firerate.
     * 
     * @return Timer has finished.
     */
    private boolean globalCD() {
        return world.millis()
                - startTime > (1000 / (TowerStatistics.getTowerStats("rate", towerNumber, upgradeNumber)));
    }

    /**
     * Updates the enemies inside the enemylist the target can shoot at. If the list
     * is not empty the tower picks 1 enemy to shoot at and empties the list right
     * after. We also update the drawing of the enemy detection box to be visible
     * only when the tower is clicked.
     */
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

    /**
     * Draws the tower sprite on the given X and Y position.
     * 
     * @param g PGraphics the draw tool we use to draw.
     */
    public void draw(PGraphics g) {
        g.image(towerSprite.getPImage(), x, y);
    }
}