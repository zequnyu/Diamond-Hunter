package com.neet.MapViewer.Main;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class is the main class of Map Viewer. It loads the root layout and the basis of map layout
 * by loading the fxml file. It also loads map file and image file for the preparation of drawing.
 * Set the implicit exit to false is to hide the map viewer when the user go to the main game.
 * However there is a circumstance that the user closes the map without going to the main game. Therefore
 * on close request it is set to true back.
 * 
 * @author  Jadon Ming
 * @since   2016-12-08
*/

public class MapMain extends Application {
    public static Stage primaryStage;
    public static TileMapViewer tileMapViewer;
    
    public BorderPane rootLayout;
    public TilePane tileOverview;

    public static boolean viewerLaunch = false;
    
	@Override
	public void start(Stage primaryStage) {
		MapMain.primaryStage = primaryStage;
        MapMain.primaryStage.setTitle("MapViewer");
        
        Platform.setImplicitExit(false);

        initRootLayout();
        
		showMapOverview();
		
		viewerLaunch = true;
		
		primaryStage.setOnCloseRequest(event -> { Platform.setImplicitExit(true); });
	}
	
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MapMain.class.getResource("/com/neet/MapViewer/View/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            setFadeAnimation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setFadeAnimation() {
    	FadeTransition ft = new FadeTransition(Duration.millis(5000), rootLayout);
    	ft.setFromValue(0);
    	ft.setToValue(1.0);
    	ft.setCycleCount(1);
    	ft.setAutoReverse(true);

    	ft.play();
    }
    
    public void showMapOverview() {
    	tileMapViewer = new TileMapViewer();
    	tileMapViewer.loadMapFile("/Maps/testmap.map");
    	tileMapViewer.loadImagesFiles("/Tilesets/testtileset.gif", "/Sprites/items.gif");
	    
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MapMain.class.getResource("/com/neet/MapViewer/View/MapOverview.fxml"));
		
			tileOverview = (TilePane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		tileOverview.setPrefColumns(tileMapViewer.numCols);
		tileOverview.setPrefRows(tileMapViewer.numRows);
		tileMapViewer.initialiseCanvas();
		tileOverview.getChildren().add(tileMapViewer.currentCanvas);

		rootLayout.setCenter(tileOverview);
    }

	public static void main(String[] args) {
		launch(args);
	}
}
