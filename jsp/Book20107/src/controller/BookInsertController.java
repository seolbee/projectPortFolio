package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import Book.BookDAO;

@WebServlet("/AddBook.do")//어노테이션으로 AddBook.do로 서블릿 매핑 
public class BookInsertController extends HttpServlet{//서블릿으로 만들기 위해 HttpServlet을 상속
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//get 요청들을 처리하는 매서드
		bookInsert(req, resp);//get방식으로 AddBook.do 요청이 왔을 때 bookInsert 매서드 실행
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//post 요청들을 처리하는 매서드
		bookInsert(req, resp);//post방식으로 AddBook.do가 왔을 때 bookInsert 매서드 실행
	}
	
	
	public void bookInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{//BOOK_TBL에 값들을 추가하려는 매서드
		req.setCharacterEncoding("UTF-8");//파라미터 값들 중 한글이 있을 것을 고려해서 인코딩 -> utf-8로 해줌
		res.setContentType("text/html; charset=UTF-8");//출력시 한글이 있을 것을 고려해서 인코딩  -> utf-8로 해줌
		PrintWriter out = res.getWriter();//printwriter 변수 만들기 -> 출력시 사용
		int bcode = Integer.parseInt(req.getParameter("bcode"));//req.getParameter()로 bcode에 대입하기 // req.getParameter()는 반환 타입이 String 이기 때문에 Integer.parseInt()를 하여 int 형으로 형 변환을 해줘야 한다.
		String btitle = req.getParameter("btitle");//req.getParameter()로 btitle 대입하기
		String bwriter = req.getParameter("bwriter");//req.getParameter()로 bwriter 대입하기
		int bpub = Integer.parseInt(req.getParameter("bpub"));//req.getParameter()로 bpub 대입하기 // req.getParameter()는 반환 타입이 String 이기 때문에 Integer.parseInt()를 하여 int 형으로 형 변환을 해줘야 한다.
		int bprice = Integer.parseInt(req.getParameter("bprice"));//req.getParameter()로 bprice 대입하기 //req.getParameter()는 반환 타입이 String 이기 때문에 Integer.parseInt()를 하여 int 형으로 형 변환을 해줘야 한다.
		String bdate = req.getParameter("bdate");//req.getParameter()로 bdate 대입하기
		BookDAO instance = BookDAO.getInstance();//BookDAO instance 변수 생성
		int cnt = instance.insertBook(bcode, btitle, bwriter, bpub, bprice, bdate);//BookDAO에 insertBook()매서드 실행, 실행시 반환되는 값은 BOOK_TBL에 추가된 튜플의 갯수
		if(cnt > 0) instance.sendMsg(out, "도서 등록 성공", "selectBook.do");// cnt가 0보다 클 경우 -> BOOK_TBL에 추가된 튜플이 있다. -> 추가가 되었다. -> 성공 메시지 출력 다음 페이지로 이동
		else instance.errorMsg(out, "도서 등록 실패"); // cnt 0과 같거나 작을 경우 -> BOOK_TBL에 추가된 컬럼이 없다. -> 추가가 안 되었다. -> 실패 메시지 출력 이전 페이지로 이동
	}
}
