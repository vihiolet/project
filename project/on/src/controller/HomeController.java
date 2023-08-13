package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.UserIndexAction;
import action.homeIndexAction;
import vo.ActionForward;

public class HomeController extends javax.servlet.http.HttpServlet{ 

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	   IOException {
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
	             response.sendRedirect(forward.getPath());	//redirect방식 주소 변화 o
	          }else {
	             RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());	//forward(dispatcher) 방식 주소 변화 x
	             dispatcher.forward(request,response);
	          }
	       }
	    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
}
