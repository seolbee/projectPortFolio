package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Util.AlertUtil;
import Util.JDBCUtil;
import Util.contentVO;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;

public class MainController {
	
	@FXML
	private ListView<contentVO> list;
	
	private static ObservableList<contentVO> obList;
	
	@FXML
	public void initialize() {
		obList = FXCollections.observableArrayList();
		list.setItems(obList);
		System.out.println("메인 레이아웃 생성 완료");
		reload();
	}
	
	public void Btn_click() {
		Main.app.openWritePopup();
	}
	
	public static void reload() {
		obList.clear();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM DBboard";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				contentVO item = new contentVO();
				item.setId(rs.getInt("id"));
				item.setTitle(rs.getString("title"));
				item.setWriter(rs.getString("writer"));
				item.setContent(rs.getString("content"));
				item.setDate(rs.getDate("date"));
				obList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB오류");
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	public void delete() {
		if(obList.size() <= 0) {
			AlertUtil.MsgAlert(AlertType.ERROR, "에러입니다.", "작성된 글이 없습니다.");
			return;
		}
		int id = list.getSelectionModel().getSelectedItem().getId();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM DBboard WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("db오류");
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
		reload();
	}
	
	public void view() {
		if(obList.size() <= 0) {
			AlertUtil.MsgAlert(AlertType.ERROR, "에러입니다.", "작성된 글이 없습니다.");
			return;
		}
		contentVO item = list.getSelectionModel().getSelectedItem();
		Main.app.openViewPopup(item);
	}
	
	public void update() {
		if(obList.size() <= 0) {
			AlertUtil.MsgAlert(AlertType.ERROR, "에러입니다.", "작성된 글이 없습니다.");
			return;
		}
		contentVO item = list.getSelectionModel().getSelectedItem();
		Main.app.openUpdatePopup(item);
	}
}