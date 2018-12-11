package com.neet.MapViewer.View;

import com.neet.DiamondHunter.Main.Game;
import com.neet.MapViewer.Main.Cursor;
import com.neet.MapViewer.Main.MapMain;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.shape.*;
import javafx.util.Duration;


public class MainController {

    @FXML
    public Label boatId, axeId, cursorId, upButton, downButton, leftButton, rightButton, axeButton, boatButton;

    @FXML
    public ImageView axeImage, boatImage, cursorImage;

    @FXML
    public void handleKeyPressed (KeyEvent event) {

        //press W key to move the cursor upwards
        if(event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
            Cursor.cursorMovement(1);
            upButton.setScaleX(0.9);
            upButton.setScaleY(0.9);
            cursorId.setText("CURSOR: (" + Cursor.CursorRow + ", " + Cursor.CursorCol + ")");
        }

        //press S key to move the cursor downwards
        else if(event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
            Cursor.cursorMovement(2);
            downButton.setScaleX(0.9);
            downButton.setScaleY(0.9);
            cursorId.setText("CURSOR: (" + Cursor.CursorRow + ", " + Cursor.CursorCol + ")");
        }

        //press A key to move the cursor to the left
        else if(event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
            Cursor.cursorMovement(3);
            leftButton.setScaleX(0.9);
            leftButton.setScaleY(0.9);
            cursorId.setText("CURSOR: (" + Cursor.CursorRow + ", " + Cursor.CursorCol + ")");
        }

        //press D key to move the cursor to the right
        else if(event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
            Cursor.cursorMovement(4);
            rightButton.setScaleX(0.9);
            rightButton.setScaleY(0.9);
            cursorId.setText("CURSOR: (" + Cursor.CursorRow + ", " + Cursor.CursorCol + ")");
        }

        else if(event.getCode() == KeyCode.DIGIT1) {
            Cursor.turnOnCursorColour();
            axeButton.setScaleX(0.9);
            axeButton.setScaleY(0.9);
        }

        else if(event.getCode() == KeyCode.DIGIT2) {
            boatButton.setScaleX(0.9);
            boatButton.setScaleY(0.9);
            Cursor.turnOnCursorColour();
        }

        else if(event.getCode() == KeyCode.ENTER) {
            MapMain.primaryStage.hide();
            Game.main(null);
        }
    }

    @FXML
    public void handleSetItem (KeyEvent event) {
        int setStatus;

        // When the key is released, it will return to its normal size
        if(event.getCode() == KeyCode.W) {
            upButton.setScaleX(1);
            upButton.setScaleY(1);
        }

        else if(event.getCode() == KeyCode.S) {
            downButton.setScaleX(1);
            downButton.setScaleY(1);
        }

        else if(event.getCode() == KeyCode.A) {
            leftButton.setScaleX(1);
            leftButton.setScaleY(1);
        }

        else if(event.getCode() == KeyCode.D) {
            rightButton.setScaleX(1);
            rightButton.setScaleY(1);
        }

        //press 1 to place the axe
        if(event.getCode() == KeyCode.DIGIT1) {
            setStatus = MapMain.mapViewer.setItem(1);
            axeButton.setScaleX(1);
            axeButton.setScaleY(1);
            axeId.setText("AXE: (" + MapMain.mapViewer.getAxeRow() + ", " + MapMain.mapViewer.getAxeCol() + ")");
        }

        //press 2 to place the boat
        if(event.getCode() == KeyCode.DIGIT2) {
            MapMain.mapViewer.setItem(2);
            boatButton.setScaleX(1);
            boatButton.setScaleY(1);
            boatId.setText("BOAT: (" + MapMain.mapViewer.getBoatRow() + ", " + MapMain.mapViewer.getBoatCol() + ")");
        }
    }

    public void tryThis()
    {
        Path cursorPath = new Path();
        cursorPath.getElements().add(new MoveTo(45,7));
        cursorPath.getElements().add(new VLineTo(12));
        cursorPath.getElements().add(new ClosePath());

        PathTransition cursorPathTransition = new PathTransition();
        cursorPathTransition.setDuration(Duration.millis(1500));
        cursorPathTransition.setNode(cursorImage);
        cursorPathTransition.setPath(cursorPath);
        cursorPathTransition.setCycleCount(PathTransition.INDEFINITE);
        cursorPathTransition.play();

        Path sBoatPath = new Path();
        sBoatPath.getElements().add(new MoveTo(15, 15));
        sBoatPath.getElements().add(new VLineTo(10));
        sBoatPath.getElements().add(new ClosePath());

        PathTransition sBoatPathTransition = new PathTransition();
        sBoatPathTransition.setDuration(Duration.millis(1000));
        sBoatPathTransition.setNode(boatImage);
        sBoatPathTransition.setPath(sBoatPath);
        sBoatPathTransition.setCycleCount(PathTransition.INDEFINITE);
        sBoatPathTransition.play();


        Path sAxePath = new Path();
        sAxePath.getElements().add(new MoveTo(0, 15));
        sAxePath.getElements().add(new VLineTo(10));
        sAxePath.getElements().add(new ClosePath());

        PathTransition sAxePathTransition = new PathTransition();
        sAxePathTransition.setDuration(Duration.millis(900));
        sAxePathTransition.setNode(axeImage);
        sAxePathTransition.setPath(sBoatPath);
        sAxePathTransition.setCycleCount(PathTransition.INDEFINITE);
        sAxePathTransition.play();

    }
}