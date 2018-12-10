package com.neet.MapViewer.View;

import com.neet.MapViewer.Main.Cursor;
import com.neet.MapViewer.Main.MapMain;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController {
    @FXML
    public void handleKeyPressed (KeyEvent event) {
        //press W key to move the cursor upwards
        if(event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
            Cursor.cursorMovement(1);
        }

        //press S key to move the cursor downwards
        else if(event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
            Cursor.cursorMovement(2);
        }

        //press A key to move the cursor to the left
        else if(event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
            Cursor.cursorMovement(3);
        }

        //press D key to move the cursor to the right
        else if(event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
            Cursor.cursorMovement(4);
        }

        else if(event.getCode() == KeyCode.DIGIT1) {
            Cursor.turnOnCursorColour();
        }

        else if(event.getCode() == KeyCode.DIGIT2) {
            Cursor.turnOnCursorColour();
        }
    }

    @FXML
    public void handleSetItem (KeyEvent event) {
        //press 1 to place the axe
        if(event.getCode() == KeyCode.DIGIT1) {
            MapMain.mapViewer.setItem(1);
        }

        //press 2 to place the boat
        if(event.getCode() == KeyCode.DIGIT2) {
            MapMain.mapViewer.setItem(2);
        }
    }
}