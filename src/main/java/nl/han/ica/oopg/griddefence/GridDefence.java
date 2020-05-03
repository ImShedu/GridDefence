package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.griddefence.Enemy.EnemySpawner;
import nl.han.ica.oopg.griddefence.Tiles.BoardsTile;
import nl.han.ica.oopg.griddefence.Tiles.CastleTile;
import nl.han.ica.oopg.griddefence.Tiles.NoBuildTile;
import nl.han.ica.oopg.griddefence.Tiles.PathTile;
import nl.han.ica.oopg.griddefence.Tiles.SpawnTile;
import nl.han.ica.oopg.griddefence.Tower.Tower;
import nl.han.ica.oopg.griddefence.Tower.TowerStatistics;

import java.util.ArrayList;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import processing.core.PApplet;
import nl.han.ica.oopg.view.View;

public class GridDefence extends GameEngine {
    private final int tileSize = 40;
    private Tile previousTile;
    private ArrayList<ClickableObject> cObjects = new ArrayList<>();
    private EnemySpawner enemySpawner;
    private boolean gameIsPaused = false;
    private Tower towerBuild = null;
    private boolean towerClicked = false;
    private Sprite resetSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Board.jpg");

    public static void main(String[] args) {
        String[] processingArgs = { "nl.han.ica.oopg.griddefence.GridDefence" };
        GridDefence mySketch = new GridDefence();

        PApplet.runSketch(processingArgs, mySketch);
    }

    @Override
    public void setupGame() {
        int worldWidth = 1600;
        int worldHeight = 800;

        generateTileMap();
        createViewWithoutViewport(worldWidth, worldHeight);
        createEnemySpawner();
        drawClickButtons();
        createUI();
    }

    // Birds-eye view
    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        view.setBackground(255, 255, 255);

        setView(view);
        size(screenWidth, screenHeight);
    }

    // Non-clickable UserInterface
    private void createUI() {
        UserInterface testUI = new UserInterface(this, enemySpawner, towerBuild);
        addGameObject(testUI, 1);
    }

    private void drawClickButtons() {
        ClickableObject ButtonTowerOne = new ClickableObject(640, 720, 80, 40, "ButtonTowerOne");
        ButtonTowerOne.setTowerNumber(1);
        ClickableObject ButtonTowerTwo = new ClickableObject(760, 720, 80, 40, "ButtonTowerTwo");
        ButtonTowerTwo.setTowerNumber(2);
        ClickableObject ButtonTowerThree = new ClickableObject(880, 720, 80, 40, "ButtonTowerThree");
        ButtonTowerThree.setTowerNumber(3);

        ClickableObject ButtonUpgradeTower = new ClickableObject(200, 680, 40, 40, "ButtonUpgradeTower");
        ClickableObject ButtonSellTower = new ClickableObject(0, 680, 40, 40, "ButtonSellTower");
        ClickableObject ButtonPause = new ClickableObject(1480, 760, 120, 40, "ButtonPause");

        addGameObject(ButtonTowerOne);
        addGameObject(ButtonTowerTwo);
        addGameObject(ButtonTowerThree);
        addGameObject(ButtonUpgradeTower);
        addGameObject(ButtonSellTower);
        addGameObject(ButtonPause);

        cObjects.add(ButtonTowerOne);
        cObjects.add(ButtonTowerTwo);
        cObjects.add(ButtonTowerThree);
        cObjects.add(ButtonUpgradeTower);
        cObjects.add(ButtonSellTower);
        cObjects.add(ButtonPause);
    }

    // Clickable UserInterface (GameObjects)
    private boolean mouseClickButtons() {
        ClickableObject temp = null;
        Tower towerTemp = null;
        Tower sellTower = null;

        // Checks every ClickableObject with every click
        for (ClickableObject bo : cObjects) {
            if (bo.mouseClicked(mouseX, mouseY)) {
                temp = bo;
            }

            // Check if click is on tower
            if (bo instanceof Tower && bo.mouseClicked(mouseX, mouseY)) {
                towerTemp = (Tower) bo;
                // towerBuild = (Tower) bo;
            }

            if (bo.getName() == "ButtonUpgradeTower" && bo.mouseClicked(mouseX, mouseY) && towerClicked == true) {
                if (towerBuild.getUpgradeNumber() != 4) {
                    towerBuild.upgradeTower();
                }
            }

            if (bo.getName() == "ButtonSellTower" && bo.mouseClicked(mouseX, mouseY) && towerClicked == true) {
                towerBuild.sellTower();
                sellTower = towerBuild;
                // cObjects.remove(towerBuild);
            }

            if (bo.getName() == "ButtonPause" && bo.mouseClicked(mouseX, mouseY)) {
                String status; // >> Testing purpose
                if (!gameIsPaused) {
                    pauseGame();
                    status = "paused";
                    gameIsPaused = true;

                } else {
                    resumeGame();
                    status = "resumed";
                    gameIsPaused = false;
                }
                System.out.println("Game has been " + status + ".");
            }

        }

        if (sellTower != null) {
            cObjects.remove(sellTower);
            sellTower = null;
        }

        // Build tower on empty cell & previousTile needs to be active
        if (temp != null && previousTile != null && towerClicked == false) {
            int towerNumber = temp.getTowerNumber();
            int cost = Math.round(TowerStatistics.getTowerStats(towerNumber, 1).get("cost"));

            if (temp.checkTowerNumber()) {
                if (Currency.getCurrency() >= cost) {
                    createTower(towerNumber);

                    Currency.decreaseCurrency(cost);

                    previousTile.setSprite(resetSprite);
                    previousTile = null;
                    towerClicked = false;

                    System.out.println("You have build tower " + towerNumber + ": "
                            + TowerStatistics.getTowerName(towerNumber, 1).get("name") + ".");
                } else {
                    System.out.println("You need €" + (cost - Currency.getCurrency()) + " more.");
                }
            }
        }

        if (towerTemp != null) {
            towerClicked = true;
            towerBuild = towerTemp;
        } else {
            towerClicked = false;
        }

        if (temp != null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean getGameIsPaused() {
        return gameIsPaused;
    }

    public Tower getTowerClicked() {
        if (towerClicked) {
            towerBuild.getEnemyDetection().setVisible(true);
            return towerBuild;
        } else {
            return null;
        }
    }

    // Return tilesize for easier calculation purpose
    public int getTileSize() {
        return tileSize;
    }

    // Create tower
    public void createTower(int towerNumber) {
        TileMap TM = getTileMap();
        float[] location = TM.getTilePixelLocation(previousTile).array();

        // World, X & Y position, Width & Height (tileSize), Towernumber, Upgradenumber
        Tower newTower = new Tower(this, location[0], location[1], tileSize, tileSize, towerNumber);
        addGameObject(newTower);
        cObjects.add(newTower);
    }

    // Create enemy spawner from spawntile (Actual gamemechanic)
    public void createEnemySpawner() {
        enemySpawner = new EnemySpawner(this, 100); // Number of waves
    }

    @Override
    public void mouseClicked() {
        // Clickable objects (Testing phase)

        if (!mouseClickButtons()) {
            if (previousTile != null) {
                previousTile.setSprite(resetSprite);
                previousTile = null;
            }
            return;
        }

        TileMap TM = getTileMap();
        Tile currentTile = TM.getTileOnPosition(mouseX, mouseY);

        Sprite selectedSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Selected.jpg");

        // Checks selectedtile = buildtile
        if (TM.findTileTypeIndex(currentTile) == 0) {

            // Deselects current tile
            if (previousTile == currentTile) {
                currentTile.setSprite(resetSprite);
                towerClicked = false;
                previousTile = null;
                return;
            } else {
                // Change sprite of selected tile
                if (previousTile != null) {
                    currentTile.setSprite(selectedSprite);
                    previousTile.setSprite(resetSprite);
                    previousTile = currentTile;
                } else {
                    previousTile = currentTile;
                    previousTile.setSprite(selectedSprite);
                }
            }
        }
    }

    private void generateTileMap() {
        // Sprites van de tiles
        Sprite boardSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Board.jpg");
        Sprite pathSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Path.jpg");
        Sprite noBuildSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/NoBuild.png");
        Sprite castleSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Castle.png");
        Sprite spawnSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Spawn.png");

        TileType<BoardsTile> boardTileType = new TileType<>(BoardsTile.class, boardSprite);
        TileType<PathTile> pathTileType = new TileType<>(PathTile.class, pathSprite);
        TileType<NoBuildTile> noBuildTileType = new TileType<>(NoBuildTile.class, noBuildSprite);
        TileType<CastleTile> castleTileType = new TileType<>(CastleTile.class, castleSprite);
        TileType<SpawnTile> spawnTileType = new TileType<>(SpawnTile.class, spawnSprite);

        // TILEMAP
        TileType[] tileTypes = { boardTileType, pathTileType, noBuildTileType, castleTileType, spawnTileType };
        // int tileSize = 40;
        int tilesMap[][] = {
                { 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0 },
                { 2, 4, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 2, 2, 2 },
                { 2, 2, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 3, 2 },
                { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 2, 2, 2 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                        0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
                        0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
                        0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                        0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0 },
                { -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1 } };

        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }

    @Override
    public void update() {
        if (!Castle.getCastleIsAlive()) {
            pauseGame();
        }
    }
}