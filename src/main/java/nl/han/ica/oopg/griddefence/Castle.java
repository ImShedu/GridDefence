package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

/**
 * Castle is an object in the game that acts as the endpoint for the enemies in
 * the game. There's only 1 instance of Castle
 * <p>
 * This class is created by: Wyman Chau.
 */
public class Castle extends GameObject {

    private static int hp = 100;
    private static boolean castleIsAlive = true;

    /**
     * Substracts the incoming damage from the castle's hp.
     * 
     * @param damage int The incoming damage.
     */
    public static void castleHP(int damage) {
        if (hp - damage <= 0) {
            castleIsAlive = false;
        } else {
            hp -= damage;
        }
    }

    /**
     * Gets the HP of the castle.
     * 
     * @return The castle's HP.
     */
    public static int getHP() {
        return hp;
    }

    /**
     * Gets the boolean castleIsAlive, which is: is the castle still alive?
     * 
     * @return boolean castleIsAlive.
     */
    public static boolean getCastleIsAlive() {
        return castleIsAlive;
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