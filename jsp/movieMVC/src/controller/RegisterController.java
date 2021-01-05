package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;

@WebServlet("/Register.do")
public class RegisterController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		register(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		register(req, resp);
	}
	
	private void register(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8;");
		PrintWriter out = res.getWriter();
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String tel = req.getParameter("tel");
		String birth = req.getParameter("birth");
		
		MovieDAO instance = MovieDAO.getInstance();
		
		int cnt = instance.addUser(id, password, email, tel, birth);
		if(cnt > 0) MovieDAO.successMsg(out, "회원가입 성공", "index.jsp");
		else MovieDAO.errorMsg(out, "회원가입 실패");
	}
}
