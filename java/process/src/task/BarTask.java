package task;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class BarTask extends Thread{
	
	private ProgressBar bar;
	private Button btnStop;
	private Label status;
	
	private double value = 0.0;
	
	private boolean stop = false;
	private boolean first = true;
	private boolean quit = false;
	
	public BarTask(ProgressBar bar, Label status, Button btnStop) {
		this.bar = bar;
		this.status = status;
		this.btnStop = btnStop;
	}
	
	@Override
	public void run() {
		while(!quit) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(stop) continue;
			value += 0.001;
			Platform.runLater(()->{
				bar.setProgress(value);
				double number = Math.round(value*1000);
				status.setText(number / 10 +"%");
			});
			if(value > 1) {
				break;
			}
		}
	}
	
	public void startCount() {
		if(first) {
			this.start();
			quit = false;
		} else {
			stop = false;
		}
	}
	
	public void pushCount() {
		if(stop) {
			btnStop.setText("일시정지");
		} else {
			btnStop.setText("재시작");
		}
		
		stop = !stop;
	}
	
	public void stopCount() {
		quit = true;
		value = 0.0;
		first = false;
		Platform.runLater(()->{
			bar.setProgress(value);
			status.setText("0.0");
		});
	}
}