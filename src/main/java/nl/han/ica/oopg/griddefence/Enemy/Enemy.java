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

public class Enemy extends GameObject implements ICollidableWithTiles {
    
    private GridDefence world;
    private int hp, damage;
    private float speed;
    private Sprite enemySprite;
    private boolean enemyIsAlive = true;
    private String enemyType;
    private int currency;

    /**
     * 
     * @param x           int X coordinate for the enemy
     * @param y           int Y coordinate for the enemy
     * @param size        int size for the enemy
     * @param GridDefence World the world for the enemy to be in
     * @param HashMap     HashMap of the enemy properties
     */
    public Enemy(int x, int y, int size, String enemyType, GridDefence world, HashMap<String, Integer> properties) {
        super(x, y, size, size);
        this.speed = (float) (properties.get("speed")/10);
        this.hp = properties.get("hp");
        this.damage = properties.get("damage");
        this.world = world;
        this.enemyType = enemyType;
        this.currency = properties.get("currency");
        enemySprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/" + enemyType + ".png");
        setDirectionSpeed(90, this.speed);
    }

    public void enemyTakeDamage(int damage) {
        if (this.hp - damage <= 0) {
            enemyIsAlive = false;
            // System.out.println("Enemy has " + (hp - damage) + " left.");
        } else {
            this.hp -= damage;
            // System.out.println("Enemy has " + hp + " left.");
        }
    }

    public float getEnemySpeed() {
        return speed;
    }

    public void setEnemySpeed(float updateSpeed) {
        this.speed = updateSpeed;
        setSpeed(this.speed);
    }

    public int getEnemyCurrency() {
        return currency;
    }

    public boolean getEnemyIsAlive() {
        return enemyIsAlive;
    }

    public void changeDirection(Tile tile) {

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

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    public void draw(PGraphics g) {
        g.image(enemySprite.getPImage(), x, y);
    }

    public PImage getImage() {
        return enemySprite.getPImage();
    }
}