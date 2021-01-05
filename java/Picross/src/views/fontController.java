package views;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class fontController extends MasterController{
	@FXML
	private AnchorPane pane;
	
	public void gameStart() {
		Main.app.slideOut(getRoot());
	}
}
