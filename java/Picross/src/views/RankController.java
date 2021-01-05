package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;

import Util.JDBCUtil;
import Util.util;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RankController extends MasterController{
	@FXML
	private TextField txtName;
	
	private Time time;
	
	private int id;
	
	@FXML
	private Label lblTime;
	
	public void setTime(Time time) {
		this.txtName.setText("");
		this.time = time;
		lblTime.setText("시간 : " + time.toString());
	}
	
	public void click() {
		String name = txtName.getText();
		if(name.isEmpty()) {
			util.showAlert("에러", "빈칸이 존재합니다.", AlertType.ERROR);
			return;
		}
		String query = "SELECT * FROM picross_rank WHERE name = ? AND board = ?";
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setInt(2, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sql = "UPDATE `picross_rank` SET `time`= ? WHERE name = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setTime(1, time);
				pstmt.setString(2, name);
			} else {
				sql = "INSERT INTO `picross_rank`(`name`, `time`,`board`) VALUES (?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setTime(2, time);
				pstmt.setInt(3, id);
			}
			System.out.println(sql);
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				util.showAlert("랭킹 등록", "랭킹에 등록했습니다.", AlertType.CONFIRMATION);
				Main.app.loadPane("stage");
				Main.app.slideOut(getRoot());
			} else {
				util.showAlert("오류", "DB에 등록하는 도중 오류가 터졌습니다.", AlertType.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 오류");
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
