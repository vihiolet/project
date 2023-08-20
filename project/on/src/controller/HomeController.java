package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.homeIndexAction;
import vo.ActionForward;

@WebServlet("*.do")
public class HomeController extends javax.servlet.http.HttpServlet{ 

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
	    String RequestURI = request.getRequestURI();
	    String contextPath = request.getContextPath();
	    String command = RequestURI.substring(contextPath.length());
	    ActionForward forward = null;
	    Action action = null;
	    
	    if(command.equals("/home.do")) {
	    	  action = new homeIndexAction();
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
