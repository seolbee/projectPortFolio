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

@WebServlet("/login.do")

public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Login(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Login(req, resp);
	}
	
	public void Login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		String id = req.getParameter("id");
		String pass = req.getParameter("password");
		
		MovieDAO instance = MovieDAO.getInstance();
		UserVO vo = instance.getUser(id, pass);
		String url = "movieList.do";
		if(id.equals("admin")) url="Admin.do";
		
		if(vo == null) MovieDAO.errorMsg(out, "아이디 혹은 비밀번호가 틀렸습니다. 다시 확인해 주세요");
		else {
			HttpSession session = req.getSession();
			session.setAttribute("user", vo);
			res.sendRedirect(url);
		}
	}
}
