package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Book.BookDAO;
import Book.BookVO;

@WebServlet("/insertBook.do")//어노테이션으로 insertBook.do로 서블릿 매핑을 시켜준다.

public class InsertBook extends HttpServlet{//서블릿으로 만들기 위해서 HttpServlet클래스를 상속받는다.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//get방식 요청 처리 매서드
		insertBook(req, resp);//get방식으로 insertBook.do로 요청이 들어올 때 insertBook() 매서드 실행
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//post방식 요청 처리 매서드
		insertBook(req, resp);//post방식으로 insertBook.do로 요청이 들어올 때 insertBook() 매서드 실행
	}
	
	public void insertBook(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{//header에 도서 등록 링크를 클릭했을 때 이곳으로 들어와서 insertBook.jsp로 이동하려고 하는 매서드 입니다.
		req.setCharacterEncoding("UTF-8");//파라미터 값들 중 한글이 있을 것을 고려해서 인코딩 -> utf-8로 해줌
		res.setContentType("text/html; charset=utf-8;");//출력시 한글이 있을 것을 고려해서 인코딩  -> utf-8로 해줌
		BookDAO instance = BookDAO.getInstance();//BookDAO.getInstance()로 BookDAO instance 객체 만들기
		int bcode = instance.getMaxNo();//입력하는 책의 코드를 구함.
		BookVO vo = new BookVO(bcode, "", "", -1, -1, null);//vo 객체를 생성하여 bcode를 넣고 나머지는 초기 값으로 설정
		req.setAttribute("book", vo);//session에 vo 객체 넣기
		RequestDispatcher rd = req.getRequestDispatcher("insertBook.jsp?type=insert");//forward 시켜주기 위한 객체 생성 //insertBook.jsp?type=insert는 insertBook.jsp에서 type이 insert 일 때에는 AddBook.do로 update일 때에는 EditBook.do로 action을 설정 해줌 
		rd.forward(req, res);// forward 시켜줌 (httpservletrequest, httpservletresponse)를 파라매터로 넣어줌
	}
}
