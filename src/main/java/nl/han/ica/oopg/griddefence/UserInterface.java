package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class UserInterface extends GameObject {
    private int x, y;

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(PGraphics g) {
        // TODO Auto-generated method stub
        g.fill(255, 255, 255);
        g.rect(1000, 720, 120, 40);
        g.rect(1000, 760, 120, 40);
        g.rect(0, 680, 240, 120);
        g.rect(720, 0, 160, 40);
        g.rect(600, 680, 400, 120);

        g.fill(110, 110, 110);
        g.rect(1480, 760, 120, 40);
        g.rect(0, 680, 40, 40);
        g.rect(200, 680, 40, 40);

        g.fill(255, 255, 255);
    }
}