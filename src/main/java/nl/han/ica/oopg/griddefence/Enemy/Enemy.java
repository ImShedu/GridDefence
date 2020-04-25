package nl.han.ica.oopg.griddefence.Enemy;

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
    private int hp, damage;
    private float speed;
    private Sprite enemySprite;
    private GridDefence world;
    private Castle castle;
    private boolean enemyIsAlive = true;

    /**
     * 
     * @param x           int X coordinate for the enemy
     * @param y           int Y coordinate for the enemy
     * @param size        int size for the enemy
     * @param speed       int of the enemy
     * @param hp          int health points fo the enemy
     * @param damage      int Damage inflicted by the enemy
     * @param GridDefence World the world for the enemy to be in
     */
    public Enemy(int x, int y, int size, int speed, int hp, int damage, String enemyType, GridDefence world, Castle castle) {
        super(x, y, size, size);
        this.speed = (float) (speed/10.0);
        this.hp = hp;
        this.damage = damage;
        //setDirection(speed);
        this.world = world;
        this.castle = castle;
        enemySprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/"+enemyType+".png");
    }

    public void enemyTakeDamage(int damage) {
        if (this.hp - damage <= 0) {
            enemyIsAlive = false;
            world.deleteGameObject(this);
            EnemySpawner.handleEnemyDeath(this);
        } else {
            this.hp -= damage;
        }
    }

    public boolean getEnemyIsAlive() {
        return enemyIsAlive;
    }

    public void setDirection(int degree, int speed) {
        setDirectionSpeed(degree, speed);
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
    	Tile tile0 = collidedTiles.get(0).getTile();
        Tile tile1 = collidedTiles.get(1).getTile();

        // Checks if next tile is castle (endtile)
        if (world.getTileMap().findTileTypeIndex(tile0) == 3 || world.getTileMap().findTileTypeIndex(tile1) == 3) {
            world.deleteGameObject(this);
            EnemySpawner.handleEnemyDeath(this);
            castle.castleHP(damage);
        }

        // Checking current position tile equal to 1 (Path tile)
        if (world.getTileMap().findTileTypeIndex(tile0) != 1) {
            changeDirection(tile1);
        } else if (world.getTileMap().findTileTypeIndex(tile1) != 1) {
            changeDirection(tile0);
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