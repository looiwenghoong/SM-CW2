package com.neet.MapViewer.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import javax.swing.border.Border;

public class MapMain extends Application {

    public static Stage primaryStage;
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
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//    Function to init the map view
    public void initMapLayout()
    {
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

        rootLayout.setCenter(mapLayout);
//        mapLayout.setStyle("-fx-background-color: #000000");
    }
}
