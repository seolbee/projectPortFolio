package Book;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO {
	private static BookDAO instance = new BookDAO();//BookDAO 변수를 가진 instance 객체를 선언 
	
	public static BookDAO getInstance() {//BookDAO 객체를 반환해주는 매서드
		return instance;//위에서 선언 된 instance 객체를 반환
	}
	
	public Connection getConnection() {//Connection을 생성하는 매서드
		String url = "jdbc:oracle:thin:@localhost:1521:xe";//db주소
		String user = "hr";//db username
		String password = "1234"; // db password
		Connection conn = null;// connection 변수
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password); // DriverManager.getConnection으로 Connection 객체 생성
		} catch (Exception e) {// try에서 오류가 날 시 바로 이 안에 구문을 실행
			e.printStackTrace();//오류나면 에러 항목 출력
		}
		return conn;//객체 반환
	}
	
	public int getMaxNo() {//입력할 책의 bcode 구하는 매서드
		int no = 0;//bcode의 최댓 값을 저장하는 변수 초기 값은 0
		String sql = "SELECT MAX(bcode) as bcode FROM BOOK_TBL";//BOOK_TBL 내에서 제일 큰 bcode 가져오기
		Connection conn = getConnection();//Connection 변수 선언과 getConnection()으로 Connection 객체를 생성하여 반환 시켜 대입
		PreparedStatement pstmt = null;// PreparedStatement 변수 선언 초기 값은 null
		ResultSet rs = null;//ResultSet 변수 선언 초기 값은 null
		try {
			pstmt = conn.prepareStatement(sql);//pstmt 객체 가져오기
			rs = pstmt.executeQuery();//sql 실행된 결과 값 가져오기
			if(rs.next()) no = rs.getInt("bcode");//rs.next()가 있으면 rs.getInt()로 제일 큰 bcode를 no에 넣어줌
		} catch (Exception e) {// try에서 오류가 날 시 바로 이 안에 구문을 실행
			e.printStackTrace();//오류나면 에러 항목 출력
		}
		return ++no;//제일 큰 bcode에 ++no 한 값 (다음 bcode)을 return
	}
	
	//close 매서드: 썼던 객체의 자원 반환
	//오버로드를 이용해서 Connection, PreparedStatment, ResultSet 객체의 close를 반환함 (이유 : 반환 코드는 같지만 반환해주는 변수의 타입 형이 다르기 때문에 오버로드를 사용하여 같은 코드에 다른 변수들을 넣을 수 있게 함)
	public void close(ResultSet rs) {
		try {if(rs != null) rs.close(); } catch (SQLException e) {e.printStackTrace();}
	}
	
	public void close(PreparedStatement pstmt) {
		try {if(pstmt != null) pstmt.close();} catch (SQLException e) {e.printStackTrace();}
	}
	
	public void close(Connection conn) {
		try {if(conn != null) conn.close();} catch (SQLException e) {e.printStackTrace();}
	}
	
	public ArrayList<BookVO> getList(){//BOOK_TBL의 전체 컬럼 값을 가져오는 매서드 selectBook.jsp에 쓰이는 거
		ArrayList<BookVO> list = new ArrayList<BookVO>();//BookVO형 변수만 들어가는 ArrayList 변수 선언과 대입
		String sql = "SELECT * FROM BOOK_TBL ORDER BY BCODE ASC";//BOOK_TBL의 전체 컬럼 값을 가져오는
		Connection conn = getConnection();//Connection 변수 선언과 getConnection()으로 Connection 객체를 생성하여 반환 시켜 대입
		PreparedStatement pstmt = null;//PreparedStatement 객체 선언 초기 값 null
		ResultSet rs = null;//ResultSet 변수 선언 초기 값은 null
		try {
			pstmt = conn.prepareStatement(sql);//PrepareStatement 객체 대입
			rs = pstmt.executeQuery();//sql 실행의 결과 값을 ResultSet으로 rs 에 대입
			while(rs.next()) {
				BookVO vo = new BookVO(rs.getInt("bcode"), rs.getString("btitle"), rs.getString("bwriter"), rs.getInt("bpub"), rs.getInt("bprice"), rs.getDate("bdate"));//BookVO 객체 만들기 -> rs.getInt()로 number형 컬럼 값 가져오기, rs.getString()으로 varchar2형 컬럼 값 가져오기 rs.getDate()로 date형 컬럼 값 가져오기
				list.add(vo);//list에 위에서 만든 BookVO 값 넣어주기
			}
		} catch (Exception e) {// try에서 오류가 날 시 바로 이 안에 구문을 실행
			e.printStackTrace();//오류시 에러 항목 출력
		}
		return list;//만들어진 list를 리턴
	}
	
	public int insertBook(int bcode, String btitle, String bwriter, int bpub, int bprice, String bdate) {//BOOK_TBL에 사용자가 입력한 값들을 추가하는 매서드 //파라매터로 bcode, btitle, bwriter, bpub, bprice, bdate 가져옴
		int cnt = 0;//추가하는 튜플의 갯수를 받아오기 위한 변수 (초기 값은 0)
		String sql = "INSERT INTO BOOK_TBL VALUES(?, ?, ?, ?, ?, ?)";//BOOK_TBL에 사용자가 입력한 값들을 추가하는 sql문 입력
		Connection conn = getConnection();//Connection 변수 선언과 getConnection()으로 Connection 객체를 생성하여 반환 시켜 대입
		Date date = Date.valueOf(bdate);//java.sql.date의 date로 위에서 받아온 String bdate를 Date형으로 형 변환 시켜줌
		PreparedStatement pstmt = null;//PreparedStatement 변수 선언 초기 값은 null
		try {
			pstmt = conn.prepareStatement(sql);//PrepareStatement 객체를 pstmt에 대입
			pstmt.setInt(1, bcode);//첫번 째 ?에 bcode 넣어주기 -> bcode가 int 형이기 때문에 setInt()
			pstmt.setString(2, btitle);//두번 째 ?에 btitle 넣어주기 -> btitle이 String 형이기 때문에 setString()
			pstmt.setString(3, bwriter);//세번 째 ?에 bwriter 넣어주기 -> bwriter String 형이기 때문에 setString()
			pstmt.setInt(4, bpub);//네번 째 ?에 bpub 넣어주기 -> bpub가 int 형이기 때문에 setInt()
			pstmt.setInt(5,  bprice);//다섯번 째 ?에 bprice 넣어주기 -> bprice가 int 형이기 때문에 setInt()
			pstmt.setDate(6, date);//여섯번 째 ?에 date 넣어주기 -> date가 Date 형이기 때문에 setDate()
			cnt = pstmt.executeUpdate();//executeUpdate()로 sql문을 실행하고, cnt에는 추가되는 튜플의 갯수를 cnt에 넣어줌
		} catch (Exception e) {// try에서 오류가 날 시 바로 이 안에 구문을 실행
			e.printStackTrace();//오류시 에러 항목을 출력
		} finally {//try와 catch문을 모두 거치고 나서 실행
			close(pstmt);
			close(conn);
			//객체 자원 반환
		}
		return cnt;//cnt 변수를 반환
	}
	
	public BookVO searchBook(int bcode) {//파라메터로 들어온 bcode에 해당하는 컬럼 값을 찾아주는 매서드 //파라매터로 bcode가 들어옴
		String sql = "SELECT * FROM BOOK_TBL WHERE BCODE = ?";//BOOK_TBL에서 bcode에 해당하는 컬럼 값을 가져와주는 sql문 선언
		Connection conn = getConnection();//Connection 변수 선언과 getConnection()으로 Connection 객체를 생성하여 반환 시켜 대입
		PreparedStatement pstmt = null;//PreparedStatement 변수 선언 초기 값은 null
		ResultSet rs = null;//ResultSet 변수 선언 초기 값은 null
		BookVO vo = null;//BookVO 변수 선언 초기 값은 null
		try {
			pstmt = conn.prepareStatement(sql);//PrepareStatement 객체 가져와 대입
			pstmt.setInt(1, bcode);//첫번 째 ?에 bcode 넣기 -> bcode가 int형 타입이기 때문에 setInt
			rs = pstmt.executeQuery();//executeQuery()로 실행된 sql의 결과 값을 rs 변수 안에 넣기
			if(rs.next()) vo = new BookVO(rs.getInt("bcode"), rs.getString("btitle"), rs.getString("bwriter"), rs.getInt("bpub"), rs.getInt("bprice"), rs.getDate("bdate"));
			//rs.next()가 true일 경우 -> 결과 값이 존재한다.
			//rs.next()가 false일 경우 -> 결과 값이 존재하지 않는다.
			//rs.next()가 true라면 vo변수 안에다가 BookVO 객체를 생성한다. 생성자에는 파라메터가 있기 때문에 rs.getInt()와 rs.getString()으로 각 컬럼 값들을 가져와 파라매터 값으로 넣어준다.
		} catch (Exception e) {// try에서 오류가 날 시 바로 이 안에 구문을 실행
			e.printStackTrace();//오류시 에러 항목들을 출력
		} finally {//try와 catch문을 모두 거치고 나서 실행
			close(rs);
			close(pstmt);
			close(conn);
			//썼던 객체들의 자원반환
		}
		return vo;//vo 변수를 리턴
	}
	
	public int updateBook(int bcode, String btitle, String bwriter, int bpub, int bprice, String bdate) {//해당하는 책의 정보를 수정하는 매서드 //파라매터로 bcode와 btitle, bwriter, bpub, bprice, bdate가 들어옴
		int cnt = 0;//수정된 튜플의 갯수를 저장하기 위한 변수 초기 값은 0;
		String sql = "UPDATE BOOK_TBL SET btitle = ?, bwriter = ?, bpub = ?, bprice = ?, bdate = ? WHERE bcode = ?";//위에 bcode에 해당하는 컬럼 값을 수정하는 sql문을 선언
		Connection conn = getConnection();//Connection 변수 선언과 getConnection()으로 Connection 객체를 생성하여 반환 시켜 대입
		PreparedStatement pstmt = null;//PreparedStatement 변수 선언 초기 값은 null
		Date date = Date.valueOf(bdate);//java.sql.date의 Date 형 변수 선언과 String 형으로 들어온 bdate를 Date으로 형변환 시켜줌 (sql문에 Date형 값을 넣기 위해서)
		try {
			pstmt = conn.prepareStatement(sql);//prepareStatement 객체를 대입
			pstmt.setString(1, btitle);//첫번 째 ? 에 btitle 넣어줌 -> String 타입이기 때문에 setString()
			pstmt.setString(2, bwriter);//두번 째 ? 에 bwriter 넣어줌 -> String 타입이기 때문에 setString()
			pstmt.setInt(3, bpub);//세번 째 ? 에 bpub 넣어줌 -> int 타입이기 때문에 setInt()
			pstmt.setInt(4, bprice);//네번 째 ? 에 bprice 넣어줌 -> int 타입이기 때문에 setInt()
			pstmt.setDate(5, date);//다섯 번째 ? 에 date 넣어줌 -> Date 타입이기 때문에 setDate()
			pstmt.setInt(6, bcode);//여섯 번째 ? 에 bcode 넣어줌 -> int 타입이기 때문에 setInt()
			cnt = pstmt.executeUpdate();//executeUpdate()로 sql문 실행 executeUpdate()로 반환된 수정한 튜플의 갯수를  cnt에 대입
		} catch (Exception e) {// try에서 오류가 날 시 바로 이 안에 구문을 실행
			e.printStackTrace();//오류시 에러 항목을 출력
		} finally {//try와 catch문을 모두 거치고 나서 실행
			close(pstmt);
			close(conn);
			//썼던 객체들의 반환
		}
		return cnt;//수정된 튜플들의 갯수들을 반환
	}
	
	public int deleteBook(int bcode) {//bcode에 해당하는 책의 정보를 삭제 //파라매터로 bcode가 들어옴
		int cnt = 0;//테이블에서 삭제된 컬럼의 갯수를 저장하기 위한 변수 초기 값 0
		String sql = "DELETE FROM BOOK_TBL WHERE bcode = ?";//bcode에 해당하는 컬럼을 삭제하기 위한 sql문 선언
		Connection conn = getConnection();//Connection 변수 선언 getConnection()으로 Connection 객체 생성 반환 그리고 conn에 대입
		PreparedStatement pstmt = null;//PreparedStatement 변수 선언 초기 값 null
		try {
			pstmt = conn.prepareStatement(sql);//prepareStatement 객체 대입
			pstmt.setInt(1, bcode);//첫번 째 ?에 bcode 넣기 -> int 형이기 때문에 setInt()
			cnt = pstmt.executeUpdate();//executeUpdate()로 sql문 실행 그리고 실행하면서 반환된 삭제된 튜플의 갯수를 cnt에 대입
		} catch (Exception e) {//try에서 오류가 날 시 바로 이 안의 구문을 실행
			e.printStackTrace();//오류시 에러 항목을 출력
		} finally {//try와 catch문을 모두 거치고 나서 실행
			close(pstmt);
			close(conn);
			//썼던 객체들의 반환
		}
		return cnt;//삭제된 튜플 값을 반환
	}
	
	public void sendMsg(PrintWriter out, String msg, String url) {//성공시 성공했다는 메시지를 띄어주고 다른 페이지로 이동하기 위한 매서드
		out.println("<script>");//script 태그 열기
		out.println("alert('" + msg +"');");//alert 함수로 안에 출력할 msg 변수 넣어줌
		out.println("location.href='"+url+"';");//location.href에 다음에 이동할 url 변수를 넣어줌
		out.println("</script>");//script 태그 닫기
	}
	
	public void errorMsg(PrintWriter out, String msg) {//실패시 실패했다는 메시지를 띄워주고 이전 페이지로 이동하기 위한 메서드
		out.println("<script>");//script 태그 열기
		out.println("alert('" + msg +"');");//alert 함수로 안에 출력할 msg 변수 넣어줌
		out.println("history.back();");//history.back()함수로 이전에 페이지로 이동
		out.println("</script>");//script 태그 닫기
	}
}
