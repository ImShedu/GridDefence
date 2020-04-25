package nl.han.ica.oopg.griddefence.Tower;

import java.util.HashMap;
import java.util.Iterator;

import nl.han.ica.oopg.griddefence.ClickableObject;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;
import processing.core.PImage;

public class Tower extends ClickableObject {

    protected GridDefence world;
    private EnemyDetection eDetect;
    private Sprite towerSprite;
    private int towerNumber, upgradeNumber;

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
        enemyDetection(towerNumber, upgradeNumber);
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
        this.eDetect = new EnemyDetection(((x - (properties.get("range") * world.getTileSize())) + 15),
                ((y - (properties.get("range") * world.getTileSize())) + 15),
                (((properties.get("range") * world.getTileSize()) * 2) + 10),
                (((properties.get("range") * world.getTileSize()) * 2) + 10));
        world.addGameObject(eDetect);
    }

    public void shootProjectile(Enemy enemy) {
        HashMap<String, Float> properties = getTowerProperties(towerNumber, upgradeNumber);

        enemy.enemyTakeDamage(Math.round((properties.get("damage"))));
        System.out.println("Damage done to enemy" + Math.round((properties.get("damage"))));

        // X position, Y position, Width, Height, GameObject, Damage, World
        Projectile proj = new Projectile(x, y, 20, 20, enemy, world);
        world.addGameObject(proj);

    }

    public float getUpgrade(int towerNumber, int upgradeNumber) {
        return getTowerProperties(towerNumber, upgradeNumber).get("upgrade");
    }

    public void upgradeTower(int towerNumber, int upgradeNumber) {
        // Get current towernumber & upgradenumber, check cost and upgrade to next
        HashMap<String, Float> properties = getTowerProperties(towerNumber, upgradeNumber);
        Iterator towerIterator = getTowerProperties(towerNumber, upgradeNumber).entrySet().iterator();

        if (properties.get("upgrade") <= 3) {
            // Check if you have enough currency to upgrade >> Requires currency system in
            // place
            float nextUpgrade = properties.get("upgrade") + 1;
            upgradeNumber = (int) nextUpgrade;
        }
    }

    public void sellTower() {

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
                    break;
                case 1:
                    output.put("upgrade", (float) 1);
                    output.put("cost", (float) 20);
                    output.put("range", (float) 5);
                    output.put("damage", (float) 15);
                    output.put("rate", (float) 1.5);
                    break;
                case 2:
                    output.put("upgrade", (float) 2);
                    output.put("cost", (float) 50);
                    output.put("range", (float) 5);
                    output.put("damage", (float) 25);
                    output.put("rate", (float) 2.0);
                    break;
                case 3:
                    output.put("upgrade", (float) 3);
                    output.put("cost", (float) 185);
                    output.put("range", (float) 6);
                    output.put("damage", (float) 35);
                    output.put("rate", (float) 5.0);
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
                        break;
                    case 1:
                        output.put("upgrade", (float) 1);
                        output.put("cost", (float) 30);
                        output.put("range", (float) 12);
                        output.put("damage", (float) 35);
                        output.put("rate", (float) 0.5);
                        break;
                    case 2:
                        output.put("upgrade", (float) 2);
                        output.put("cost", (float) 55);
                        output.put("range", (float) 15);
                        output.put("damage", (float) 35);
                        output.put("rate", (float) 1.0);
                        break;
                    case 3:
                        output.put("upgrade", (float) 3);
                        output.put("cost", (float) 210);
                        output.put("range", (float) 20);
                        output.put("damage", (float) 50);
                        output.put("rate", (float) 1.5);
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
                        break;
                    case 1:
                        output.put("upgrade", (float) 1);
                        output.put("cost", (float) 100);
                        output.put("range", (float) 10);
                        output.put("damage", (float) 85);
                        output.put("rate", (float) 0.3);
                        break;
                    case 2:
                        output.put("upgrade", (float) 2);
                        output.put("cost", (float) 120);
                        output.put("range", (float) 10);
                        output.put("damage", (float) 85);
                        output.put("rate", (float) 0.9);
                        break;
                    case 3:
                        output.put("upgrade", (float) 3);
                        output.put("cost", (float) 250);
                        output.put("range", (float) 12);
                        output.put("damage", (float) 100);
                        output.put("rate", (float) 1.0);
                        break;
                }
            }
        }
        return output;
    }

    @Override
    public void update() {
        if (!eDetect.getEnemyInAreaList().isEmpty()) {
            Enemy enemy = (Enemy) eDetect.getEnemyInAreaList().get(0); // Casting to Enemy from GameObject

            if (enemy.getEnemyIsAlive()) {
                shootProjectile(enemy);
            }
            eDetect.emptyList();
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