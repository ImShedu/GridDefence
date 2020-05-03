package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Castle extends GameObject {

    private static int hp = 100;
    private static boolean castleIsAlive = true;

    // Perhaps static method??
    // Check if castle is still alive
    public static void castleHP(int damage) {
        if (hp - damage <= 0) {
            // world.pauseGame();
            // System.out.println("The castle has fallen.");
            castleIsAlive = false;
        } else {
            hp -= damage;
            // System.out.println("Castle has " + this.hp + " hp left.");
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