package nl.han.ica.oopg.griddefence.Enemy;

import java.util.List;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Tower.Tower;
import nl.han.ica.oopg.griddefence.Tower.Tower1;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import processing.core.PGraphics;
import processing.core.PImage;

public class Enemy extends GameObject implements ICollidableWithTiles {
    private int speed, hp, damage;
    private Sprite enemySprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Enemy1.jpg");
    private TileMap enemyTileMap;
    private GridDefence world;

    public Enemy(int x, int y, int size, int speed, int hp, int damage, GridDefence world) {
        super(x, y, size, size);
        this.speed = speed;
        this.hp = hp;
        this.damage = damage;
        setDirection(speed);
        this.world = world;
    }


    // Flawed method/implementation
    public void setDirection(int degree, int speed) {
        setDirectionSpeed(degree, speed);
    }

    public void directionUp() {
        setDirection(0);
    }

    public void directionDown() {
        setDirection(180);
    }

    public void directionLeft() {
        setDirection(270);
    }

    public void directionRight() {
        setDirection(90);
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        // TODO Auto-generated method stub
        // int x = Math.round(getCenterX());
        // int y = Math.round(getCenterY());
        int x = (Math.round(getCenterX()) / 40);
        int y = (Math.round(getCenterY()) / 40);
        Sprite testSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Castle1.jpg");

        if (world.getTileMap().findTileTypeIndex(collidedTiles.get(1).getTile()) != 1) {
            // System.out.println("x: " + x + " / " + "y: " + y);
            // System.out.println(world.getTileMap().getTileOnPosition(x,y));
            // System.out.println(world.getTileMap().getTileOnPosition(x-40,y));
            // System.out.println(world.getTileMap().getTileOnPosition(x,y-40));
            // System.out.println(world.getTileMap().findTileTypeIndex(collidedTiles.get(1).getTile()));
            // System.out.println(world.getTileMap().findTileTypeIndex(world.getTileMap().getTileOnPosition(x,
            // y)));
            // System.out.println("Direction: " + getDirection());
            // collidedTiles.get(1).getTile().setSprite(testSprite);

            if (world.getTileMap().findTileTypeIndex(world.getTileMap().getTileOnIndex(x, y + 1)) == 1
                    && getDirection() != 0) {
                float[] tileLocation = world.getTileMap().getTilePixelLocation(world.getTileMap().getTileOnIndex(x, y))
                        .array();
                setX(tileLocation[0]);
                setY(tileLocation[1]);
                directionDown();
            } else if (world.getTileMap().findTileTypeIndex(world.getTileMap().getTileOnIndex(x, y - 1)) == 1
                    && getDirection() != 180) {
                float[] tileLocation = world.getTileMap().getTilePixelLocation(world.getTileMap().getTileOnIndex(x, y))
                        .array();
                setX(tileLocation[0]);
                setY(tileLocation[1]);
                directionUp();
            } else if (world.getTileMap().findTileTypeIndex(world.getTileMap().getTileOnIndex(x + 1, y)) == 1
                    && getDirection() != 270) {
                float[] tileLocation = world.getTileMap().getTilePixelLocation(world.getTileMap().getTileOnIndex(x, y))
                        .array();
                setX(tileLocation[0]);
                setY(tileLocation[1]);
                directionRight();
            } else if (world.getTileMap().findTileTypeIndex(world.getTileMap().getTileOnIndex(x - 1, y)) == 1
                    && getDirection() != 90) {
                float[] tileLocation = world.getTileMap().getTilePixelLocation(world.getTileMap().getTileOnIndex(x, y))
                        .array();
                setX(tileLocation[0]);
                setY(tileLocation[1]);
                // System.out.println(getDirection());
                directionLeft();
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