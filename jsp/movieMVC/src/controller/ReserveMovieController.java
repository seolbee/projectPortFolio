package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.MovieDAO;
import movie.UserVO;

@WebServlet("/reserveMovie.do")

public class ReserveMovieController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		reserveMovie(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		reserveMovie(req, resp);
	}
	
	public void reserveMovie(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		MovieDAO instance = MovieDAO.getInstance();
		PrintWriter out = res.getWriter();
		
		int schNo = Integer.parseInt(req.getParameter("schNo"));
		int roomNo = Integer.parseInt(req.getParameter("roomNo"));
		int seatNo = Integer.parseInt(req.getParameter("seatNo"));
		
		int add = instance.addTicket(schNo, seatNo, user.getId());
		int update = instance.addSeatCnt(schNo, roomNo);
		
		if(add > 0 && update > 0) MovieDAO.successMsg(out, "예매 성공", "movieList.do");
	}
}
