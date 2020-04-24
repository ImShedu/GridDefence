package nl.han.ica.oopg.griddefence.Tower;

import nl.han.ica.oopg.griddefence.ClickableObject;
import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;
import processing.core.PImage;


//TODO rewrite Tower together with Projectile, EnemyDetection and TowerNumber
public abstract class Tower extends ClickableObject {

    private Sprite towerSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower1upgrade1.png");
    private int cost, range, damage, rate;
    protected GridDefence world;
    private EnemyDetection eDetect;

    public Tower(float x, float y, float width, float height, int cost, int range, int damage, int rate, GridDefence world) {
        super(x, y, width, height);
        this.cost = cost;
        this.range = range;
        this.damage = damage;
        this.rate = rate;
        this.world = world;
    }

    //TODO methods > shootprojectile, upgrade, sell

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    //Draw towersprite
    public void draw(PGraphics g) {
        g.image(towerSprite.getPImage(), x, y);
    }

    //Return towersprite
    public PImage getImage() {
        return towerSprite.getPImage();
    }
}