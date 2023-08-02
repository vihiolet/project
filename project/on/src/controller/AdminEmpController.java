package controller;

import static db.JdbcUtil.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.AdminEmpDelAction;
import action.AdminEmpListAction;
import action.AdminEmpRegAction;
import action.AdminLoginAction;
import action.AdminMainAction;
import dao.AdminEmpDAO;
import svc.AdminInfoService;
import vo.ActionForward;
import vo.AdminEmpBean;

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
		}else if(command.equals("/adminEmpRegForm.emp")) {
			
			HttpSession session= request.getSession();
			String id= (String)session.getAttribute("id");
			AdminInfoService adminInfoService= null;
			AdminEmpBean empInfo= null;
			
			adminInfoService= new AdminInfoService();
			empInfo= adminInfoService.getUserInfo(id);
			request.setAttribute("empInfo", empInfo);

			forward= new ActionForward();
			forward.setPath("/admin/admin_emp_reg.jsp");
			
		}else if(command.equals("/adminEmpReg.emp")) {
			action= new AdminEmpRegAction();
			try {
				forward= action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/adminEmpDel.emp")) {
			action= new AdminEmpDelAction();
			try {
				forward= action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			//관리자 등록 id 중복체크 팝업
		}else if(command.equals("/idCheckForm.emp")) {			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./admin/idCheck_popup.jsp");
		//관리자 등록 id 중복체크
		}else if(command.equals("/adminIdChk.emp")) {
			
			Connection con= getConnection();
			AdminEmpDAO adminEmpDAO= AdminEmpDAO.getInstance();
			adminEmpDAO.setConnection(con);
			
			String id= request.getParameter("chkId");
			boolean result= adminEmpDAO.duplicationIdChk(id);

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out= response.getWriter();
			if(result) out.println("1");	//id 중복
			else out.println("0");		
			close(con);
		//내 정보 관리
		}else if(command.equals("/adminInfo.emp")) {
			action= new AdminInfoAction();
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
