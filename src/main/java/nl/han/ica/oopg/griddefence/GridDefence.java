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
    private final int TILESIZE = 40;
    private Tile previousTile;
    private ArrayList<ClickableObject> cObjects = new ArrayList<>();
    private EnemySpawner enemySpawner;
    private boolean gameHasStarted = false;
    private boolean gameIsPaused = false;
    private Tower towerBuild = null;
    private boolean towerClicked = false;
    private ClickableObject buttonModeInf, buttonModeChl;
    private Sprite resetSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Board.jpg");

    public static void main(String[] args) {
        String[] processingArgs = { "nl.han.ica.oopg.griddefence.GridDefence" };
        GridDefence mySketch = new GridDefence();

        PApplet.runSketch(processingArgs, mySketch);
    }

    /**
     * Initializes the world height and width and calls all the necessary methods.
     */
    @Override
    public void setupGame() {
        int worldWidth = 1600;
        int worldHeight = 800;

        generateTileMap();
        createViewWithoutViewport(worldWidth, worldHeight);
        createClickableButtons();
        createUI();
    }

    /**
     * Creates a new viewport, this will create a birds-eye view (topdown).
     */
    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        setView(view);
        size(screenWidth, screenHeight);
    }

    /**
     * Creates a new instance of the User Interface
     */
    private void createUI() {
        UserInterface userInterface = new UserInterface(this, towerBuild);
        addGameObject(userInterface);
    }

    /**
     * Creates all the clickable buttons and assign them an X and Y location on the
     * screen. Add the created button to the game as a GameObject and to the
     * clickable object list.
     */
    private void createClickableButtons() {
        ClickableObject ButtonTowerOne = new ClickableObject(640, 720, 40, 40, "ButtonTowerOne");
        ButtonTowerOne.setTowerNumber(1);
        ClickableObject ButtonTowerTwo = new ClickableObject(680, 720, 40, 40, "ButtonTowerTwo");
        ButtonTowerTwo.setTowerNumber(2);
        ClickableObject ButtonTowerThree = new ClickableObject(760, 720, 40, 40, "ButtonTowerThree");
        ButtonTowerThree.setTowerNumber(3);
        ClickableObject ButtonTowerFour = new ClickableObject(800, 720, 40, 40, "ButtonTowerFour");
        ButtonTowerFour.setTowerNumber(4);
        ClickableObject ButtonTowerFive = new ClickableObject(880, 720, 40, 40, "ButtonTowerFive");
        ButtonTowerFive.setTowerNumber(5);
        ClickableObject ButtonTowerSix = new ClickableObject(920, 720, 40, 40, "ButtonTowerSix");
        ButtonTowerSix.setTowerNumber(6);

        ClickableObject ButtonUpgradeTower = new ClickableObject(200, 680, 40, 40, "ButtonUpgradeTower");
        ClickableObject ButtonSellTower = new ClickableObject(0, 680, 40, 40, "ButtonSellTower");
        ClickableObject ButtonPause = new ClickableObject(1480, 760, 120, 40, "ButtonPause");

        buttonModeInf = new ClickableObject(1160, 600, 120, 40, "ButtonInfinite");
        buttonModeChl = new ClickableObject(1280, 600, 120, 40, "ButtonChallenge");

        addGameObject(ButtonTowerOne);
        addGameObject(ButtonTowerTwo);
        addGameObject(ButtonTowerThree);
        addGameObject(ButtonTowerFour);
        addGameObject(ButtonTowerFive);
        addGameObject(ButtonTowerSix);
        addGameObject(ButtonUpgradeTower);
        addGameObject(ButtonSellTower);
        addGameObject(ButtonPause);
        addGameObject(buttonModeInf);
        addGameObject(buttonModeChl);

        cObjects.add(ButtonTowerOne);
        cObjects.add(ButtonTowerTwo);
        cObjects.add(ButtonTowerThree);
        cObjects.add(ButtonTowerFour);
        cObjects.add(ButtonTowerFive);
        cObjects.add(ButtonTowerSix);
        cObjects.add(ButtonUpgradeTower);
        cObjects.add(ButtonSellTower);
        cObjects.add(ButtonPause);
        cObjects.add(buttonModeInf);
        cObjects.add(buttonModeChl);
    }

    /**
     * Checks if any of the clickable objects has been clicked. This will go through
     * the clickable object list that contains all the clickable objects and we run
     * through all of them to see if any of the objects has been clicked. If that
     * returns true, we continue with the action that has been given if the object
     * has been clicked.
     * 
     * @return The object of we clicked.
     */
    private boolean mouseClickButtons() {
        ClickableObject temp = null;
        Tower tempTower = null;
        Tower tempSell = null;

        // Checks every ClickableObject with every click
        for (ClickableObject bo : cObjects) {
            if (bo.mouseClicked(mouseX, mouseY)) {
                temp = bo;
            }

            // Check if click is on tower
            if (bo instanceof Tower && bo.mouseClicked(mouseX, mouseY)) {
                tempTower = (Tower) bo;
            }

            if (bo.getName() == "ButtonUpgradeTower" && bo.mouseClicked(mouseX, mouseY) && towerClicked == true) {
                if (towerBuild.getUpgradeNumber() != 4) {
                    towerBuild.upgradeTower();
                }
            }

            if (bo.getName() == "ButtonSellTower" && bo.mouseClicked(mouseX, mouseY) && towerClicked == true) {
                towerBuild.sellTower();
                tempSell = towerBuild;
            }

            if (bo.getName() == "ButtonPause" && bo.mouseClicked(mouseX, mouseY)) {
                String status;
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

            if (bo.getName() == "ButtonInfinite" && bo.mouseClicked(mouseX, mouseY) && gameHasStarted == false) {
                this.enemySpawner = new EnemySpawner(this, 1000); // Number of waves
                gameHasStarted = true;
            }

            if (bo.getName() == "ButtonChallenge" && bo.mouseClicked(mouseX, mouseY) && gameHasStarted == false) {
                this.enemySpawner = new EnemySpawner(this, 20); // Number of waves
                gameHasStarted = true;
            }
        }

        if (gameHasStarted == true) {
            deleteGameObject(buttonModeChl);
            cObjects.remove(buttonModeChl);
            deleteGameObject(buttonModeInf);
            cObjects.remove(buttonModeInf);
        }

        if (tempSell != null) {
            cObjects.remove(tempSell);
            tempSell = null;
        }

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

        if (tempTower != null) {
            towerClicked = true;
            towerBuild = tempTower;
        } else {
            towerClicked = false;
        }

        if (temp != null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if the game has started yet, if the game hasn't started it will return
     * false, otherwise true.
     * 
     * @return The state of the boolean gameHasStarted.
     */
    public boolean getGameHasStarted() {
        return gameHasStarted;
    }

    /**
     * Checks if enemySpawner has a valid instance, if it does it will return the
     * enemySpawner, otherwise it returns null.
     * 
     * @return The valid instance of enemySpawner.
     */
    public EnemySpawner getEnemySpawner() {
        if (enemySpawner != null) {
            return enemySpawner;
        } else {
            return null;
        }
    }

    /**
     * Checks if the game is currently paused, if the game is paused it will return
     * true, otherwise false.
     * 
     * @return The state of the boolean gameIsPaused.
     */
    public boolean getGameIsPaused() {
        return gameIsPaused;
    }

    /**
     * Checks if the boolean towerClicked is true, if it's true it means we clicked
     * a tower and we set the enemy detection box drawing to visible.
     *
     * @return The tower that we clicked on.
     */
    public Tower getTowerClicked() {
        if (towerClicked) {
            towerBuild.getEnemyDetection().setVisible(true);
            return towerBuild;
        } else {
            return null;
        }
    }

    /**
     * Gets the size of the TILESIZE.
     *
     * @return The size of the TILESIZE.
     */
    public int getTileSize() {
        return TILESIZE;
    }

    /**
     * Creates a new tower on the given X and Y position with the correct tower
     * number.
     *
     * @param towerNumber int The number of the tower.
     */
    private void createTower(int towerNumber) {
        TileMap TM = getTileMap();
        float[] location = TM.getTilePixelLocation(previousTile).array();

        // World, X & Y position, size (TILESIZE), Towernumber, Upgradenumber
        Tower newTower = new Tower(this, location[0], location[1], TILESIZE, towerNumber);
        addGameObject(newTower);
        cObjects.add(newTower);
    }

    /**
     * Checks if the any mouse click is within one of the tiles. If that returns
     * true, we select that tile by changing the sprite.
     */
    @Override
    public void mouseClicked() {
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
        if (TM.findTileTypeIndex(currentTile) == 0 && gameHasStarted == true) {

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

    /**
     * Generates the complete tilemap of the game with all the given tiletype and
     * tilesprites.
     */
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
        // int TILESIZE = 40;
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

        tileMap = new TileMap(TILESIZE, tileTypes, tilesMap);
    }

    /**
     * Pauses the game when the castle is no longer alive (reached 0 HP).
     */
    @Override
    public void update() {
        if (!Castle.getCastleIsAlive()) {
            pauseGame();
        }
    }
}