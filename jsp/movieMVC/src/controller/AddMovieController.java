package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;

@WebServlet("/AddMovie.do")
public class AddMovieController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addMovie(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addMovie(req, resp);
	}
	
	public void addMovie(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		MovieDAO instance = MovieDAO.getInstance();
		
		ServletContext context = req.getServletContext();
		int movieNo = instance.getMaxNo("movie", "movieNo") + 1;
		int cnt = instance.fileUpload(context, req, movieNo);
		
		
		
		if(cnt > 0) res.sendRedirect("AddSchedule.jsp?movieNo="+movieNo);
	}
}
