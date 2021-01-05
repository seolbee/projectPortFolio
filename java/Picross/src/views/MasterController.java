package views;

import javafx.scene.layout.Pane;

public class MasterController {
	private Pane pane;
	
	public void setRoot(Pane pane) {
		this.pane = pane;
	}
	
	public Pane getRoot() {
		return this.pane;
	}
}
