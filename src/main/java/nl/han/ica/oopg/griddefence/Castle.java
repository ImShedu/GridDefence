package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Castle extends GameObject {

    public static int hp = 10;
    private static GridDefence world;
    public static boolean castleIsAlive = true;

    public static void castleHP(int damage) {
        if (hp - damage <= 0) {
            world.pauseGame();
            System.out.println("The castle has fallen.");
            castleIsAlive = false;
        } else {
            hp -= damage;
            System.out.println("Castle has "+hp+" hp left.");
        }
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