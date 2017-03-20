package com.neet.MapViewer.View;

import com.neet.DiamondHunter.Main.Game;
import com.neet.MapViewer.Main.MapMain;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController {

	@FXML
	private Label cursorPosition;

	@FXML
	private Label information;

	@FXML
	private Label axePosition;

	@FXML
	private Label boatPosition;


	@FXML
	private void handleKeyAction(KeyEvent event) {
		if (MapMain.tileMapViewer.cursorColor == false) {
			information.setText("U: Axe, I: Boat");
		}

		if(event.getCode() == KeyCode.K) {
			MapMain.tileMapViewer.zoomInImage();
	    }
	    else if (event.getCode() == KeyCode.J) {
	    	MapMain.tileMapViewer.zoomOutImage();
	    }
	    else if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
	    	MapMain.tileMapViewer.cursorUp();
	    	updateCursorPosition();
	    }
	    else if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {
	    	MapMain.tileMapViewer.cursorDown();
	    	updateCursorPosition();
	    }
	    else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {
	    	MapMain.tileMapViewer.cursorLeft();
	    	updateCursorPosition();
	    }
	    else if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
	    	MapMain.tileMapViewer.cursorRight();
	    	updateCursorPosition();
	    }
	    else if (event.getCode() == KeyCode.U) {
	    	MapMain.tileMapViewer.turningOnCurorColor();
	    	information.setText("Putting Axe...");
	    }
	    else if (event.getCode() == KeyCode.I) {
	    	MapMain.tileMapViewer.turningOnCurorColor();
	    	information.setText("Putting Boat...");
	    }
	    else if (event.getCode() == KeyCode.ENTER) {
			MapMain.primaryStage.hide();
			Game.main(null);
	    }
	}

	@FXML
	private void handleSetPosition(KeyEvent event) {
		int temp;
		if (event.getCode() == KeyCode.U) {
			temp = MapMain.tileMapViewer.handleSetAxeRequest();
			if (temp == 1) {
				information.setText("Position invalid!");
			}
			else if (temp == 2) {
				information.setText("Axe pos updated!");
				axePosition.setText(
    					"Axe: (" + MapMain.tileMapViewer.cursor.cursorRows + ", " + MapMain.tileMapViewer.cursor.cursorCols + ")");
			}
			else if (temp == 0) {
				information.setText("Axe done!");
    			axePosition.setText(
    					"Axe: (" + MapMain.tileMapViewer.cursor.cursorRows + ", " + MapMain.tileMapViewer.cursor.cursorCols + ")");
			}
		}

		else if (event.getCode() == KeyCode.I) {
			temp = MapMain.tileMapViewer.handleSetBoatRequest();
			if (temp == 1) {
				information.setText("Position invalid!");
			}
			else if (temp == 2) {
				information.setText("Boat pos updated!");
				boatPosition.setText(
    					"Boat: (" + MapMain.tileMapViewer.cursor.cursorRows + ", " + MapMain.tileMapViewer.cursor.cursorCols + ")");
			}
			else if (temp == 0) {
				information.setText("Boat done!");
    			boatPosition.setText(
    					"Boat: (" + MapMain.tileMapViewer.cursor.cursorRows + ", " + MapMain.tileMapViewer.cursor.cursorCols + ")");
			}
		}
	}

	@FXML
	private void exit() {
		Platform.setImplicitExit(true);
		MapMain.primaryStage.hide();
	}

	@FXML private void zoomInFromMenu() {
		MapMain.tileMapViewer.zoomInImage();
	}
	@FXML private void zoomOutFromMenu() {
		MapMain.tileMapViewer.zoomOutImage();
	}
	@FXML private void helpInfo() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Instruction");
		alert.setHeaderText("Instruction of map viewer");
		alert.setContentText("W/A/S/D or UP/LEFT/DOWN/RIGHT to move.\n"
        		+ "K/J to zoom in/out the map.\n"
        		+ "Press U/I to detect if AXE/BOAT position valid, release it to confirm. "
        		+ "(Green is valid, Red is invalid)\n\n"
        		+ "Press Enter to the main game.\n\n"
        		+ "NOTICE: When you press `U` or `I` (not yet release), "
        		+ "you will find the cursor color automatically change to red/green "
        		+ "so that you know whether the position is available to you."
        		+ "During your press, you can move the cursor to find a position you would like to set the item up. "
        		+ "Once you decided, release the key.\n"
        		+ "NOTICE: You can move the cursor even when the map is zoomed in. "
        		+ "The map will update automatically following your cursor move. "
        		+ "You can update the axe and boat position many times as you like.\n");
		alert.showAndWait();
		alert.setOnCloseRequest(event -> {alert.close();});
	}
	@FXML private void aboutInfo() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("About the software");
		alert.setContentText("University of Nottingham - Zequn Yu\n\nVersion 1.0\n");
		alert.showAndWait();
		alert.setOnCloseRequest(event -> {alert.close();});
	}
	
	private void updateCursorPosition() {
		cursorPosition.setText("(" + MapMain.tileMapViewer.cursor.cursorRows + ", " + MapMain.tileMapViewer.cursor.cursorCols + ")");
	}
}
