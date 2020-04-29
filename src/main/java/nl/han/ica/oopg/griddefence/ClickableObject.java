package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class ClickableObject extends GameObject {

    private int towerNumber = 0;

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
        
    }
}