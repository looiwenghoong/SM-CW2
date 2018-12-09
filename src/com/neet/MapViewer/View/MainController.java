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
        if(event.getCode() == KeyCode.W)
        {
            Cursor.cursorUp();
        }
    }
}
