package nl.han.ica.oopg.griddefence.Enemy;

import java.util.HashMap;
import java.util.List;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.griddefence.Castle;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * Enemy is an object in the game that only has one purpose. Walk from the
 * startpoint to the endpoint (Castle) on top of the road.
 * <p>
 * This class is created by: Wyman Chau.
 */
public class Enemy extends GameObject implements ICollidableWithTiles {

    private GridDefence world;
    private int hp, damage, currency, maxHP;
    private float speed;
    private Sprite enemySprite;
    private boolean enemyIsAlive = true;

    /**
     * 
     * @param world      GridDefence The world for the enemy to be in.
     * @param x          int X coordinate of the enemy.
     * @param y          int Y coordinate of the enemy.
     * @param size       int size of the enemy.
     * @param properties HashMap HashMap of the enemy properties.
     */
    public Enemy(GridDefence world, int x, int y, int size, String enemyType, HashMap<String, Integer> properties) {
        super(x, y, size, size);
        this.world = world;
        this.speed = (float) (properties.get("speed") / 10.0);
        this.hp = properties.get("hp");
        this.damage = properties.get("damage");
        this.currency = properties.get("currency");
        this.maxHP = properties.get("hp");
        enemySprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/" + enemyType + ".png");
        setDirectionSpeed(90, this.speed);
    }

    /**
     * Substracts the incoming damage from the enemy's hp.
     * 
     * @param damage int The incoming damage.
     */
    public void enemyTakeDamage(int damage) {
        if (this.hp - damage <= 0) {
            enemyIsAlive = false;
            // System.out.println("Enemy has " + (hp - damage) + " left.");
        } else {
            this.hp -= damage;
            // System.out.println("Enemy has " + hp + " left.");
        }
    }

    /**
     * Gets the enemy speed.
     * 
     * @return The enemy speed.
     */
    public float getEnemySpeed() {
        return speed;
    }

    /**
     * Sets the given number as the new speed.
     * 
     * @param updateSpeed float The given number as the new speed.
     */
    public void setEnemySpeed(float updateSpeed) {
        this.speed = updateSpeed;
        setSpeed(this.speed);
    }

    /**
     * Gets the enemy currency.
     * 
     * @return The enemy currency.
     */
    public int getEnemyCurrency() {
        return currency;
    }

    /**
     * Gets the boolean enemyIsALive, which is: is the enemy still alive?
     * 
     * @return boolean enemyIsAlive.
     */
    public boolean getEnemyIsAlive() {
        return enemyIsAlive;
    }

    /**
     * Changes the direction of this object depending on it's previous direction. If
     * it's previous direction is either 90 or 270, then the next direction has to
     * be either 180 or 0 since it cannot move forward or backwards anymore. This
     * method will check the next available tile and changes it direction to that
     * tile.
     * 
     * @param tile Tile The tile the object is colliding with.
     */
    private void changeDirection(Tile tile) {

        int x = (int) world.getTileMap().getTileIndex(tile).array()[0];
        int y = (int) world.getTileMap().getTileIndex(tile).array()[1];

        if (getDirection() == 90 || getDirection() == 270) {
            // check top bottom, change direction
            if (world.getTileMap().findTileTypeIndex(world.getTileMap().getTileOnIndex(x, y + 1)) == 1) {
                float[] tileLocation = world.getTileMap().getTilePixelLocation(tile).array();
                setX(tileLocation[0]);
                setY(tileLocation[1]);
                setDirection(180); // down
            } else {
                float[] tileLocation = world.getTileMap().getTilePixelLocation(tile).array();
                setX(tileLocation[0]);
                setY(tileLocation[1]);
                setDirection(0); // up
            }
        } else {
            // check left right, change direction
            if (world.getTileMap().findTileTypeIndex(world.getTileMap().getTileOnIndex(x + 1, y)) == 1) {
                float[] tileLocation = world.getTileMap().getTilePixelLocation(tile).array();
                setX(tileLocation[0]);
                setY(tileLocation[1]);
                setDirection(90); // right
            } else {
                float[] tileLocation = world.getTileMap().getTilePixelLocation(tile).array();
                setX(tileLocation[0]);
                setY(tileLocation[1]);
                setDirection(270); // left
            }
        }
    }

    /**
     * Gets the 2 tiles this object currently collides with and checks which tile is
     * the pathtile.
     * 
     * @param collidedTiles List The list of the tiles the current object collides
     *                      with.
     */
    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        if (collidedTiles.size() > 1) {
            Tile tile0 = collidedTiles.get(0).getTile();
            Tile tile1 = collidedTiles.get(1).getTile();

            // Checks if next tile is castle (endtile)
            if (world.getTileMap().findTileTypeIndex(tile0) == 3 || world.getTileMap().findTileTypeIndex(tile1) == 3) {
                world.deleteGameObject(this);
                EnemySpawner.handleEnemyDeath(this);
                Castle.castleHP(damage);
            }

            // Checking current position tile equal to 1 (Path tile)

            if (world.getTileMap().findTileTypeIndex(tile0) != 1) {
                changeDirection(tile1);
            } else if (world.getTileMap().findTileTypeIndex(tile1) != 1) {
                changeDirection(tile0);
            }
        }
    }

    /**
     * Implement this method to update the objects that need to be drawn.
     */
    @Override
    public void update() {
    }

    /**
     * Draws the enemy sprite on the given X and Y position and draws the HP bar of
     * each enemy.
     * 
     * @param g PGraphics the draw tool we use to draw.
     */
    public void draw(PGraphics g) {
        g.image(enemySprite.getPImage(), x, y);

        g.fill(250, 0, 0);
        g.rect(x, y - 5, 40, 8);

        g.fill(0, 250, 0);
        g.rect(x, y - 5, (40 / this.maxHP) * this.hp, 8);

        g.fill(255, 255, 255);
    }

    /**
     * Get the PImage object from the enemy sprite.
     */
    public PImage getImage() {
        return enemySprite.getPImage();
    }
}