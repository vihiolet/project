package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import action.AdminKeyDeleteAction;
import action.AdminKeyListAction;
import action.AdminKeyModiFormAction;
import action.AdminKeyModifyAction;
import action.AdminKeyRegAction;
import vo.ActionForward;



@WebServlet("*.ke")
public class AdminKeywordController extends javax.servlet.http.HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");    
		String RequestURI= request.getRequestURI();
		String contextPath= request.getContextPath();
		String command= RequestURI.substring(contextPath.length());
		ActionForward forward= null;
		Action action= null;
		
		if(command.equals("/adminKey.ke")) {
			action= new AdminKeyListAction();
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		//등록
		}else if(command.equals("/adminKeywordReg.ke")) {	
			action= new AdminKeyRegAction();
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}			
		//삭제
		}else if(command.equals("/adminKeywordDel.ke")) {	
			action= new AdminKeyDeleteAction();
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		//수정 form
		}else if(command.equals("/adminKeywordModiForm.ke")) {	
			action= new AdminKeyModiFormAction();
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}			
		//수정
		}else if(command.equals("/adminKeywordModi.ke")) {	
			action= new AdminKeyModifyAction();
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
