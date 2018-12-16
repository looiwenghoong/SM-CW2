package com.neet.MapViewer.Main;

import com.neet.MapViewer.View.MainController;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class MapMain extends Application {

    public static TileMapViewer mapViewer;
    public static Stage primaryStage;

    // Variable to be used in the playstate to determine if this map viewer application is launched
    public static boolean appLauncher = false;

    public BorderPane rootLayout;
    public TilePane mapLayout;

    static MainController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MapMain.primaryStage = primaryStage;
        MapMain.primaryStage.setTitle("Map Viewer");

        Platform.setImplicitExit(false);
        initRootView();
        initMapLayout();

        appLauncher = true;
    }

    // Function to init the root view
    public void initRootView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MapMain.class.getResource("/com/neet/MapViewer/View/RootLayout.fxml"));

            // Load the rootlayout by casting it to BorderPane
            rootLayout = (BorderPane) loader.load();

            // Get the controller associate with the rootlayout
            controller = (MainController) loader.getController();
            controller.setAnimation();

            Scene scene = new Scene(rootLayout);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();

            fadeAnimation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to show the map
    public void initMapLayout() {
        mapViewer = TileMapViewer.getInstance();
        mapViewer.loadMap("/Maps/testmap.map");
        mapViewer.loadImages("/Tilesets/testtileset.gif", "/Sprites/items.gif");
        mapViewer.initMapCanvas();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MapMain.class.getResource("/com/neet/MapViewer/View/MapLayout.fxml"));

            mapLayout = (TilePane) loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the number of columns and rows for the tilepane map layout
        mapLayout.setPrefColumns(mapViewer.numCols);
        mapLayout.setPrefRows(mapViewer.numRows);
        mapLayout.getChildren().add(mapViewer.currentCanvas);
        rootLayout.setCenter(mapLayout);
    }

    // The fading animation function
    public void fadeAnimation() {
        FadeTransition ft = new FadeTransition(Duration.millis(5000), rootLayout);
        ft.setFromValue(0);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);

        ft.play();
    }

    private void initDialogBox()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");

        alert.showAndWait();
    }
}
