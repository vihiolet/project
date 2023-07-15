package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.AdminProRegAcrion;
import action.ProList1Action;
import action.ProList2Action;
import action.ProList3Action;
import action.ProList4Action;
import action.ProList5Action;
//import action.BoardDeleteProAction;
//import action.BoardDetailAction;
import action.ReviewListAction;
//import action.BoardModifyFormAction;
//import action.BoardModifyProAction;
import action.ReviewRegAction;

import vo.ActionForward;

@WebServlet("*.fr")
public class FrontController extends javax.servlet.http.HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	   IOException {
	      request.setCharacterEncoding("UTF-8");
	      String RequestURI = request.getRequestURI();
	      String contextPath = request.getContextPath();
	      String command = RequestURI.substring(contextPath.length());
	      ActionForward forward = null;
	      Action action = null;

	      if(command.equals("/ProList1.fr")) {
	    	  action = new ProList1Action();
	          try {
	             forward=action.execute(request, response);
	          }catch(Exception e) {
	             e.printStackTrace();
	          }	   		         
		  }else if(command.equals("/ProList2.fr")) {
	    	  action = new ProList2Action();
	          try {
	             forward=action.execute(request, response);
	          }catch(Exception e) {
	             e.printStackTrace();
	          }	   		         
		  }else if(command.equals("/ProList3.fr")) {
	    	  action = new ProList3Action();
	          try {
	             forward=action.execute(request, response);
	          }catch(Exception e) {
	             e.printStackTrace();
	          }	   		         
		  }else if(command.equals("/ProList4.fr")) {
	    	  action = new ProList4Action();
	          try {
	             forward=action.execute(request, response);
	          }catch(Exception e) {
	             e.printStackTrace();
	          }	   		         
		  }else if(command.equals("/ProList5.fr")) {
	    	  action = new ProList5Action();
	          try {
	             forward=action.execute(request, response);
	          }catch(Exception e) {
	             e.printStackTrace();
	          }	   		         
		  }else if(command.equals("/ReviewWriteForm.fr")) {
	         forward = new ActionForward();
	         forward.setPath("/front/review_write.jsp");
	         
	      }else if(command.equals("/reviewWriteRev.fr")) {
	          action = new ReviewRegAction();
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

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	          throws ServletException, IOException {
	       // TODO Auto-generated method stub
	       doProcess(request, response);
	    }

	    /**
	     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	     *      response)
	     */
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	          throws ServletException, IOException {
	       // TODO Auto-generated method stub
	       doProcess(request, response);
	    }

	 }
	      
	
