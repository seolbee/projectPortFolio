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
import movie.MovieVO;
import movie.ScheduleVO;

@WebServlet("/viewMovie.do")
public class viewMovieController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		viewMovie(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		viewMovie(req, resp);
	}
	
	public void viewMovie(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8;");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		MovieDAO instance = MovieDAO.getInstance();
		
		if(session.getAttribute("user") == null) MovieDAO.successMsg(out, "로그인 되지 않은 유저 입니다. 로그인 하세요", "Login.jsp");
		
		MovieVO vo = instance.getMovie(no);
		
		ArrayList<ScheduleVO> list = instance.getScheduleList(no);
		
		RequestDispatcher rd = req.getRequestDispatcher("viewMovie.jsp");
		
		req.setAttribute("movie", vo);
		req.setAttribute("ScheduleList", list);
		rd.forward(req, res);
		
	}
}
