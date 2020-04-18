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
import nl.han.ica.oopg.tile.TileMap;
import processing.core.PGraphics;
import processing.core.PImage;

public class Enemy extends GameObject implements ICollidableWithTiles {
    private int speed, hp, damage;
    private Sprite enemySprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Enemy1.jpg");
    private TileMap enemyTileMap;
    private GridDefence world;

    public Enemy(int speed, int hp, int damage, TileMap enemyTileMap) {
        super(0,0,40,40);
        this.speed = speed;
        this.hp = hp;
        this.damage = damage;
        setDirection(speed);
        this.enemyTileMap = enemyTileMap;
    }

    public void setDirection(int degree, int speed) {
        setDirectionSpeed(degree, speed);
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        // TODO Auto-generated method stub
        if(world.getTileMap().findTileTypeIndex(collidedTiles.get(1).getTile()) == 1) {
            System.out.println("TESTESTSET");
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