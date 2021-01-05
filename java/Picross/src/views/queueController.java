package views;

import application.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class queueController extends MasterController{
	
	public void click(ActionEvent e) {
		String level = ((Button)e.getSource()).getText();
		Main.app.setLevel(level);
		Main.app.slideOut(getRoot());
	}
}
