package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.griddefence.Enemy.EnemySpawner;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class UserInterface extends GameObject {

    private int x, y;
    private EnemySpawner enemySpawner;
    private Castle castle;
    private GridDefence world;

    public UserInterface(Castle castle, EnemySpawner enemySpawner, GridDefence world) {
        this.castle = castle;
        this.enemySpawner = enemySpawner;
        this.world = world;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(PGraphics g) {
        // TODO Auto-generated method stub
        g.fill(255, 255, 255); // White background
        g.rect(1000, 720, 120, 40); // Castle HP information
        g.rect(1000, 760, 120, 40); // Currency information
        g.rect(720, 0, 160, 40); // Wave number counter
        g.rect(600, 680, 400, 120); //Tower choice window

        //Tower information window
        g.rect(0, 680, 240, 120); // Window outlining
        g.rect(0, 720, 120, 40); // Current level box
        g.rect(0, 760, 120, 40); // Current range box
        g.rect(120, 720, 120, 40); // Current damage box
        g.rect(120, 760, 120, 40); // Current rate box


        g.fill(110, 110, 110); // Grey background
        g.rect(1480, 760, 120, 40); // Settings button
        g.rect(0, 680, 40, 40); // Sell button
        g.rect(200, 680, 40, 40); // Upgrade button



        g.fill(0, 0, 0);
        g.textAlign(LEFT, CENTER); // Align text to leftside
        g.text("HP: "+castle.getHP(), 1020, 740); // Castle HP text
        g.text("Currency: €156", 1020, 780); // Currency text

        g.textAlign(CENTER, CENTER); // Align text to center
        g.text("Wave: "+enemySpawner.getCurrentWave(), 800, 20); // Wave number text

        if (!castle.getCastleIsAlive()) {
            g.fill(250, 0, 0);
            g.textSize(60);
            g.text("The castle has fallen!", 800, 400);
        }

        g.fill(255, 255, 255); // RESET DRAW TO WHITE
    }
}