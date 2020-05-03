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

    private static int hp = 10000;
    private static boolean castleIsAlive = true;

    // Check if castle is still alive
    public static void castleHP(int damage) {
        if (hp - damage <= 0) {
            castleIsAlive = false;
        } else {
            hp -= damage;
        }
    }

    // Return HP for text
    public static int getHP() {
        return hp;
    }

    // Return boolean for castle
    public static boolean getCastleIsAlive() {
        return castleIsAlive;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(PGraphics g) {
        // TODO Auto-generated method stub

    }
}