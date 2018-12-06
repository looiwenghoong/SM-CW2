package com.neet.MapViewer.Main;

import javafx.scene.image.Image;

public class Cursor {

    public Image[] imageArray;

    // variable to decide which cursor to be used
    // Currently init the cursor to use the normal colour cursor
    public int currentCursor = 2;

    // Initialise the coordinate of the cursor
    public int initCursorCol;
    public int initCursorRow;

    public Cursor()
    {
        imageArray = new Image[3];
        imageArray[0] = new Image(Cursor.class.getResourceAsStream("/Sprites/cursor_green.gif"));
        imageArray[1] = new Image(Cursor.class.getResourceAsStream("/Sprites/cursor_red.gif"));
        imageArray[2] = new Image(Cursor.class.getResourceAsStream("/Sprites/cursor_normal.gif"));

        initCursorCol = 17;
        initCursorRow = 17;
    }
}
