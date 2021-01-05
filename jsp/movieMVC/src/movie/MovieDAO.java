package movie;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MovieDAO {
	private static MovieDAO instance = new MovieDAO();
	
	public static MovieDAO getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		String id = "hr";
		String pass = "1234";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public ArrayList<MovieVO> getMovieList(int type){
		ArrayList<MovieVO> list = new ArrayList<MovieVO>();
		String sql = "SELECT movie.movieNo, movie.movieName, movie.category, movie.img, movie.info, movie.runtime, sum(NVL(schedule.seatCnt, 0)) as seatCnt FROM movie LEFT OUTER JOIN (SELECT schedule.schNo, schedule.movieNo, Room.seatCnt FROM Room LEFT OUTER JOIN schedule ON Room.schNo = schedule.schNo) schedule ON movie.movieNo = schedule.movieNo GROUP BY (movie.movieNo, movie.movieName, movie.category, movie.img, movie.info, movie.runtime)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if(type > 0)  sql += " HAVING movie.category = ?";
		sql+= " ORDER BY seatCnt DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			if(type > 0) pstmt.setInt(1, type);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MovieVO vo = new MovieVO(rs.getInt("movieNo"), rs.getString("movieName"), rs.getString("category"), rs.getInt("runtime"), rs.getString("img"), rs.getString("info"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
		return list;
	}
	
	public int getMaxNo(String tbl_name, String param) {
		int no = 0;
		String sql = "SELECT MAX("+ param +") as "+ param +" FROM "+tbl_name;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) no = rs.getInt(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		
		return no;
	}
	
	public MovieVO getMovie(int no) {
		MovieVO vo = null;
		String sql = "SELECT movieNo, movieName, decode(category, 01, '액션', 02, '로맨스', 03, '코미디', 04, '스릴러', '애니메이션') as category, runtime, img, info FROM movie WHERE movieNo = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) vo = new MovieVO(rs.getInt("movieNo"), rs.getString("movieName"), rs.getString("category"), rs.getInt("runtime"), rs.getString("img"), rs.getString("info"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
			close(pstmt);
			close(rs);
		}
		
		return vo;
	}
	
	public UserVO getUser(String id, String password) {
		UserVO vo = null;
		String sql = "SELECT * FROM member WHERE id = ? AND pw = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) vo = new UserVO(rs.getString("id"), rs.getString("pw"), rs.getString("email"), rs.getString("tel"), rs.getDate("birth"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return vo;
	}
	
	public ArrayList<ScheduleVO> getScheduleList(int movieno){
		ArrayList<ScheduleVO> list = new ArrayList<ScheduleVO>();
		String sql = "SELECT schedule.schNo, schedule.movieNo, schedule.runtime, Room.roomNo, TO_CHAR(schedule.runDay, 'YYYY-MM-DD HH24:MI') as runDay, Room.seatCnt FROM schedule, Room WHERE movieNo = ? AND schedule.schNo = Room.schNo AND Room.seatCnt < 20";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ScheduleVO vo = new ScheduleVO(rs.getInt("schNo"), rs.getInt("movieNo"), rs.getString("runDay"), rs.getInt("runtime"), rs.getInt("roomNo"), rs.getInt("seatCnt"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return list;
	}
	
	public boolean[] getSeatArr(int schno) {
		boolean[] seat = new boolean[20];
		String sql = "SELECT * FROM ticket WHERE schno = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int seatNo = rs.getInt("seatNo");
				seat[seatNo-1] = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return seat;
	}
	
	public int getRoomNo(int schno) {
		int room = 0;
		String sql = "SELECT roomNo FROM schedule WHERE schNo = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schno);
			rs = pstmt.executeQuery();
			if(rs.next()) room = rs.getInt("roomNo");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return room;
	}
	
	public int addTicket(int schNo, int seatNo, String id) {
		int cnt = 0;
		String sql = "INSERT INTO ticket VALUES(?, SYSDATE, ?, ?, ?)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getMaxNo("ticket", "ticketNo")+1);
			pstmt.setInt(2, schNo);
			pstmt.setInt(3, seatNo);
			pstmt.setString(4, id);
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		return cnt;
	}
	
	public int addSeatCnt(int schNo, int roomNo) {
		int add = 0;
		
		String sql = "UPDATE Room SET seatCnt = seatCnt + 1 WHERE schNo = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schNo);
			add = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		return add;
	}
	
	public ArrayList<TicketVO> getTicketList(String id){
		ArrayList<TicketVO> list = new ArrayList<TicketVO>();
		String sql = "SELECT ticket.ticketNo, ticket.id, TO_CHAR(ticket.bookDate, 'YYYY-MM-DD HH24:mi') as bookDate, schedule.roomNo, TO_CHAR(schedule.runDay, 'YYYY-MM-DD HH24:mi') as runDay, movie.movieName, movie.runtime, ticket.seatNo, ticket.schNo FROM schedule, ticket, movie WHERE ticket.schNo = schedule.schNo AND schedule.movieNo = movie.movieNo AND ticket.id = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TicketVO vo = new TicketVO(rs.getInt("ticketNo"), rs.getString("id"),  rs.getString("bookDate"), rs.getInt("roomNo"), rs.getString("runDay"), rs.getString("movieName"), rs.getInt("runtime"), rs.getInt("seatNo"), rs.getInt("schNo"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return list;
	}
	
	public int deleteTicket(int ticketNo) {
		int cnt = 0;
		String sql = "DELETE FROM ticket WHERE ticketNo = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ticketNo);
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(conn);
		}
		return cnt;
	}
	
	public int deleteSeatCnt(int schNo) {
		int cnt = 0;
		String sql = "UPDATE Room SET seatCnt = seatCnt - 1 WHERE schNo = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schNo);
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		return cnt;
	}
	
	public int fileUpload(ServletContext context, HttpServletRequest request, int movieNo) {
		String directory = context.getRealPath("upload");
		int maxSize = 1024 * 1024 * 100;//100MB
		String encoding = "UTF-8";//한글이면 깨지니깐 방지
		int cnt = 0;
		
		
		try {
			MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
			
			String fileName = multipartRequest.getOriginalFileName("img");
			String name = multipartRequest.getParameter("movieName");
			int category = Integer.parseInt(multipartRequest.getParameter("category"));
			String info = multipartRequest.getParameter("info");
			int runtime = Integer.parseInt(multipartRequest.getParameter("runtime"));
			cnt = addMovie(movieNo, name, info, fileName, category, runtime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public int addMovie(int movieNo, String name, String info, String img, int category, int runtime) {
		int cnt = 0;
		String sql = "INSERT INTO movie VALUES(?, ?, ?, ?, ?, ?)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getMaxNo("movie", "movieNo")+1);
			pstmt.setString(2, name);
			pstmt.setInt(3, category);
			pstmt.setInt(4, runtime);
			pstmt.setString(5, img);
			pstmt.setString(6, info);
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		return cnt;
	}
	
	public int addSchedule(int movieNo, String runDay, int roomNo, int runtime) {
		int cnt = 0;
		String sql = "INSERT INTO schedule VALUES(?, ?, TO_DATE(?, 'yyyy-MM-dd HH24:mi'), ?, ?)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int schNo = getMaxNo("schedule", "schNo") + 1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schNo);
			pstmt.setInt(2, movieNo);
			pstmt.setString(3, runDay);
			pstmt.setInt(4, runtime);
			pstmt.setInt(5, roomNo);
			if(pstmt.executeUpdate() > 0) cnt = schNo;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return cnt;
	}
	
	public int addRoom(int schNo, int roomNo) {
		int cnt = 0;
		String sql = "INSERT INTO Room VALUES(?, ?, 0)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNo);
			pstmt.setInt(2, schNo);
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		return cnt;
	}
	
	public ArrayList<Integer> getRoomNoList() {
		ArrayList<Integer> roomNoArr = new ArrayList<Integer>();
		String sql = "SELECT DISTINCT roomNo FROM schedule";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int roomNo = rs.getInt("roomNo");
				roomNoArr.add(roomNo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return roomNoArr;
	}
	
	public boolean isRunday(int roomNo, String runDay) {
		boolean runday = false;
		String sql = "SELECT * FROM schedule WHERE roomNo = ? AND (runDay < TO_DATE(?, 'YYYY-MM-DD HH24:MI') AND runDay + (runtime / 60) / 24 > TO_DATE(?, 'YYYY-MM-DD HH24:MI')) OR (runDay < TO_DATE(?, 'YYYY-MM-DD HH24:MI') + (runtime / 60) / 24 AND TO_DATE(?, 'YYYY-MM-DD HH24:MI') + (runtime / 60) / 24 < runDay + (runtime / 60) / 24)";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNo);
			pstmt.setString(2, runDay);
			pstmt.setString(3, runDay);
			pstmt.setString(4, runDay);
			pstmt.setString(5, runDay);
			rs = pstmt.executeQuery();
			if(rs.next()) runday = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		return runday;
	}
	
	public ArrayList<TicketVO> getAllTicketList(int movieNo){
		ArrayList<TicketVO> list = new ArrayList<TicketVO>();
		String sql = "SELECT ticket.ticketNo, ticket.id, TO_CHAR(ticket.bookDate, 'YYYY-MM-DD HH24:mi') as bookDate, schedule.roomNo, TO_CHAR(schedule.runDay, 'YYYY-MM-DD HH24:mi') as runDay, movie.movieName, movie.runtime, ticket.seatNo, ticket.schNo FROM schedule, ticket, movie WHERE ticket.schNo = schedule.schNo AND schedule.movieNo = movie.movieNo AND movie.movieNo = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TicketVO vo = new TicketVO(rs.getInt("ticketNo"), rs.getString("id"), rs.getString("bookDate"), rs.getInt("roomNo"), rs.getString("runDay"), rs.getString("movieName"), rs.getInt("runtime"), rs.getInt("seatNo"),rs.getInt("schNo"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return list;
	}
	
	public int getCnt(String tbl) {
		int result = 0;
		String sql = "SELECT COUNT(*) as cnt FROM " + tbl;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) result = rs.getInt("cnt");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return result;
	}
	
	public int addUser(String id, String password, String email, String tel, String birth) {
		int cnt = 0;
		String sql = "INSERT INTO member VALUES(?, ?, ?, ?, TO_DATE(?, 'yyyy-MM-dd'))";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
			pstmt.setString(4, tel);
			pstmt.setString(5, birth);
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		return cnt;
	}
	
	public void close(ResultSet rs) {
		try { if(rs != null) rs.close();} catch (Exception e) {e.printStackTrace();}
	}
	
	public void close(PreparedStatement pstmt) {
		try { if(pstmt != null) pstmt.close();} catch (Exception e) {e.printStackTrace();}
	}
	
	public void close(Connection conn) {
		try { if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void successMsg(PrintWriter out, String msg, String url) {
		out.println("<script>");
		out.println("alert('"+msg+"');");
		out.println("location.href='"+url+"';");
		out.println("</script>");
	}
	
	public static void errorMsg(PrintWriter out, String msg) {
		out.println("<script>");
		out.println("alert('"+msg+"');");
		out.println("history.back()");
		out.println("</script>");
	}
	
	public static void confirmMsg(PrintWriter out, String msg, String trueURL, String falseURL) {
		out.println("<script>");
		out.println("if(confirm('"+ msg +"')){");
		out.println("location.href='" + trueURL +" +'}");
		out.println("else {");
		out.println("location.href='"+ falseURL +"'}");
		out.println("</script>");
	}
}
