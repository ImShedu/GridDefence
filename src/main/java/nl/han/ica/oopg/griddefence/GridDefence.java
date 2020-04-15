package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.griddefence.Tiles.BoardsTile;
import nl.han.ica.oopg.griddefence.Tiles.CastleTile;
import nl.han.ica.oopg.griddefence.Tiles.NoBuildTile;
import nl.han.ica.oopg.griddefence.Tiles.PathTile;
import nl.han.ica.oopg.griddefence.Tiles.SpawnTile;
import nl.han.ica.oopg.griddefence.Tower.Tower1;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import processing.core.PApplet;
import nl.han.ica.oopg.view.View;

public class GridDefence extends GameEngine {
    private Tile previousTile;
    private int worldWidth = 1600;
    private int worldHeight = 800;

    public static void main(String[] args) {
        String[] processingArgs = { "nl.han.ica.oopg.griddefence.GridDefence" };
        GridDefence mySketch = new GridDefence();

        PApplet.runSketch(processingArgs, mySketch);
    }

    @Override
    public void setupGame() {
        // TODO Auto-generated method stub
        // int worldWidth = 1600;
        // int worldHeight = 800;

        generateTileMap();
        createViewWithoutViewport(worldWidth, worldHeight);
        createDashboard();
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);

        setView(view);
        size(screenWidth, screenHeight);
    }

    private void createDashboard() {
        Tower1 towerOne = new Tower1(((worldWidth / 2) - 240), (worldHeight - 160), 80, 40);
        UserInterface towerUI = new UserInterface(((worldWidth / 2) - 200), (worldHeight - 120), 400, 120);
        towerUI.setBackground(85, 85, 85);
        // towerOne.setBackground(255, 0, 0);
        
        // addDashboard(towerOne);
        towerUI.addGameObject(towerOne);
        addDashboard(towerUI);

        // UserInterface ui = new UserInterface(200, 200, 200, 200);
        // ui.setBackground(0, 255, 0);
        // addDashboard(ui);
    }

    // Check of tile geselecteerd is en veranderd geselecteerde tile naar een
    // geselecteerde sprite
    // vernaderd daarbij ook de voorgaand geselecteerde tile terug naar een normale
    // tile
    @Override
    public void mouseClicked() {
        TileMap TM = getTileMap();
        Tile currentTile = TM.getTileOnPosition(mouseX, mouseY);

        // Eerste IF test kijkt of de geselecteerd tile gelijk is aan een normale tile
        // waar op gebouwd kan worden
        // De tweede IF test kijkt of er een voorgaand geselecteerde tile was
        // Indien er een voorgaand geselecteerde tile aanwezig is, dan verandere we de
        // huidige tile naar een geselecteerde sprite
        // en de voorgaand geselecteerde tile naar een normale sprite
        if (TM.findTileTypeIndex(currentTile) == 0) {
            if (previousTile != null) {

                Sprite selectedSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Selected1.jpg");
                currentTile.setSprite(selectedSprite);

                Sprite resetSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Board1.jpg");
                previousTile.setSprite(resetSprite);

                previousTile = currentTile;
            } else {
                previousTile = currentTile;
                Sprite selectedSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Selected1.jpg");
                previousTile.setSprite(selectedSprite);
            }
        }
    }

    private void generateTileMap() {
        // TILES Sprites
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
        int tileSize = 40;
        int tilesMap[][] = {
                { 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0 },
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
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0 } };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }
}