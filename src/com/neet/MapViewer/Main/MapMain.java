package com.neet.MapViewer.Main;

import com.neet.MapViewer.View.MainController;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        initDialogBox();

        appLauncher = true;
    }

    /**
     * Function to init the root view
     */
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

    /**
     * Function to show the map
     */
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

    /**
     * The fading animation function
     */
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
        alert.setTitle("Map Viewer for Diamond Hunter Version 1.0");
        alert.setHeaderText("Map Viewer User Guideline");
        alert.setContentText("Instruction for using Diamond Hunter Map Viewer\n\n"
                + "W/A/S/D to move the cursor to place the items on the map.\n"
                + "Press ENTER to navigate to the game.\n\n"
                + "1) The default location of the axe and boat are as specified where AXE (37, 26) & BOAT (12, 4).\n"
                + "2) If You choose not to place the boat and axe, the default location will be used for setting the axe and the boat.\n\n"
                + "NOTICE: When you press `1` or `2` (not yet release), "
                + "you will find the cursor color automatically change to red/green "
                + "so that you know whether the position is available to you."
                + "During your press, you can move the cursor to find a position you would like to set the item up. "
                + "Once you decided, release the key.\n");

        alert.showAndWait();
        alert.setOnCloseRequest(event -> {alert.close();});
    }
}
