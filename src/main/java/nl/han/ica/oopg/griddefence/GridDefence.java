package nl.han.ica.oopg.griddefence;

import nl.han.ica.oopg.griddefence.Tiles.BoardsTile;
import nl.han.ica.oopg.griddefence.Tiles.PathTile;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import processing.core.PApplet;
import nl.han.ica.oopg.view.View;

public class GridDefence extends GameEngine {

    public static void main(String[] args) {
        String[] processingArgs = {"nl.han.ica.oopg.griddefence.GridDefence"};
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
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        //view.setBackground(loadImage("src/main/java/nl/han/ica/oopg/griddefence/Resource/Paper1.png"));

        setView(view);
        size(screenWidth, screenHeight);
    }

    private void generateTileMap() {
        /* TILES */
        Sprite boardSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Reptile1.jpg");
        Sprite pathSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Path1.jpg");
        Sprite towerSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Tower1.jpg");
        Sprite enemySprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Enemy1.jpg");
        Sprite castleSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Castle1.jpg");
        Sprite spawnSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Spawn1.jpg");

        TileType<BoardsTile> boardTileType = new TileType<>(BoardsTile.class, boardSprite);
        TileType<PathTile> pathTileType = new TileType<>(PathTile.class, pathSprite);

        TileType[] tileTypes = {boardTileType, pathTileType};
        int tileSize = 50;
        int tilesMap[][] = {
            {-1 , 0 , 0},
            {0 , 0 , 0},
            {0 , -1 , 0},
            {0 , 0 , 0},
            {0 , 0 , -1},
            {0 , 1 , 0},
            {0 , 0 , 1},
            {0 , 1 , 0},
            {0 , 0 , 1}
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }



    @Override
    public void update() {
        // TODO Auto-generated method stub

    }
}