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

@WebServlet("/movieList.do")

public class movieListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getMovieList(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getMovieList(req, resp);
	}
	
	public void getMovieList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		MovieDAO instance = MovieDAO.getInstance();
		
		int type = 0;
		if(req.getParameter("type") != null) type = Integer.parseInt(req.getParameter("type"));
		
		ArrayList<MovieVO> list = instance.getMovieList(type);
		
		req.setAttribute("movieList", list);
		RequestDispatcher rd = req.getRequestDispatcher("movieList.jsp");
		rd.forward(req, res);
	}
}
