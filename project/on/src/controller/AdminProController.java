package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.AdminKeyListAction;
import action.AdminKeyRegAction;	//검색점 action
import action.AdminProDeleteAcrion;
import action.AdminProRegAcrion;	//상품등록 actoin
import action.ProViewAction;
import action.srchProListAction;
import svc.AdminInfoService;
import action.AdminProListAction;	//상품목록 actoin
import action.AdminProModiAction;
import action.AdminProModiFormAction;
import action.AdminProPopupAcrion;
import vo.ActionForward;
import vo.AdminEmpBean;


@WebServlet("*.pr")
public class AdminProController extends javax.servlet.http.HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/adminProList.pr")) {			
			action= new AdminProListAction();
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/productRegForm.pr")) {
			
			HttpSession session= request.getSession();
			String id= (String)session.getAttribute("id");
			AdminInfoService adminInfoService= null;
			AdminEmpBean empInfo= null;
			
			adminInfoService= new AdminInfoService();
			empInfo= adminInfoService.getUserInfo(id);
			request.setAttribute("empInfo", empInfo);

			forward= new ActionForward();
			forward.setPath("/admin/admin_pro_reg.jsp");
			
		}else if(command.equals("/adminProReg.pr")) {
			action= new AdminProRegAcrion();
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/adminProPopup.pr")) {
			action= new AdminProPopupAcrion();
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/adminProDel.pr")) {
			action= new AdminProDeleteAcrion();
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/proModiForm.pr")) {
	    	  action = new AdminProModiFormAction();
	          try {
	             forward=action.execute(request, response);
	          }catch(Exception e) {
	             e.printStackTrace();
	          }	   		         
		}else if(command.equals("/proModi.pr")) {
	    	  action = new AdminProModiAction();
	          try {
	             forward=action.execute(request, response);
	          }catch(Exception e) {
	             e.printStackTrace();
	          }	   		         
		}
		//검색
		else if(command.equals("/srchProList.pr")) {
	    	  action = new srchProListAction();
	          try {
	             forward=action.execute(request, response);
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
