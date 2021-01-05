package views;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Util.JDBCUtil;
import Util.contentVO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateController {
	
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
	
	private int id;

	public void setDialog(Stage me) {
		this.me  = me;
	}
	
	public void setText(contentVO item) {
		tf_title.setText(item.getTitle());
		tf_writer.setText(item.getWriter());
		ta_content.setText(item.getContent());
		this.id = item.getId();
	}
	
	public void btn_click() {
		String title = tf_title.getText();
		String writer = tf_writer.getText();
		String content = ta_content.getText();
		
		if(title.isEmpty() || writer.isEmpty() || content.isEmpty() ) {
			lbl_erro.setText("공백 없이 입력하세요");
			return;
		}
		reconnect(title, writer, content, this.id);
	}
	
	public void reconnect(String title, String writer, String content, int id) {
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE `DBboard` SET `writer`=? , title=?,`content`=?,`date`=NOW() WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setInt(4, id);
			pstmt.executeUpdate();
			MainController.reload();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("db오류");
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
		this.me.close();
	}
}
