package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.MovieDAO;

@WebServlet("/checkSeat.do")

public class CheckSeatController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		checkSeat(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		checkSeat(req, resp);
	}
	
	public void checkSeat(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8;");
		PrintWriter out = res.getWriter();
		MovieDAO instance = MovieDAO.getInstance();
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("user") == null) {
			MovieDAO.successMsg(out, "로그인한 사용자만 들어올 수 있습니다.", "index.jsp");
			return;
		}
		
		
		int no = Integer.parseInt(req.getParameter("schNo"));
		
		boolean[] seatArr = instance.getSeatArr(no);
		int room = instance.getRoomNo(no);
		req.setAttribute("seat", seatArr);
		req.setAttribute("roomNo", room);
		req.setAttribute("schNo", no);
		
		RequestDispatcher rd = req.getRequestDispatcher("checkSeat.jsp");
		rd.forward(req, res);
	}
}
