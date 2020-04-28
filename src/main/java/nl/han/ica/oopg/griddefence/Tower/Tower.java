package nl.han.ica.oopg.griddefence.Tower;

import java.util.HashMap;
import java.util.Iterator;

import nl.han.ica.oopg.griddefence.ClickableObject;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;
import processing.core.PGraphics;
import processing.core.PImage;

public class Tower extends ClickableObject {

    protected GridDefence world;
    private EnemyDetection enemyDetection;
    private Sprite towerSprite;
    private int towerNumber, upgradeNumber;
    private int startTime;

    /**
     * 
     * @param x           int X coordinate for the enemy
     * @param y           int Y coordinate for the enemy
     * @param width       int width for the enemy
     * @param height      int height of the enemy
     * @param GridDefence World the world for the enemy to be in
     * @param towerNumber int towerNumber of the tower
     */
    public Tower(float x, float y, float width, float height, GridDefence world, int towerNumber) {
        super(x, y, width, height);
        this.world = world;
        this.towerNumber = towerNumber;
        upgradeNumber = 0;
        towerSprite(upgradeNumber);
        enemyDetection(towerNumber, upgradeNumber);
        startTime = 0;
    }

    public void towerSprite(int upgradeNumber) {
        towerSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower" + towerNumber + "upgrade"
                + upgradeNumber + ".png");
    }

    /**
     * Draws the enemyDetection box around the tower.Returns the number of a certain
     * enemy type to spawn for a certain wave
     * 
     * @param towerNumber   int towerNumber of the tower
     * @param upgradeNumber int upgradeNumber of the tower
     */
    public void enemyDetection(int towerNumber, int upgradeNumber) {
        HashMap<String, Float> properties = getTowerProperties(towerNumber, upgradeNumber);
        this.enemyDetection = new EnemyDetection(((x - (properties.get("range") * world.getTileSize())) + 15),
                ((y - (properties.get("range") * world.getTileSize())) + 15),
                (((properties.get("range") * world.getTileSize()) * 2) + 10),
                (((properties.get("range") * world.getTileSize()) * 2) + 10));
        world.addGameObject(enemyDetection);
    }

    /**
     * Shoots a projectile towards the enemy.
     * 
     * @param enemy Enemy that we shoot at
     */
    public void shootProjectile(Enemy enemy) {
        HashMap<String, Float> properties = getTowerProperties(towerNumber, upgradeNumber);
        enemy.enemyTakeDamage(Math.round((properties.get("damage"))));

        // X position, Y position, Width, Height, GameObject, Damage, World
        Projectile projectile = new Projectile(x, y, world.getTileSize(), world.getTileSize(), enemy, world);
        world.addGameObject(projectile);
    }

    /**
     * Selects an enemy that is within the list and is alive, calls the method
     * shootProjectile and give the enemy as target.
     */
    public void targetEnemy() {
        for (int i = 0; i < enemyDetection.getEnemyInAreaList().size(); i++) {
            Enemy target = (Enemy) enemyDetection.getEnemyInAreaList().get(i);
            if (target.getEnemyIsAlive()) {
                shootProjectile(target);
                break;
            }
        }
    }

    public float getUpgrade(int towerNumber, int upgradeNumber) {
        return getTowerProperties(towerNumber, upgradeNumber).get("upgrade");
    }

    // Upgrade >> WIP
    public void upgradeTower(int towerNumber, int upgradeNumber) {
        // Get current towernumber & upgradenumber, check cost and upgrade to next
        HashMap<String, Float> properties = getTowerProperties(towerNumber, upgradeNumber);

        // Not sure if required?
        Iterator towerIterator = getTowerProperties(towerNumber, upgradeNumber).entrySet().iterator();

        if (properties.get("upgrade") < 3) {
            // Check if you have enough currency to upgrade >> Requires currency system in
            // place
            int nextUpgrade = (int) (properties.get("upgrade") + 1);
            int newUpgradeNumber = nextUpgrade;
            // upgradeNumber = nextUpgrade;
            towerSprite(newUpgradeNumber);
            System.out.println("You have upgraded your tower to level "+newUpgradeNumber);
        } else {
            System.out.println("Max upgrade reached.");
        }
    }

    public void changeTower(int upgradeNumber) {
        Tile selectedTile;
        Sprite newTower = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower" + towerNumber
                + "upgrade" + upgradeNumber + ".png");

        selectedTile = world.getTileMap().getTileOnPosition((int) x, (int) y);
        selectedTile.setSprite(newTower);
    }

    /**
     * Refunds 40% of the paid amount and deletes the tower.
     * 
     * @param towerNumber   int towerNumber of the tower
     * @param upgradeNumber int upgradeNumber of the tower
     */
    public int sellTower(int towerNumber, int upgradeNumber) {
        HashMap<String, Float> properties = getTowerProperties(towerNumber, upgradeNumber);
        int refund = 0;

        // Refund amount
        refund = Math.round(properties.get("refund"));

        // Add refund amount to currency
        // currency.increaseCurrency(refund);

        // world.deleteGameObject(this);
        System.out.println("You have sold your tower for €" + refund + ".");
        return refund;
    }

    /**
     * Gets the upgrade, cost, range, damage and rate of the towers.
     * 
     * @param towerNumber   int towerNumber of the tower
     * @param upgradeNumber int upgradeNumber of the tower
     * @return HashMap with upgrade, cost, range, damage and rate integers
     */
    public HashMap<String, Float> getTowerProperties(int towerNumber, int upgradeNumber) {
        HashMap<String, Float> output = new HashMap<String, Float>();

        if (towerNumber == 1) {
            switch (upgradeNumber) {
                case 0:
                    output.put("upgrade", (float) 0);
                    output.put("cost", (float) 20);
                    output.put("range", (float) 3);
                    output.put("damage", (float) 15);
                    output.put("rate", (float) 1.0);
                    output.put("refund", (float) 8);
                    break;
                case 1:
                    output.put("upgrade", (float) 1);
                    output.put("cost", (float) 20);
                    output.put("range", (float) 5);
                    output.put("damage", (float) 15);
                    output.put("rate", (float) 1.5);
                    output.put("refund", (float) 16);
                    break;
                case 2:
                    output.put("upgrade", (float) 2);
                    output.put("cost", (float) 50);
                    output.put("range", (float) 5);
                    output.put("damage", (float) 25);
                    output.put("rate", (float) 2.0);
                    output.put("refund", (float) 36);
                    break;
                case 3:
                    output.put("upgrade", (float) 3);
                    output.put("cost", (float) 185);
                    output.put("range", (float) 6);
                    output.put("damage", (float) 35);
                    output.put("rate", (float) 5.0);
                    output.put("refund", (float) 110);
                    break;
            }
        } else {
            if (towerNumber == 2) {
                switch (upgradeNumber) {
                    case 0:
                        output.put("upgrade", (float) 0);
                        output.put("cost", (float) 50);
                        output.put("range", (float) 10);
                        output.put("damage", (float) 35);
                        output.put("rate", (float) 0.5);
                        output.put("refund", (float) 20);
                        break;
                    case 1:
                        output.put("upgrade", (float) 1);
                        output.put("cost", (float) 30);
                        output.put("range", (float) 12);
                        output.put("damage", (float) 35);
                        output.put("rate", (float) 0.5);
                        output.put("refund", (float) 32);
                        break;
                    case 2:
                        output.put("upgrade", (float) 2);
                        output.put("cost", (float) 55);
                        output.put("range", (float) 15);
                        output.put("damage", (float) 35);
                        output.put("rate", (float) 1.0);
                        output.put("refund", (float) 54);
                        break;
                    case 3:
                        output.put("upgrade", (float) 3);
                        output.put("cost", (float) 210);
                        output.put("range", (float) 20);
                        output.put("damage", (float) 50);
                        output.put("rate", (float) 1.5);
                        output.put("refund", (float) 138);
                        break;
                }
            } else {
                switch (upgradeNumber) {
                    case 0:
                        output.put("upgrade", (float) 0);
                        output.put("cost", (float) 75);
                        output.put("range", (float) 6);
                        output.put("damage", (float) 85);
                        output.put("rate", (float) 0.3);
                        output.put("refund", (float) 30);
                        break;
                    case 1:
                        output.put("upgrade", (float) 1);
                        output.put("cost", (float) 100);
                        output.put("range", (float) 10);
                        output.put("damage", (float) 85);
                        output.put("rate", (float) 0.3);
                        output.put("refund", (float) 70);
                        break;
                    case 2:
                        output.put("upgrade", (float) 2);
                        output.put("cost", (float) 120);
                        output.put("range", (float) 10);
                        output.put("damage", (float) 85);
                        output.put("rate", (float) 0.9);
                        output.put("refund", (float) 118);
                        break;
                    case 3:
                        output.put("upgrade", (float) 3);
                        output.put("cost", (float) 250);
                        output.put("range", (float) 12);
                        output.put("damage", (float) 100);
                        output.put("rate", (float) 1.0);
                        output.put("refund", (float) 218);
                        break;
                }
            }
        }
        return output;
    }

    /**
     * Cooldown timer for tower firerate.
     */
    public boolean globalCD() {
        HashMap<String, Float> properties = getTowerProperties(towerNumber, upgradeNumber);
        return world.millis() - startTime > (1000 / (properties.get("rate")));
    }

    @Override
    public void update() {
        // sellTower(3,1);
        if (globalCD()) {
            targetEnemy();
            // upgradeTower(towerNumber, upgradeNumber);
            startTime = world.millis();
        }
        enemyDetection.emptyList();

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