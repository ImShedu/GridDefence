package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Castle extends GameObject {

    private int hp = 100;
    private GridDefence world;
    private boolean castleIsAlive = true;

    public Castle(GridDefence world) {
        this.world = world;
    }

    public void castleHP(int damage) {
        if (this.hp - damage <= 0) {
            world.pauseGame();
            System.out.println("The castle has fallen.");
            castleIsAlive = false;
        } else {
            this.hp -= damage;
            System.out.println("Castle has "+this.hp+" hp left.");
        }
    }

    public int getHP() {
        return this.hp;
    }

    public boolean getCastleIsAlive() {
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