package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;

@WebServlet("/deleteTicket.do")
public class DeleteTicketController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		deleteTicket(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		deleteTicket(req, resp);
	}
	
	public void deleteTicket(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTf-8");
		MovieDAO instance = MovieDAO.getInstance();
		PrintWriter out = res.getWriter();
		
		int ticketNo = Integer.parseInt(req.getParameter("ticketNo"));
		int schNo = Integer.parseInt(req.getParameter("schNo"));
		int delete = instance.deleteTicket(ticketNo);
		int update = instance.deleteSeatCnt(schNo);
		
		if(delete > 0 && update > 0) MovieDAO.successMsg(out, "예매 취소 완료", "user.do");
	}
}
