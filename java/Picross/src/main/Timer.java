package main;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Timer extends Thread{
	
	public Integer hour;
	
	public Integer minute;
	
	public Integer second;
	
	public int sec;
	
	public Label lblHour;
	
	public Label lblminute;
	
	public Label lblsecond;

	private boolean start = false;
	
	private boolean stop = false;
	
	private boolean quit = false;
	
	public Timer(Label hour, Label minute, Label second) {
		this.lblHour = hour;
		this.lblminute = minute;
		this.lblsecond = second;
	}
	
	@Override
	public void run() {
		while(start) {
			try {
				Thread.sleep(1000);
				if(quit) return;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("쓰레드 오류");
			}
			
			sec++;
			
			hour = sec / 3600;
			minute = sec % 3600/60;
			second = sec % 3600;
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					lblHour.setText(value(hour.toString()));
					lblminute.setText(value(minute.toString()));
					lblsecond.setText(value(second.toString()));
				}
			});
		}
	}
	
	public String value(String value) {
		String val = "00" + value;
		return val.substring(val.length()-2, val.length());
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}
	
	public void reset() {
		sec = 0;
		hour = 0;
		minute = 0;
		second = 0;
		lblHour.setText(value("0"));
		lblsecond.setText(value("0"));
		lblminute.setText(value("0"));
	}
}
