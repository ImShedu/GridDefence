package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.griddefence.Enemy.Enemy;
import nl.han.ica.oopg.griddefence.Enemy.EnemySpawner;
import nl.han.ica.oopg.griddefence.Tiles.BoardsTile;
import nl.han.ica.oopg.griddefence.Tiles.CastleTile;
import nl.han.ica.oopg.griddefence.Tiles.NoBuildTile;
import nl.han.ica.oopg.griddefence.Tiles.PathTile;
import nl.han.ica.oopg.griddefence.Tiles.SpawnTile;
import nl.han.ica.oopg.griddefence.Tower.Tower;
import nl.han.ica.oopg.griddefence.Tower.Tower1;

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
    private TileMap globalTileMap;

    public static void main(String[] args) {
        String[] processingArgs = { "nl.han.ica.oopg.griddefence.GridDefence" };
        GridDefence mySketch = new GridDefence();

        PApplet.runSketch(processingArgs, mySketch);
    }

    @Override
    public void setupGame() {
        // TODO Auto-generated method stub
        int worldWidth = 1600;
        int worldHeight = 800;

        generateTileMap();
        createViewWithoutViewport(worldWidth, worldHeight);
        createUI();
        testMouse();
        createEnemySpawner();
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);

        setView(view);
        size(screenWidth, screenHeight);
    }

    private void createUI() {
        UserInterface testUI = new UserInterface();
        addGameObject(testUI, 1);
    }

    private void testMouse() {
        ClickableObject uiTowerOne = new ClickableObject(640, 720, 80, 40);
        uiTowerOne.setTowerNumber(1);
        ClickableObject uiTowerTwo = new ClickableObject(760, 720, 80, 40);
        uiTowerTwo.setTowerNumber(2);
        ClickableObject uiTowerThree = new ClickableObject(880, 720, 80, 40);
        uiTowerThree.setTowerNumber(3);

        addGameObject(uiTowerOne, 1);
        addGameObject(uiTowerTwo, 1);
        addGameObject(uiTowerThree, 1);

        cObjects.add(uiTowerOne);
        cObjects.add(uiTowerTwo);
        cObjects.add(uiTowerThree);

        ClickableObject temp = null;
        for (ClickableObject bo : cObjects) {
            if (bo.mouseClicked(mouseX, mouseY)) {
                temp = bo;
            }
        }

        if (temp != null && previousTile != null) {
            if (temp.checkTowerNumber()) {
                createTower(temp.getTowerNumber());
            }
        }

    }

    public int getTileSize() {
        return tileSize;
    }

    // TODO create building logic
    public void createTower(int towerNumber) {
        TileMap testTM = getTileMap();
        float[] test = testTM.getTilePixelLocation(previousTile).array();

        Tower1 testTowerOne = new Tower1(test[0], test[1], tileSize, tileSize, this, 1, 3, 1, 1);
        addGameObject(testTowerOne);
        cObjects.add(testTowerOne);
    }

    public void createEnemySpawner() {
        //# = amount of enemies per second
        enemySpawner = new EnemySpawner(this, 2);
    }

    public void createEnemy() {
        enemySpawner.spawnEnemy(40, 40);

        // Print out all enemy currently in a list
        // System.out.println(enemySpawner.getEnemyList());
        // ArrayList<Enemy> testDirectionList = enemySpawner.getEnemyList();
        // Enemy changeDirection = testDirectionList.get(0);
        // float direction = changeDirection.getDirection();
    }

    // private void createDashboard() {
    // Dashboard testCB = new Dashboard(600, 600, 200, 200);
    // ClickableObject test2 = new ClickableObject(600, 600, 400, 100);
    // testCB.addGameObject(test2);
    // testCB.setBackground(250, 0, 0);
    // addDashboard(testCB);

    // ClickableObject test2 = new ClickableObject(550, 600, 100, 100);
    // addGameObject(test2, 1);
    // cObjects.add(test2);
    // testCB.addGameObject(test2, 2);
    // }

    @Override
    public void keyTyped() {
        if (key == 'a') {
            createEnemy();
        }

        if (key == 's') {
            createTower(1);
        }
    }

    @Override
    public void mouseClicked() {
        testMouse();

        TileMap TM = getTileMap();
        Tile currentTile = TM.getTileOnPosition(mouseX, mouseY);

        Sprite selectedSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Selected1.jpg");
        Sprite resetSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Board1.jpg");

        // Eerste IF test checkt of geselecteerde tile = bouwbare tile
        // Tweede IF test deselect huidige tile
        // Derde IF test veranderd geselecteerde tile naar selectedSprite
        if (TM.findTileTypeIndex(currentTile) == 0) {
            if (previousTile == currentTile) {
                currentTile.setSprite(resetSprite);
                previousTile = null;
                return;
            } else {
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
        Sprite boardSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Board1.jpg");
        Sprite pathSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Path1.jpg");
        Sprite noBuildSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/NoBuild.jpg");
        Sprite castleSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Castle1.jpg");
        Sprite spawnSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Spawn1.jpg");

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
        globalTileMap = getTileMap();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }
}