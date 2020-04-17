package nl.han.ica.oopg.griddefence.Tower;

import nl.han.ica.oopg.griddefence.ClickableObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PGraphics;
import processing.core.PImage;

public class Tower extends ClickableObject {
    private Sprite towerSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Tower1.jpg");

    private int cost, range, damage, rate;

    public Tower(float x, float y, float width, float height, int cost, int range, int damage, int rate) {
        super(x, y, width, height);
        this.cost = cost;
        this.range = range;
        this.damage = damage;
        this.rate = rate;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    public void draw(PGraphics g)
	{
		g.image(towerSprite.getPImage(), x, y);
	}
	
	public PImage getImage()
	{
		return towerSprite.getPImage();
	}
}