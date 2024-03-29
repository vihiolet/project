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
import javax.servlet.http.HttpSession;

import action.Action;
import action.AdminEmpListAction;
import action.AdminLoginAction;
import action.JoinAction;
import action.UserModiAction;
import action.UserModiFormAction;
import action.UserQuitAction;
import action.UserQuitFormAction;
import action.UserReviewAction;
import action.UserReviewDelAction;
import action.JoinAction;
import action.loginAction;
import vo.ActionForward;
import vo.UserBean;
import dao.UsersDAO;
import svc.UserListService;

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
			
			close(con);
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
		//관리자 로그인 form
		}else if(command.equals("/adminLoginForm.ur")) {			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/admin_login.jsp");
		//
		}else if(command.equals("/adminLogin.ur")) {			
			action= new AdminLoginAction();
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		//로그아웃
		}else if(command.equals("/logout.ur")) {
			HttpSession session= request.getSession();
			session.removeAttribute("id");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/home.do");		
		//탈퇴
		}else if(command.equals("/userQuitForm.ur")) {
			action= new UserQuitFormAction();	
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		//탈퇴 Action
		}else if(command.equals("/userQuit.ur")) {
			action= new UserQuitAction();	
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		//내가 남긴 후기
		}else if(command.equals("/userReview.ur")) {
			action= new UserReviewAction();	
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		//내 정보 수정
		}else if(command.equals("/userPasswdInput.ur")) {
			
			HttpSession session= request.getSession();
			UserListService userListService= new UserListService();
			String id= (String)session.getAttribute("id");
			UserBean userInfo= userListService.getUserInfo2(id);
			request.setAttribute("userInfo", userInfo);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/front/UserPasswdInput.jsp");
			
		}else if(command.equals("/userModiForm.ur")) {
			action= new UserModiFormAction();	
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/userModi.ur")) {
			action= new UserModiAction();	
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		//내가 쓴 후기 삭제
		else if(command.equals("/userReviewDel.ur")) {
			action= new UserReviewDelAction();	
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
