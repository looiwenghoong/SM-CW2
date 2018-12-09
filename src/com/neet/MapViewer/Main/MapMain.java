package com.neet.MapViewer.Main;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.border.Border;

public class MapMain extends Application {

    public static TileMapViewer mapViewer;
    public static Stage primaryStage;

    // Variable to be used in the playstate to determine if this map viewer application is launched
    public static boolean appLauncher = false;

    public BorderPane rootLayout;
    public TilePane mapLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MapMain.primaryStage = primaryStage;
        MapMain.primaryStage.setTitle("Map Viewer");
        initRootView();
        initMapLayout();
    }

//    Function to init the root view
    public void initRootView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MapMain.class.getResource("/com/neet/MapViewer/View/RootLayout.fxml"));

            rootLayout = (BorderPane)loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();

//            fadeAnimation();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//    Function to show the map
    public void initMapLayout()
    {
        mapViewer = TileMapViewer.getInstance();
        mapViewer.loadMap("/Maps/testmap.map");
        mapViewer.loadImages("/Tilesets/testtileset.gif", "/Sprites/items.gif");
        mapViewer.initMapCanvas();
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MapMain.class.getResource("/com/neet/MapViewer/View/MapLayout.fxml"));

            mapLayout = (TilePane)loader.load();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Set the number of columns and rows for the tilepane map layout
        mapLayout.setPrefColumns(mapViewer.numCols);
        mapLayout.setPrefRows(mapViewer.numRows);
        mapLayout.getChildren().add(mapViewer.currentCanvas);
        rootLayout.setCenter(mapLayout);
    }

    public void fadeAnimation()
    {
        FadeTransition ft = new FadeTransition(Duration.millis(5000), rootLayout);
        ft.setFromValue(0);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);

        ft.play();
    }
}
