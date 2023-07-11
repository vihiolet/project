package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.LoginAction;
import svc.LoginService;
import vo.UserBean;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	//
	private static final long serialVersionUID = 1L;
	
	//생성자
	public LoginController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Cookie[] cookieArray= request.getCookies();
		String id= "";
		String pass= "";
		
		if(cookieArray != null) {
			for(int i=0; i < cookieArray.length; i++) {
				if(cookieArray[i].getName().equals("id")) {
					id= cookieArray[i].getValue();
				}
				else if(cookieArray[i].getName().equals("pass")) {
					pass= cookieArray[i].getValue();
				}
			}
			
			LoginService loginService= new LoginService();
			UserBean loginUser= loginService.getLoginUser(id, pass);
			
			if(loginUser != null) {
				//세션 자동 로그인 
				RequestDispatcher dispatcher= request.getRequestDispatcher("");
				request.setAttribute("loginUser", loginUser);
				dispatcher.forward(request, response);
			}else {
				//로그인 시도
				RequestDispatcher dispatcher= request.getRequestDispatcher("");
				dispatcher.forward(request, response);
			}
		}		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String id= request.getParameter("id");
		String pass= request.getParameter("pass");		
		String useCookie= request.getParameter("useCookie");
		LoginAction loginAction= new LoginAction();
		String salt= "";
		pass= loginAction.getEncrypt(pass, salt);	//암호화된 비번
		UserBean loginUser= loginAction.LoginUser(id, pass);
		
		//다음 로그인 시 쿠키 사용
		if(useCookie != null) {
			Cookie idCookie= new Cookie("id", id);
			idCookie.setMaxAge(60 * 60 * 24);
			Cookie passCookie= new Cookie("pass", pass);
			passCookie.setMaxAge(60 * 60 * 24);
			response.addCookie(idCookie);
			response.addCookie(passCookie);
		}
		
		if(loginUser != null) {
			//로그인 성공
			RequestDispatcher dispatcher= request.getRequestDispatcher("");
			request.setAttribute("loginUser", loginUser);
			dispatcher.forward(request, response);
		}else {
			//로그인 실패
			RequestDispatcher dispatcher= request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
		
	}

}
