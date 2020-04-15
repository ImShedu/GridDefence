package nl.han.ica.oopg.griddefence;


import nl.han.ica.oopg.GridDefence.Tiles.BoardsTile;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import processing.core.PApplet;
import nl.han.ica.oopg.view.View;

public class GridDefence extends GameEngine {

    public static void main(String[] args) {
        String[] processingArgs = {"src.main.java.nl.han.ica.oopg.griddefence.GridDefence"};
        GridDefence mySketch = new GridDefence();

        PApplet.runSketch(processingArgs, mySketch);
    }

    @Override
    public void setupGame() {
        // TODO Auto-generated method stub
        int worldWidth = 1280;
        int worldHeight = 800;

        generateTileMap();
        createViewWithoutViewport(worldWidth, worldHeight);
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        view.setBackground(loadImage("src/main/java/nl/han/ica/oopg/griddefence/Resource/Low_Poly_Wallpaper_1.png"));

        setView(view);
        size(screenWidth, screenHeight);
    }

    private void generateTileMap() {
        /* TILES */
        Sprite testSprite = new Sprite("src/main/java/nl/han/ica/oopg/griddefence/Resource/Reptile_1.jpg");
        TileType<BoardsTile> boardTileType = new TileType<>(BoardsTile.class, testSprite);

        TileType[] tileTypes = {boardTileType};
        int tileSize = 100;
        int tilesMap[][] = {
            {0 , 0 , 0},
            {0 , 0 , 0},
            {0 , 0 , 0}
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }



    @Override
    public void update() {
        // TODO Auto-generated method stub

    }
}