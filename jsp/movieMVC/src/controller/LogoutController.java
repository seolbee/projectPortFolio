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

@WebServlet("/Logout.do")

public class LogoutController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Logout(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Logout(req, resp);
	}
	
	public void Logout(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8;");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		if(session.getAttribute("user") == null) MovieDAO.errorMsg(out, "로그인한 유저만 가능합니다.");
		session.invalidate();
		MovieDAO.successMsg(out, "로그아웃", "index.jsp");
	}
}
