package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Book.BookDAO;

@WebServlet("/deleteBook.do")//어노테이션으로 deleteBook.do으로 서블릿 매핑 

public class BookDeleteController extends HttpServlet{//서블릿파일로 만들기 위해 HttpServlet을 상속받음
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//get으로 들어온 요청들을 처리
		deleteBook(req, resp);//get방식으로 deleteBook.do로 들어온 경우 deleteBook매서드 실행
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//post로 들어온 요청들을 처리
		deleteBook(req, resp);//post방식으로 deleteBook.do로 들어온 경우 deleteBook매서드 실행
	}
	
	public void deleteBook(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//인코딩 설정 -> 요청을 받은 과정에서 파라미터의 값이 한국어로 설정될 경우
		res.setContentType("text/html; charset=UTF-8");//ContentType 설정 -> 나중에 PrintWriter로 한국어를 출력할 경우를 대비
		PrintWriter out = res.getWriter();//printwriter 변수 선언과 printwriter객체 가져오기
		int bcode = Integer.parseInt(req.getParameter("bcode"));//req.getParameter()로 bcode의 값 가져오기 // req.getParameter()는 무조건 String 형으로 반환이기 때문에 Integer.parseInt()로 형변환을 해줘야 함
		BookDAO instance = BookDAO.getInstance();//BookDAO의 instance 객체를 가져오기
		int cnt = instance.deleteBook(bcode);//BookDAO 안에 deleteBook() 매서드 실행 cnt는 deleteBook()에서 반환되는 BOOK_TBL 내에서 삭제된 컬럼의 갯수를 받음
		if(cnt > 0) instance.sendMsg(out, "도서 삭제 완료", "selectBook.do");//cnt 0 보다 클경우 -> 삭제된 컬럼이 있다. -> 삭제가 되었다. -> sendMsg()로 성공 메시지 출력과 다음 페이지로 이동
		else instance.errorMsg(out, "도서 삭제 실패 ");//cnt 0과 같거나 작을 경우 -> 삭제된 컬럼이 없다. -> 삭제가 안 되었다. -> errorMsg()로 실패 메시지 출력 이전 페이지로 이동
	}
}
