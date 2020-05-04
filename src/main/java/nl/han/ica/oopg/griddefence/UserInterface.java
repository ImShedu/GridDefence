package nl.han.ica.oopg.griddefence;

import java.util.HashMap;

import nl.han.ica.oopg.griddefence.Enemy.EnemySpawner;
import nl.han.ica.oopg.griddefence.Tower.Tower;
import nl.han.ica.oopg.griddefence.Tower.TowerStatistics;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * UserInterface contains all the drawings and text on the screen that counts as
 * User Interface. This contains all the windows, all the text that and
 * information that are not part of the game but part of the User Interface.
 * <p>
 * This class is created by: Wyman Chau.
 */
public class UserInterface extends GameObject {

    private GridDefence world;
    private EnemySpawner enemySpawner;
    private Sprite towerSprite;

    /**
     * 
     * @param world        GridDefence The world for the tower to be in.
     * @param enemySpawner EnemySpawner The spawner the interface needs information
     *                     about.
     * @param tower        Tower The tower the interface needs information about.
     */
    public UserInterface(GridDefence world, EnemySpawner enemySpawner, Tower tower) {
        this.enemySpawner = enemySpawner;
        this.world = world;
    }

    /**
     * Displays the sprites of the tower inside the tower selection box.
     */
    private void towerSelectionSprite(PGraphics g) {
        Sprite tower1 = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower1upgrade1.png");
        Sprite tower2 = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower2upgrade1.png");
        Sprite tower3 = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower3upgrade1.png");

        g.image(tower1.getPImage(), 660, 720); // Bulbasar sprite for first tower
        g.image(tower2.getPImage(), 780, 720); // Squirtle sprite for second tower
        g.image(tower3.getPImage(), 900, 720); // Charmander sprite for third tower
    }

    /**
     * Displays all the tower information of the currently selected tower inside the
     * tower information box. If there is no tower selected, the tower information
     * box will not display any information.
     * 
     * @param g     PGraphics the draw tool we use to draw.
     * @param tower Tower The tower the interface needs information about.
     */
    private void towerInformation(PGraphics g, Tower tower) {
        Sprite upgradeSprite;
        HashMap<String, Float> towerStats;
        HashMap<String, String> towerName;

        int towerNumber = tower.getTowerNumber();
        int upgradeNumber = tower.getUpgradeNumber();
        towerStats = TowerStatistics.getTowerStats(towerNumber, upgradeNumber);
        towerName = TowerStatistics.getTowerName(towerNumber, upgradeNumber);

        towerSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower" + towerNumber + "upgrade"
                + upgradeNumber + ".png");

        if (upgradeNumber < 4) {
            upgradeSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower" + towerNumber
                    + "upgrade" + (upgradeNumber + 1) + ".png");
        } else {
            upgradeSprite = new Sprite(
                    "src/main/java/nl/han/ica/oopg/griddefence/Resource/InfoProjUpgrade" + towerNumber + ".png");
        }

        // Text settings
        g.fill(0, 0, 0); // Textcolor to black
        g.textSize(14); // Textsize to 20
        g.textAlign(LEFT, CENTER); // Align text to leftside

        // Tower information
        g.text("Level: " + Math.round(towerStats.get("upgrade")), 20, 740);
        g.text("Range: " + Math.round(towerStats.get("range")), 20, 780);
        g.text("Dmg: " + Math.round(towerStats.get("damage")), 140, 740);
        g.text("Rate: " + towerStats.get("rate"), 140, 780);

        // Tower Sprite next to name
        g.image(towerSprite.getPImage(), 40, 680);
        g.text(towerName.get("name"), 85, 700);

        // Upgrade tower sprite
        g.image(upgradeSprite.getPImage(), 200, 680);

        g.textSize(12); // RESET TEXTSIZE TO 12
    }

    /*
     * Draws all the basic User Interface windows, boxes, images and buttons on the
     * given X and Y position. Also displays the tower information, currency, castle
     * HP and current wave on the screen.
     */
    @Override
    public void draw(PGraphics g) {
        // Basic UI windows
        g.fill(255, 255, 255); // White background
        g.rect(1000, 720, 120, 40); // Castle HP information
        g.rect(1000, 760, 120, 40); // Currency information
        g.rect(720, 0, 160, 40); // Wave number counter
        g.rect(600, 680, 400, 120); // Tower choice window

        // Tower selection windows
        g.rect(640, 720, 80, 40); // Tower 1 selection
        g.rect(760, 720, 80, 40); // Tower 2 selection
        g.rect(880, 720, 80, 40); // Tower 3 selection

        // Tower information windows
        g.rect(0, 680, 240, 120); // Window outlining
        g.rect(0, 720, 120, 40); // Current level box
        g.rect(0, 760, 120, 40); // Current range box
        g.rect(120, 720, 120, 40); // Current damage box
        g.rect(120, 760, 120, 40); // Current rate box

        // Basic UI buttons
        g.fill(255, 255, 102); // Yellow background
        g.rect(1480, 760, 120, 40); // Pause button
        g.rect(0, 680, 40, 40); // Sell button
        g.rect(200, 680, 40, 40); // Upgrade button

        // Tower selection windows
        g.rect(640, 720, 80, 40);
        g.rect(760, 720, 80, 40);
        g.rect(880, 720, 80, 40);

        // Actual Tower information
        if (world.getTowerClicked() != null) {
            towerInformation(g, world.getTowerClicked());
        }

        // Tower selection Sprite
        towerSelectionSprite(g);

        // Texts
        g.fill(0, 0, 0); // Textcolor to black
        g.textSize(20); // Textsize to 20
        g.textAlign(LEFT, CENTER); // Align text to leftside
        g.text("HP: " + Castle.getHP(), 1020, 740); // Castle HP text
        g.text("€" + Currency.getCurrency(), 1020, 780); // Currency text

        g.textAlign(CENTER, CENTER); // Align text to center
        g.text("Wave: " + enemySpawner.getCurrentWave(), 800, 20); // Wave number text
        g.text("$", 20, 700); // Sell text

        if (world.getGameIsPaused()) {
            g.text("Resume", 1540, 780); // Pause game text
        } else {
            g.text("Pause", 1540, 780); // Resume game text
        }

        if (!Castle.getCastleIsAlive()) {
            g.fill(250, 0, 0);
            g.textSize(60);
            g.text("The castle has fallen!", 800, 400);
        }
        g.textSize(12); // RESET TEXTSIZE TO 12
        g.fill(255, 255, 255); // RESET DRAW TO WHITE
    }

    /*
     * Get the PImage object from the tower sprite.
     */
    public PImage getImage() {
        return towerSprite.getPImage();
    }

    /*
     * Implement this method to update the objects that need to be drawn.
     */
    @Override
    public void update() {
    }
}