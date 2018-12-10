package com.neet.MapViewer.Main;
import com.neet.DiamondHunter.TileMap.TileMap;

import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javafx.scene.canvas.Canvas;

public class TileMapViewer {

    private static TileMapViewer mapViewer = null;

    public static Cursor cursor;

    public int numCols;
    public int numRows;

    private int[][] mapMatrix;
    public int[][] tileType;

    private Image tileset;
    private int numTilesAcross;
    private int tileSize = 16;

    public static Image items;
    private boolean axeIsPlaced = false;
    private boolean boatIsPlaced = false;

    private Image originalImage;
    public Image newImage;

    public Canvas mainCanvas;
    public Canvas currentCanvas;

    /*
    * Variables for setting the items, boat and axe
    */
    private int boatRow = -1;
    private int boatCol = -1;
    private int axeRow = -1;
    private int axeCol = -1;

    public int getBoatRow() {
        return boatRow;
    }
    public int getBoatCol() {
        return boatCol;
    }
    public int getAxeRow() {
        return axeRow;
    }
    public int getAxeCol() {
        return axeCol;
    }

    public TileMapViewer(){}

    public static TileMapViewer getInstance()
    {
        if(mapViewer == null)
        {
            mapViewer = new TileMapViewer();
        }
        return mapViewer;
    }
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
    public void loadImages(String tilesImage, String itemsImage)
    {
        try
        {
            tileset = new Image(TileMapViewer.class.getResourceAsStream(tilesImage));
            items = new Image(TileMapViewer.class.getResourceAsStream(itemsImage));
            numTilesAcross = (int)tileset.getWidth() / tileSize;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // Function that initialise the canvas of the map
    void initMapCanvas()
    {
        mainCanvas = new Canvas(640, 640);
        currentCanvas = new Canvas(640, 640);
        tileType = new int[numRows][numCols]; // store value that determines whether the tile is with obstacle or without obstacle
        cursor = Cursor.getInstance();

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

                // if else statement to determine whether to load the tileset image from row 1 or row 2
                // tileset row 1 contains no obstacle
                // tileset row 2 contains obstacle
                if(R == 0)
                {
                    mainCanvas.getGraphicsContext2D().drawImage(
                            tileset, C * tileSize, 0, tileSize, tileSize,
                            col * tileSize, row * tileSize, tileSize, tileSize);
                    currentCanvas.getGraphicsContext2D().drawImage(
                            tileset, C * tileSize, 0, tileSize, tileSize,
                            col * tileSize, row * tileSize, tileSize, tileSize);
                    tileType[row][col] = 0; // tileType == 0 means the tile has no obstacle
                }
                else
                {
                    mainCanvas.getGraphicsContext2D().drawImage(
                            tileset, C * tileSize, tileSize, tileSize, tileSize,
                            col * tileSize, row * tileSize, tileSize, tileSize);
                    currentCanvas.getGraphicsContext2D().drawImage(
                            tileset, C * tileSize, tileSize, tileSize, tileSize,
                            col * tileSize, row * tileSize, tileSize, tileSize);
                    tileType[row][col] = 1; // tiletype == 1 means the tile has obstacle
                }
            }
        }

        originalImage = mainCanvas.snapshot(null, null);
        drawCursorToMain();
        currentCanvas.getGraphicsContext2D().drawImage(
                cursor.imageArray[cursor.currentCursor], 0, 0, tileSize, tileSize,
                cursor.CursorCol * tileSize, cursor.CursorRow * tileSize,
                tileSize, tileSize);
        newImage = mainCanvas.snapshot(null, null);
    }

    // Function to replace the tile back to the original tile after the cursor is moved
    void replaceToOriginal(int col, int row)
    {
        mainCanvas.getGraphicsContext2D().drawImage(
                originalImage,
                col * tileSize,
                row * tileSize,
                tileSize, tileSize,
                col * tileSize,
                row * tileSize,
                tileSize, tileSize);
    }

    public void updateCurrentCanvas()
    {
        currentCanvas.getGraphicsContext2D().drawImage(
                newImage, 0, 0,
                numCols * tileSize, numRows * tileSize,
                0, 0, 640, 640);
    }


    // Function to draw the cursor
    public void drawCursorToMain()
    {
        mainCanvas.getGraphicsContext2D().drawImage(
                cursor.imageArray[cursor.currentCursor], 0, 0, tileSize, tileSize,
                cursor.CursorCol * tileSize,
                cursor.CursorRow * tileSize, tileSize, tileSize);
    }

    public void drawitem()
    {
        if(axeIsPlaced)
        {
            int axe = 1;
            mainCanvas.getGraphicsContext2D().drawImage(
                    items,
                    axe * tileSize, tileSize, tileSize, tileSize,
                    axeCol * tileSize,
                    axeRow * tileSize,
                    tileSize, tileSize);
        }
        if (boatIsPlaced)
        {
            int boat = 0;
            mainCanvas.getGraphicsContext2D().drawImage(
                    items,
                    boat * tileSize, tileSize, tileSize, tileSize,
                    boatCol * tileSize,
                    boatRow * tileSize,
                    tileSize, tileSize);
        }
    }

    private int checkItemPlaced(boolean itemCondition, int itemRow, int itemCol)
    {
        if(itemCondition)
        {
            replaceToOriginal(itemCol, itemRow);
            tileType[itemRow][itemCol] = 0;
            return 2;
        }
        else
        {
            return 0;
        }
    }

    public int setItem(int itemType)
    {
        int handleType = 0;
        Cursor.cursorColour = false;
        Cursor.changeCursorColour();

        replaceToOriginal(cursor.CursorCol, cursor.CursorRow);

        if (tileType[cursor.CursorRow][cursor.CursorCol] == 1) {
            handleType = 1;
        }
        // return type: Axe put successful
        else {
            switch (itemType)
            {
                case 1:
                    handleType = checkItemPlaced(axeIsPlaced, axeRow, axeCol);
                    axeIsPlaced = true;
                    tileType[cursor.CursorRow][cursor.CursorCol] = 1;

                    axeRow = cursor.CursorRow;
                    axeCol = cursor.CursorCol;
                    break;
                case 2:
                    handleType = checkItemPlaced(boatIsPlaced, boatRow, boatCol);
                    boatIsPlaced = true;
                    tileType[cursor.CursorRow][cursor.CursorCol] = 1;

                    boatRow = cursor.CursorRow;
                    boatCol = cursor.CursorCol;
                    break;
            }
        }

        drawitem();
        drawCursorToMain();

        newImage = mainCanvas.snapshot(null, null);
        updateCurrentCanvas();

        return handleType;
    }
}
