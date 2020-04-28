package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;
import processing.core.PImage;

public class ClickableObject extends GameObject {

    private int towerNumber = 0;
    private Sprite tower1 = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower1upgrade0.png");
    private Sprite tower2 = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower2upgrade0.png");
    private Sprite tower3 = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower3upgrade0.png");

    public ClickableObject(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public boolean checkTowerNumber() {
        if (towerNumber > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setTowerNumber(int towerNumber) {
        this.towerNumber = towerNumber;
    }

    public int getTowerNumber() {
        return towerNumber;
    }

    public boolean mouseClicked(int mouseX, int mouseY) {
        if (mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height) {
            return true;
        } else {
            return false;
        }
    }

    public void printLine() {
        System.out.println(towerNumber);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
        g.fill(255, 255, 102);
        g.rect(x, y, width, height);
        g.image(tower1.getPImage(), 660, 720); // Bulbasar sprite for first tower
        g.image(tower2.getPImage(), 780, 720); // Squirtle sprite for second tower
        g.image(tower3.getPImage(), 900, 720); // Charmander sprite for third tower

        g.fill(255, 255, 255);
    }

    public PImage getImage() {
        return tower1.getPImage();
    }
}