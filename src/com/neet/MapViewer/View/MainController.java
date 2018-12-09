package com.neet.MapViewer.View;


import com.neet.MapViewer.Main.Cursor;
import com.neet.MapViewer.Main.MapMain;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController {

    @FXML
    private void handleKeyEvent(KeyEvent event)
    {
        if(event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP)
        {
            Cursor.cursorMovement(1);
        }
        else if(event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN)
        {
            Cursor.cursorMovement(2);
        }
        else if(event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT)
        {
            Cursor.cursorMovement(3);
        }
        else if(event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT)
        {
            Cursor.cursorMovement(4);
        }
//        else if(event.getCode() == KeyCode.K)
//        {
//            Cursor.turnOnCursorColour();
//        }
    }

    @FXML
    private void handleSetEvent(KeyEvent event)
    {
        if(event.getCode() == KeyCode.K)
        {
            MapMain.mapViewer.setItem();
        }
    }
}
