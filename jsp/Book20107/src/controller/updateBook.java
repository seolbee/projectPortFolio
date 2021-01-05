package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Book.BookDAO;
import Book.BookVO;

@WebServlet("/updateBook.do")//어노테이션으로 updateBook.do로 서블릿 매핑

public class updateBook extends HttpServlet{//서블릿으로 만들어주기 위해 HttpServlet클래스를 상속 받는다.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//get전송 방식 처리 매서드
		updateBook(req, resp);//get전송 방식 으로 updateBook.do로 요청이 온다면 updateBook()메서드를 실행
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//post전송 방식 처리 매서드
		updateBook(req, resp);//post전송 방식으로 updateBook.do로 요청이 온다면 updateBook() 매서드를 실행
	}
	
	public void updateBook(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{//파라매터로 들어온 bcode로 도서 정보를 찾아서 insertBook.jsp에 전달하려는 매서드
		req.setCharacterEncoding("UTF-8");//파라미터 값들 중 한글이 있을 것을 고려해서 인코딩 -> utf-8로 해줌
		res.setContentType("text/html; charset=utf-8;");//출력시 한글이 있을 것을 고려해서 인코딩  -> utf-8로 해줌
		int bcode = Integer.parseInt(req.getParameter("bcode"));//req.getParameter()로 가져온 bcode를 변수로 만든다. -> req.getParameter()의 반환 타입은 string이기 때문에 Integer.parseInt()를 이용하여 int형 형변환을 해줘야 한다.
		BookDAO instance = BookDAO.getInstance();//BookDAO.getInstance()로 BookDAO instance를 가져온다.
		BookVO vo = instance.searchBook(bcode);//BookDAO에 있는 searchBook()메서드를 이용해서 bcode에 해당하는 도서의 정보를 BookVO 객체로 가져온다. 파라매터 bcode를 넣어준다.
		RequestDispatcher rd = req.getRequestDispatcher("insertBook.jsp?type=update");//forward를 시켜주기 위한 객체 생성
		req.setAttribute("book", vo);//session에 해당 vo을 넣어준다.
		rd.forward(req, res);//forward 시켜준다. 파라매터로는 (HttpServletRequest, HttpServletResponse)를 넣는다.
	}
}
