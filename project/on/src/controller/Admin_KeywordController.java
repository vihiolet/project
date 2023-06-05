package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import action.AdminKeyListAction;
import action.AdminKeyRegAction;
import action.AdminProRegAcrion;	//상품등록 actoin
import dao.AdminProDAO;
import svc.KeywordRegService;
import action.AdminProListAction;	//상품목록 actoin
import vo.ActionForward;
import vo.KeywordBean;


@WebServlet("*.ke")
public class Admin_KeywordController extends javax.servlet.http.HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/adminKey.ke")) {
			System.out.println(command);
			action= new AdminKeyListAction();
			try {
				forward= action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/adminKeywordReg.ke")) {	//jsp에서 ajax에 써놨음
						
			KeywordBean keywordBean= new KeywordBean();
			keywordBean.setSrch_name(request.getParameter("srch_name"));
			keywordBean.setCreate_id(Integer.parseInt(request.getParameter("create_id")));
			keywordBean.setRemark(request.getParameter("remark"));	
			KeywordRegService keywordRegService= new KeywordRegService();
			boolean isRegSuccess= keywordRegService.registKeyword(keywordBean);	
			PrintWriter out = response.getWriter();
			if(isRegSuccess) {
				out.print("reg success");
			}else {
				out.print("reg fail");
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
