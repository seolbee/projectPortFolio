package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Book.BookDAO;
import Book.BookVO;

@WebServlet("/selectBook.do")//어노테이션으로 selectBook.do로 서블릿을 매핑한다.

public class BookListController extends HttpServlet{//서블릿으로 만들어주기 위해 HttpServlet클래스를 상속 받는다.
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//get 요청을 처리하는 매서드 //파라매터로 HttpServletRequest객체와 HttpServletResponse객체를 받는다.
		getList(req, resp);//get방식으로 selectBook.do요청이 온다면 getList 매서드를 실행
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//post 요청을 처리하는 매서드 //파라매터로 HttpServletRequest객체와 HttpServletResponse객체를 받는다.
		getList(req, resp);//post방식으로 selectBook.do요청이 온다면 getList 매서드를 실행 
	}
	
	public void getList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{//BOOK_TBL의 전체 튜플 값을 가져오는 매서드
		BookDAO instance = BookDAO.getInstance();//BookDAO에 instance를 BookDAO instance에 가져온다.
		ArrayList<BookVO> list = instance.getList();//BookDAO에 getList()로 전체 book_tbl 값들을 ArrayList 형으로 받는다.
		req.setAttribute("list", list);//session에 list를 넣는다.
		
		RequestDispatcher rd = req.getRequestDispatcher("selectBook.jsp");//RequestDispatcher 변수 만들기 나중에 selectBook.jsp로 forward 시켜주기 위해서
		rd.forward(req, res);//forward 시켜줌 (HttpServletRequest, HttpServletResponse)를 파라매터로 넣는다.
	}
}
