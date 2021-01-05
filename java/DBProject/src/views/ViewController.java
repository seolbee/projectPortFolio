package views;

import Util.contentVO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class ViewController {
	
	@FXML
	private Label lbl_title;
	
	@FXML
	private Label lbl_writer;
	
	@FXML
	private Label lbl_date;
	
	@FXML
	private Label lbl_content;
	
	private Stage me;
	
	public void setDialog(Stage me) {
		this.me = me;
	}
	
	public void setText(contentVO item) {
		lbl_title.setText(item.getTitle());
		lbl_writer.setText(item.getWriter());
		lbl_date.setText(item.getDate().toString());
		lbl_content.setText(item.getContent());
	}
}
