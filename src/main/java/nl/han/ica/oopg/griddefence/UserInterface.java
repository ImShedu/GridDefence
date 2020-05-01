package nl.han.ica.oopg.griddefence;

import java.util.HashMap;
import java.util.Random;

import nl.han.ica.oopg.griddefence.Enemy.EnemySpawner;
import nl.han.ica.oopg.griddefence.Tower.Tower;
import nl.han.ica.oopg.griddefence.UpgradeStone.UpgradeStone;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PGraphics;
import processing.core.PImage;

public class UserInterface extends GameObject {

    private int x, y;
    private EnemySpawner enemySpawner;
    private GridDefence world;
    private Sprite towerSprite;
    private Sprite upgradeSprite;
    private int a = 0;
    private int b = 0;
    private int c = 0;

    public UserInterface(EnemySpawner enemySpawner, GridDefence world, Tower tower) {
        this.enemySpawner = enemySpawner;
        this.world = world;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    public void towerSelectionSprite(PGraphics g) {
        Sprite tower1 = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower1upgrade0.png");
        Sprite tower2 = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower2upgrade0.png");
        Sprite tower3 = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower3upgrade0.png");

        g.image(tower1.getPImage(), 660, 720); // Bulbasar sprite for first tower
        g.image(tower2.getPImage(), 780, 720); // Squirtle sprite for second tower
        g.image(tower3.getPImage(), 900, 720); // Charmander sprite for third tower
    }

    public void towerInformation(PGraphics g, Tower tower) {
        int towerNumber = tower.getTowerNumber();
        int upgradeNumber = tower.getUpgradeNumber();

        HashMap<String, Float> properties = tower.getTowerProperties(towerNumber, upgradeNumber);
        HashMap<String, String> propertiesName = tower.getTowerName(towerNumber, upgradeNumber);
        towerSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower" + towerNumber + "upgrade"
                + upgradeNumber + ".png");

        if (upgradeNumber < 3) {
            upgradeSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/tower" + towerNumber
                    + "upgrade" + (upgradeNumber + 1) + ".png");
        } else {
            upgradeSprite = new Sprite(
                    "src/main/java/nl/han/ica/oopg/griddefence/Resource/upgradeObject" + towerNumber + ".png");
        }

        // Text settings
        g.fill(0, 0, 0); // Textcolor to black
        g.textSize(14); // Textsize to 20
        g.textAlign(LEFT, CENTER); // Align text to leftside

        // Tower information
        g.text("Level: " + Math.round(properties.get("upgrade")), 20, 740);
        g.text("Range: " + Math.round(properties.get("range")), 20, 780);
        g.text("Dmg: " + Math.round(properties.get("damage")), 140, 740);
        g.text("Rate: " + properties.get("rate"), 140, 780);

        // Tower Sprite next to name
        g.image(towerSprite.getPImage(), 40, 680);
        g.text(propertiesName.get("name"), 85, 700);

        // Upgrade tower sprite
        g.image(upgradeSprite.getPImage(), 200, 680);

        g.textSize(12); // RESET TEXTSIZE TO 12
    }

    public void upgradeStoneSprite(PGraphics g) {
        Sprite upgradeStone1 = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/upgradeObject1.png");
        Sprite upgradeStone2 = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/upgradeObject2.png");
        Sprite upgradeStone3 = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/upgradeObject3.png");

        g.image(upgradeStone1.getPImage(), 560, 680); // Bulbasar stone for first tower
        g.image(upgradeStone2.getPImage(), 560, 720); // Squirtle stone for first tower
        g.image(upgradeStone3.getPImage(), 560, 760); // Charmander stone for first tower
    }

    public void upgradeStoneCounter(PGraphics g) {
        Random random = new Random();

        if (UpgradeStone.stoneDropped()) {
            int counter = random.nextInt(3);
            counter += 1;

            switch (counter) {
                case 1:
                    a += 1;
                    break;
                case 2:
                    b += 1;
                    break;
                case 3:
                    c += 1;
                    break;
            }
            UpgradeStone.stoneFalse();
        }

        g.text(a, 540, 700);
        g.text(b, 540, 740);
        g.text(c, 540, 780);
    }

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

        // upgradeStoneSprite window
        g.rect(520, 680, 80, 40); // Upgradestone 1
        g.rect(520, 720, 80, 40); // Upgradestone 2
        g.rect(520, 760, 80, 40); // Upgradestone 3

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

        // Upgradestone Sprite
        upgradeStoneSprite(g);

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
        upgradeStoneCounter(g); // Upgradestones counter text

        if (!Castle.getCastleIsAlive()) {
            g.fill(250, 0, 0);
            g.textSize(60);
            g.text("The castle has fallen!", 800, 400);
        }
        g.textSize(12); // RESET TEXTSIZE TO 12
        g.fill(255, 255, 255); // RESET DRAW TO WHITE
    }

    public PImage getImage() {
        return towerSprite.getPImage();
    }
}