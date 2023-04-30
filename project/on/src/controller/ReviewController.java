package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
//import action.BoardDeleteProAction;
//import action.BoardDetailAction;
import action.ReviewListAction;
//import action.BoardModifyFormAction;
//import action.BoardModifyProAction;
import action.ReviewWriteProAction;

import vo.ActionForward;

@WebServlet("*.on")
public class ReviewController extends javax.servlet.http.HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	   IOException {
	      request.setCharacterEncoding("UTF-8");
	      String RequestURI = request.getRequestURI();
	      String contextPath = request.getContextPath();
	      String command = RequestURI.substring(contextPath.length());
	      ActionForward forward = null;
	      Action action = null;

	      if(command.equals("/ReviewWriteForm.on")) {
	         forward = new ActionForward();
	         forward.setPath("/review/review_write.jsp");
	         
	      }else if(command.equals("/reviewWritePro.on")) {
	          action = new ReviewWriteProAction();
	          try {
	             forward=action.execute(request, response);
	          }catch(Exception e) {
	             e.printStackTrace();
	          }
	          
	       }
	      
	      if(forward!= null) {
	          if(forward.isRedirect()) {
	             response.sendRedirect(forward.getPath());
	          }else {
	             RequestDispatcher dispatcher = 
	                   request.getRequestDispatcher(forward.getPath());
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
	      
	
