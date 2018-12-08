package com.neet.MapViewer.Main;
import com.neet.DiamondHunter.TileMap.TileMap;

import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javafx.scene.canvas.Canvas;

public class TileMapViewer {

    public Cursor cursor;
    public boolean cursorColour = false;

    public int numCols;
    public int numRows;

    private int[][] mapMatrix;
    private int[][] tileType;

    private Image tileset;
    private int numTilesAcross;
    private int tileSize = 16;
    private Image items;

    private Image originalImage;
    private Image newImage;

    public Canvas mainCanvas;
    public Canvas currentCanvas;

    /*
    * Function to load the map file with matrices
    * Creates a matrix to store all the values in the map file
    */
    public void loadMap(String mapFile)
    {
        try
        {
            InputStream in = getClass().getResourceAsStream(mapFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());

            mapMatrix = new int[numRows][numCols];

            String delims = "\\s+";

            // call the function in TileMap.java to read the string and load the data from testmap into a matrix
            TileMap.readTheString(numRows, numCols, delims, br, mapMatrix);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /*
    * Function to load the images of the map and items
    * The images of the map is created and stored in the variable for drawing
    */
    public void loadImages(String tilesImage)
    {
        try
        {
            tileset = new Image(TileMapViewer.class.getResourceAsStream(tilesImage));
            numTilesAcross = (int)tileset.getWidth() / tileSize;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void initMapCanvas()
    {
        mainCanvas = new Canvas(640, 640);
        tileType = new int[numRows][numCols]; // store value that determines whether the tile is with obstacle or without obstacle
        cursor = new Cursor(); //Create new cursor object

        for(int row = 0; row < numRows; row++)
        {
            for(int col = 0; col < numCols; col++)
            {
                if(mapMatrix[row][col] == 0)
                {
                    continue;
                }

                // This variable is used to retrieve the value according to the test map
                int rowCol = mapMatrix[row][col];

                // The R and C variables are used to determine the coordinate of the tileset that will be used to
                // render the tile image according to the testmap.
                int R = rowCol / numTilesAcross;
                int C = rowCol % numTilesAcross;

                if(R == 0)
                {
                    mainCanvas.getGraphicsContext2D().drawImage(
                            tileset, C * tileSize, 0, tileSize, tileSize,
                            col * tileSize, row * tileSize, tileSize, tileSize);
                    tileType[row][col] = 0;
                }
                else
                {
                    mainCanvas.getGraphicsContext2D().drawImage(
                            tileset, C * tileSize, tileSize, tileSize, tileSize,
                            col * tileSize, row * tileSize, tileSize, tileSize);
                    tileType[row][col] = 1;
                }

                System.out.print(C + " ");
            }
            System.out.println(" ");
        }

        originalImage = mainCanvas.snapshot(null, null);
        drawCursor();
        newImage = mainCanvas.snapshot(null, null);
    }

    // Function to draw the cursor
    public void drawCursor()
    {
        mainCanvas.getGraphicsContext2D().drawImage(
                cursor.imageArray[cursor.currentCursor], 0, 0, tileSize, tileSize,
                cursor.CursorCol * tileSize,
                cursor.CursorRow * tileSize, tileSize, tileSize);
    }

    private void changeCursorColour()
    {
        if(cursorColour == true)
        {
            cursor.currentCursor = tileType[cursor.CursorRow][cursor.CursorCol];
        }
        else
        {
            cursor.currentCursor = 2;
        }
    }
}
