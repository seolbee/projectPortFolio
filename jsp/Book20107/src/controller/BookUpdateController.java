package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Book.BookDAO;

@WebServlet("/EditBook.do")//어노테이션을 이용하여 EditBook.do로 서블릿 매핑

public class BookUpdateController extends HttpServlet{//서블릿으로 만들어주기 위해 HttpServlet클래스를 상속 받는다.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//get방식요청을 처리하는 매서드
		editBook(req, resp);//get방식 EditBook.do로 요청할 경우 editBook 매서드를 실행
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//post방식 요청을 처리하는 매서드
		editBook(req, resp);//post방식 EditBook.do로 요청할 경우 editBook 매서드를 실행
	}
	
	public void editBook(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{//도서 정보를 수정하는 매서드
		req.setCharacterEncoding("UTF-8");//파라미터 값들 중 한글이 있을 것을 고려해서 인코딩 -> utf-8로 해줌
		res.setContentType("text/html; charset=UTF-8");//출력시 한글이 있을 것을 고려해서 인코딩  -> utf-8로 해줌
		PrintWriter out = res.getWriter();//printwriter 변수 만들기 -> 출력시 사용
		int bcode = Integer.parseInt(req.getParameter("bcode"));//req.getParameter()로 bcode를 가져온다. -> req.getParameter()는 반환 타입은 String 이기 때문에 Integer.parseInt()로 int 형 변환을 해줘야 한다.
		String btitle = req.getParameter("btitle");//req.getParameter()로 btitle을 가져온다.
		String bwriter = req.getParameter("bwriter");//req.getparameter()로 bwriter를 가져온다.
		int bpub = Integer.parseInt(req.getParameter("bpub"));//req.getParameter()로 bpub를 가져온다. -> req.getParameter()는 반환타입이 String 이기 때문에 Integer.parseInt()로 int 형 변환을 해줘야 한다.
		int bprice = Integer.parseInt(req.getParameter("bprice"));//req.getParameter()로 bprice를 가져온다 -> req.getParameter()는 반환타입이 String 이기 때문에 Integer.parseInt()로 int 형 변환을 해줘야 한다.
		String bdate = req.getParameter("bdate");//req.getParameter()로 bdate를 가져온다.
		
		BookDAO instance = BookDAO.getInstance();//BookDAO.getInstance()로 BookDAO instance를 가져온다.
		int cnt = instance.updateBook(bcode, btitle, bwriter, bpub, bprice, bdate);//BookDAO에 updaetBook()매서드를 실행한다. 파라매터로 bcode, btitle, bwriter, bpubm bprice, bdate를 가져온다. updatebook 매서드의 반환 값인 수정된 튜플 개수를 받는다.
		if(cnt > 0) instance.sendMsg(out, "도서정보수정이 완료되었습니다.", "selectBook.do");//cnt가 0보다 클 경우 -> 수정된 튜플 개수가 있다. -> 튜플이 수정 되었다. -> 성공메시지 출력과 selectBook.do 페이지로 이동
		else instance.errorMsg(out, "도서 정보 수정 실패");// cnt가 0보다 작을 경우 -> 수정된 튜플 개수가 없다 -> 튜플이 수정되지 않았다. -> 실패 메시지 출력 이전 페이지로 이동
	}
}
