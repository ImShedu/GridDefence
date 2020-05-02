package nl.han.ica.oopg.griddefence.UpgradeStone;

import java.util.Random;

import nl.han.ica.oopg.griddefence.GridDefence;
import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

// EVERYTHING IS TESTING PURPOSE
public class UpgradeStone extends GameObject {

    protected GridDefence world;
    private static Random random = new Random();
    private static boolean stoneHasDropped = false;

    // Static for testing purpose
    public static void stoneDropRate() {

        int dropRate = random.nextInt(4);

        if (dropRate == 0) {
            // System.out.println("Dropped");
            stoneHasDropped = true;
        }
    }

    public static boolean stoneDropped() {
        if (stoneHasDropped) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean stoneFalse() {
        return stoneHasDropped = false;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {

    }

}