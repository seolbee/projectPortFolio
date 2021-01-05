package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;

@WebServlet("/AddSchedule.do")

public class AddScheduleController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addSchedule(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addSchedule(req, resp);
	}
	
	public void addSchedule(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		MovieDAO instance = MovieDAO.getInstance();
		PrintWriter out = res.getWriter();
		
		int movieNo = Integer.parseInt(req.getParameter("movieNo"));
		String runDay = req.getParameter("runDay");
		int roomNo = Integer.parseInt(req.getParameter("roomNo"));
		
		int runtime = instance.getMovie(movieNo).getRuntime();
		
		if(instance.isRunday(roomNo, runDay)) {
			MovieDAO.errorMsg(out, "상영시간이 겹칩니다. 다시 입력하세요");
			return;
		}
		
		int schNo = instance.addSchedule(movieNo, runDay, roomNo, runtime);
		
		int cnt = instance.addRoom(schNo, roomNo);
		
		String url = "AddSchedule.jsp?movieNo="+movieNo;
		
		if(cnt > 0 ) MovieDAO.confirmMsg(out, "현재 등록한 영화의 스케줄을 또 등록하고 싶다면 확인 을 아니라면 취소를 입력하세요", url, "Admin.do");
	}
}
