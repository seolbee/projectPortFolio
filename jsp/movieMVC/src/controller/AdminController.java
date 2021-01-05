package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;

@WebServlet("/Admin.do")
public class AdminController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		admin(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		admin(req, resp);
	}
	
	public void admin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8;");
		MovieDAO instance = MovieDAO.getInstance();
		
		int userCnt = instance.getCnt("member") - 1;
		int movieCnt = instance.getCnt("movie");
		int ticketCnt = instance.getCnt("ticket");
		
		req.setAttribute("userCnt", userCnt);
		req.setAttribute("movieCnt", movieCnt);
		req.setAttribute("ticketCnt", ticketCnt);
		RequestDispatcher rd = req.getRequestDispatcher("Admin.jsp");
		rd.forward(req, res);
	}

}
