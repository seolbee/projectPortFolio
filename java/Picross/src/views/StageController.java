package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Util.JDBCUtil;
import Util.util;
import application.Main;
import domain.BoardVO;
import domain.RankVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;

public class StageController extends MasterController{
	@FXML
	private ListView<BoardVO> listView;
	
	private ObservableList<BoardVO> list;
	
	@FXML
	private ListView<RankVO> RankView;
	
	private ObservableList<RankVO> rank;
	
	private String level;
	
	@FXML
	public void initialize(){
		list = FXCollections.observableArrayList();
		listView.setItems(list);
		rank = FXCollections.observableArrayList();
		RankView.setItems(rank);
	}
	
	public void setList() {
		list.clear();
		rank.clear();
		String sql = "SELECT picross_board.*, picross_level.id, picross_level.length FROM picross_board, picross_level WHERE picross_level.name = ? AND picross_level.id = picross_board.level";
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, level);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO item = new BoardVO();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setLocation(rs.getString("location"));
				item.setLength(rs.getInt("length"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB오류");
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	public void gameStart() {
		BoardVO item = listView.getSelectionModel().getSelectedItem();
		if(item == null) {
			util.showAlert("오류", "보드판을 선택하세요", AlertType.ERROR);
			return;
		}
		Main.app.setMainInfo(item.getLocation(), item.getLength(), item.getId());
		Main.app.slideOut(getRoot());
	}
	
	public void list_click() {
		BoardVO item = listView.getSelectionModel().getSelectedItem();
		if(item == null) {
			util.showAlert("오류", "보드 판을 선택하세요", AlertType.ERROR);
			return;
		}
		String sql = "SELECT * FROM picross_rank WHERE board = ? ORDER BY time ASC";
		rank.clear();
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item.getId());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RankVO rank_item = new RankVO();
				rank_item.setId(rs.getInt("id"));
				rank_item.setName(rs.getString("name"));
				rank_item.setTime(rs.getTime("time"));
				rank.add(rank_item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 오류");
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	public void cancel() {
		Main.app.loadPane("queue");
	}
}
