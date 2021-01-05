package views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import task.BarTask;

public class MainController {
	@FXML
	private Button btnStart;
	
	@FXML
	private Button btnPause;
	
	@FXML
	private Button btnStop;
	
	@FXML
	private ProgressBar bar;
	
	@FXML
	private Label lblStart;
	
	private BarTask task;
	
	private boolean status = false;
	
	@FXML
	public void initialize() {
		btnStop.setDisable(true);
		btnPause.setDisable(true);
	}
	
	public void start() {
		task = new BarTask(bar, lblStart, btnStop);
		btnStart.setDisable(true);
		btnStop.setDisable(false);
		btnPause.setDisable(false);
		task.startCount();
	}
	
	public void pause() {
		status = true;
		task.pushCount();
	}
	
	public void stop() {
		btnStart.setDisable(false);
		btnStop.setDisable(true);
		btnPause.setDisable(true);
		task.stopCount();
	}
}
