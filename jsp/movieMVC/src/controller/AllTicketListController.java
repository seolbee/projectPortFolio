package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;
import movie.MovieVO;
import movie.TicketVO;

@WebServlet("/AllTicketList.do")

public class AllTicketListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		allTicketList(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		allTicketList(req, resp);
	}
	
	public void allTicketList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8;");
		
		MovieDAO instance = MovieDAO.getInstance();
		
		ArrayList<MovieVO> MovieList = instance.getMovieList(0);
		ArrayList<ArrayList<TicketVO>> AllTicketList = new ArrayList<>();
		MovieList.forEach(x-> {
			ArrayList<TicketVO> list = instance.getAllTicketList(x.getNo());
			AllTicketList.add(list);
		});
		
		req.setAttribute("AllTicketList", AllTicketList);
		
		RequestDispatcher rd = req.getRequestDispatcher("AllTicketList.jsp");
		
		rd.forward(req, res);
	}
}
