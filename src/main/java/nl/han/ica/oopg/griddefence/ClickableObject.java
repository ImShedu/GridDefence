package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

/**
 * ClickableObject is used to click on the objects we made. These are either
 * objects we spawn (Tower) or objects for the User Interface such as selecting,
 * upgrading or selling the tower.
 * <p>
 * This class is created by: Wyman Chau.
 */
public class ClickableObject extends GameObject {

    private int towerNumber = 0;
    private String name;

    /**
     * 
     * @param x      float X coordinate of the clickable object.
     * @param y      float Y coordinate of the clickable object.
     * @param width  float Width of the clickable object.
     * @param height float Height of the clickable object.
     */
    public ClickableObject(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    /**
     * 
     * @param x      float X coordinate of the clickable object.
     * @param y      float Y coordinate of the clickable object.
     * @param width  float Width of the clickable object.
     * @param height float Height of the clickable object.
     * @param name   String Name of the clickeable object.
     */
    public ClickableObject(float x, float y, float width, float height, String name) {
        super(x, y, width, height);
        this.name = name;
    }

    /**
     * Gets the name of the clickable object.
     * 
     * @return The name of the clickable object.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the number of the tower is higher than 0. If the number is higher
     * than 0, then the tower has been assigned.
     * 
     * @return The tower exist.
     */
    public boolean checkTowerNumber() {
        if (towerNumber > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the number of the tower.
     * 
     * @return The number of the tower.
     */
    public int getTowerNumber() {
        return towerNumber;
    }

    /**
     * Sets the number of the tower to the given number.
     */
    public void setTowerNumber(int towerNumber) {
        this.towerNumber = towerNumber;
    }

    /**
     * Checks if the mouseclick is within the clickable object. If it is, it returns
     * true, otherwise false.
     * 
     * @return The mouseclick is within the clickable object.
     */
    public boolean mouseClicked(int mouseX, int mouseY) {
        if (mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Implement this method to update the objects that need to be drawn.
     */
    @Override
    public void update() {
    }

    /**
     * Implement this method to actually draw the GameObject.
     * 
     * @param g PGraphics the draw tool we use to draw.
     */
    @Override
    public void draw(PGraphics g) {
    }
}