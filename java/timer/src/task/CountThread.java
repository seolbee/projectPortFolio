package task;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class CountThread extends Thread{
	
	private Label lblH;
	
	private Label lblM;
	
	private Label lblS;
 	
	private long sec;
	
	private boolean status = true; //상태코드
	private boolean first = true; //최초 실행 코드
	private boolean quit = false; //종료코드
	
	public CountThread(Label h, Label m, Label s) {
		lblH = h;
		lblM =m;
		lblS = s;
		sec = 0;
	}
	
	public void setQuit() {
		quit = true;
	}
	
	@Override
	public void run() {
		while(!quit) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(!status) continue;
			
			sec++;
			
			Long h = sec / 3600;
			Long m = sec % 3600/60;
			Long s = sec % 60;
			
			Platform.runLater(()->{
//				if(h<10) {
//					lblH.setText("0" + h.toString());
//				} else {
//					lblH.setText(h.toString());
//				}
//				
//				if(m < 10) {
//					lblM.setText("0" + m.toString());
//				} else {
//					lblM.setText(m.toString());
//				}
//				
//				if(s < 10) {
//					lblS.setText("0" + s.toString());
//				} else {
//					lblS.setText(s.toString());
//				}
				
				lblH.setText(setZero(h.toString()));
				lblM.setText(setZero(m.toString()));
				lblS.setText(setZero(s.toString()));
			});
		}
	}
	
	public String setZero(String time) {
		String num = "00" + time;
		String number = num.substring(num.length()-2, num.length());
		return number;
	}
	
	public void stopCount() {
		status = false;
		sec = 0;
		Platform.runLater(()->{
			lblH.setText("00");
			lblM.setText("00");
			lblS.setText("00");
		});
	}
	
	public void pauseCount() {
		status = false;
	}
	
	public void startCount() {
		if(first) {
			this.start();
			first = false;
		} else {
			status = true;
		}
	}
}
