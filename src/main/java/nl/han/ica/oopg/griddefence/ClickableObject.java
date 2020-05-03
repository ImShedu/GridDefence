package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

/**
 * ClickableObject is used to click on methods in the game.
 * <p>
 * This class is created by: Wyman Chau.
 */
public class ClickableObject extends GameObject {

    private int towerNumber = 0;
    private String name;

    public ClickableObject(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public ClickableObject(float x, float y, float width, float height, String name) {
        super(x, y, width, height);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean checkTowerNumber() {
        if (towerNumber > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getTowerNumber() {
        return towerNumber;
    }

    public void setTowerNumber(int towerNumber) {
        this.towerNumber = towerNumber;
    }

    public boolean mouseClicked(int mouseX, int mouseY) {
        if (mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {

    }
}