package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.AdminEmpListAction;
import action.JoinAction;
import action.JoinAction;
import action.loginAction;
import vo.ActionForward;
import dao.UsersDAO;
import static db.JdbcUtil.*;

@WebServlet("*.ur")
public class UsersController extends javax.servlet.http.HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String RequestURI= request.getRequestURI();
		String contextPath= request.getContextPath();
		String command= RequestURI.substring(contextPath.length());
		ActionForward forward= null;
		Action action= null;
		
		//사용자 회원가입
		if(command.equals("/Join.ur")) {			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/join.jsp");
		}else if(command.equals("/JoinAction.ur")) {			
			action= new JoinAction();	
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		//회원가입 id 중복체크 팝업
		}else if(command.equals("/idCheckForm.ur")) {			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./front/idCheck_popup.jsp");
		//회원가입 id 중복체크
		}else if(command.equals("/userIdChk.ur")) {
			
			Connection con= getConnection();
			UsersDAO userDAO= UsersDAO.getInstance();
			userDAO.setConnection(con);
			
			String id= request.getParameter("chkId");
			boolean result= userDAO.duplicationIdChk(id);

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out= response.getWriter();
			if(result) out.println("1");	//id 중복
			else out.println("0");		
			
		//사용자 로그인
		}else if(command.equals("/login.ur")) {			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/login.jsp");
		}else if(command.equals("/loginAction.ur")) {			
			action= new loginAction();	
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		//관리자 로그인
		}else if(command.equals("/adminLogin.ur")) {			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin_login.jsp");
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher= request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}
}
