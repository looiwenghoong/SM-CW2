/*
* This is the cursor class that is used to create cursor object
*/
package com.neet.MapViewer.Main;

import javafx.scene.image.Image;

public class Cursor {

    private static TileMapViewer mapViewer;

    private static Cursor cursor = null;

    public static boolean cursorColour = false;
    // Init the image array
    public Image[] imageArray;

    // variable to decide which cursor to be used
    // Currently init the cursor to use the normal colour cursor
    public int currentCursor = 2;

    // Initialise the coordinate of the cursor
    public static int CursorCol;
    public static int CursorRow;

    private Cursor()
    {
        mapViewer = TileMapViewer.getInstance();

        // Create an image array of 3 element to contain all the available cursor
        imageArray = new Image[3];

        // Import the cursor.gifs into the image array
        imageArray[0] = new Image(Cursor.class.getResourceAsStream("/Sprites/cursor_green.gif"));
        imageArray[1] = new Image(Cursor.class.getResourceAsStream("/Sprites/cursor_red.gif"));
        imageArray[2] = new Image(Cursor.class.getResourceAsStream("/Sprites/cursor_normal.gif"));

        // Initialise the coordinate of the cursor to (17,17)
        CursorCol = 17;
        CursorRow = 17;
    }

    public static Cursor getInstance()
    {
        if(cursor == null) {
            cursor = new Cursor();
        }
        return cursor;
    }

    private static void cursorMovementOpt()
    {
        mapViewer.drawitem();
        mapViewer.drawCursorToMain();
        mapViewer.newImage = mapViewer.mainCanvas.snapshot(null, null);
        mapViewer.updateCurrentCanvas();
    }

    public static void cursorMovement(int direction)
    {
        switch (direction)
        {
            // Cursor Move Up
            case 1:
                // If the current cursor row is more than 0
                // Meaning can move to up
                if(cursor.CursorRow > 0)
                {
                    mapViewer.replaceToOriginal(cursor.CursorCol, cursor.CursorRow);
                    cursor.CursorRow--;
                    cursorMovementOpt();
                }
                break;

            // Cursor Move down
            case 2:
                // If the current cursor row is lesser than the total row of the map - 1
                // Then move to the down
                // When move down until reaches the maximum number of map row then cannot move down anymore
                if(cursor.CursorRow < mapViewer.numRows - 1)
                {
                    mapViewer.replaceToOriginal(cursor.CursorCol, cursor.CursorRow);
                    cursor.CursorRow++;
                    cursorMovementOpt();
                }
                break;

            // Cursor Move left
            case 3:
                if(cursor.CursorCol > 0)
                {
                    mapViewer.replaceToOriginal(cursor.CursorCol, cursor.CursorRow);
                    cursor.CursorCol--;
                    cursorMovementOpt();
                }
                break;
            // Cursor Move right
            case 4:
                if(cursor.CursorCol < mapViewer.numCols - 1)
                {
                    mapViewer.replaceToOriginal(cursor.CursorCol, cursor.CursorRow);
                    cursor.CursorCol++;
                    cursorMovementOpt();
                }
        }
    }

    public static void changeCursorColour()
    {
        if(cursorColour)
        {
            cursor.currentCursor = mapViewer.tileType[cursor.CursorRow][cursor.CursorCol];
        }
        else
        {
            cursor.currentCursor = 2;
        }
    }

    public static void turnOnCursorColour()
    {
        cursorColour = true;
        mapViewer.replaceToOriginal(cursor.CursorCol, cursor.CursorRow);
        cursor.changeCursorColour();
        mapViewer.drawCursorToMain();
        mapViewer.newImage = mapViewer.mainCanvas.snapshot(null, null);
        mapViewer.updateCurrentCanvas();
    }
}
