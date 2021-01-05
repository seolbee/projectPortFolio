package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.MovieDAO;
import movie.TicketVO;
import movie.UserVO;

@WebServlet("/user.do")
public class UserController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		user(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		user(req, resp);
	}
	
	public void user(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();
		PrintWriter out = res.getWriter();
		if(session.getAttribute("user") == null) MovieDAO.successMsg(out, "로그인 되지 않은 유저 입니다. 로그인 하세요.", "Login.jsp");
		UserVO user = (UserVO) session.getAttribute("user");
		MovieDAO instance = MovieDAO.getInstance();
		
		ArrayList<TicketVO> list = instance.getTicketList(user.getId());
		
		req.setAttribute("ticketList", list);
		RequestDispatcher rd = req.getRequestDispatcher("user.jsp");
		rd.forward(req, res);
		
	}
}
