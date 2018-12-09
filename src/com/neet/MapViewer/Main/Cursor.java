/*
* This is the cursor class that is used to create cursor object
*/
package com.neet.MapViewer.Main;

import javafx.scene.image.Image;

public class Cursor {

    private static TileMapViewer mapViewer;

    private static Cursor cursor = null;

    // Init the image array
    public Image[] imageArray;

    // variable to decide which cursor to be used
    // Currently init the cursor to use the normal colour cursor
    public int currentCursor = 2;

    // Initialise the coordinate of the cursor
    public int CursorCol;
    public int CursorRow;

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

    public static void cursorUp()
    {
        if(cursor.CursorRow > 0)
        {
            mapViewer.replaceToOriginal(cursor.CursorCol, cursor.CursorRow);
            cursor.CursorRow--;
            System.out.println(cursor.CursorRow);
//            mapViewer.changeCursorColour();

            mapViewer.drawCursorToMain();

            mapViewer.newImage = mapViewer.mainCanvas.snapshot(null, null);
            mapViewer.updateCurrentCanvas();
        }
    }
}
