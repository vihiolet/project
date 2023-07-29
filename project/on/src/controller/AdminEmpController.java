package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.AdminEmpDelAction;
import action.AdminEmpListAction;
import action.AdminEmpRegAction;
import action.AdminKeyListAction;
import action.AdminLoginAction;
import action.AdminMainAction;
import vo.ActionForward;

@WebServlet("*.emp")
public class AdminEmpController extends javax.servlet.http.HttpServlet{
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String RequestURI= request.getRequestURI();
		String contextPath= request.getContextPath();
		String command= RequestURI.substring(contextPath.length());
		ActionForward forward= null;
		Action action= null;
		
		//관리자 메인(데시보드
		if(command.equals("/adminMain.emp")) {			
			action= new AdminMainAction();
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}		
		}
		//관리자 설정
		else if(command.equals("/adminEmp.emp")) {			
			action= new AdminEmpListAction();	
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("adminEmpReg.emp")) {
			action= new AdminEmpRegAction();
			try {
				forward= action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("adminEmpDel.emp")) {
			action= new AdminEmpDelAction();
			try {
				forward= action.execute(request, response);
			}catch(Exception e){
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
