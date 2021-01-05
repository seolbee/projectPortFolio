package views;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Util.JDBCUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WriteController {
	
	@FXML
	private Button btn;
	
	@FXML
	private TextField tf_title;
	
	@FXML
	private TextField tf_writer;
	
	@FXML
	private TextArea ta_content;
	
	@FXML
	private Label lbl_erro;
	
	private Stage me;

	public void setDialogStage(Stage me) {
		this.me  = me;
	}
	
	public void btn_click() {
		String title = tf_title.getText();
		String writer = tf_writer.getText();
		String content = ta_content.getText();
		
		if(title.isEmpty() || writer.isEmpty() || content.isEmpty() ) {
			lbl_erro.setText("공백 없이 입력하세요");
			return;
		}
		
		reconnect(title, writer, content);
	}
	
	public void reconnect(String title, String writer, String content) {
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO DBboard(writer, title, content, date) VALUES(?, ?, ?, NOW())";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
			MainController.reload();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("db오류");
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
		tf_title.setText("");
		tf_writer.setText("");
		ta_content.setText("");
		lbl_erro.setText("");
		this.me.close();
	}
}
