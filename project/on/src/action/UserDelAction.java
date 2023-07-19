package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.UserDelService;
import svc.UserListService;
import vo.ActionForward;
import vo.UserBean;

public class UserDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//HttpSession session= request.getSession();
		//String id= (String)session.getAttribute("id");
		String id= request.getParameter("id");
		ActionForward forward= new ActionForward();
		UserDelService userDelService= new UserDelService();
		boolean deleteRsult= userDelService.deleteUser(id);		

    if(deleteRsult){
      forward.setRedirect(true);
      forward.setPath("./index.jsp");
    }else{
      response.setContentType("text/html;charset=utf-8");
      PrintWriter out= response.getWriter();
      out.println("<script>");
      out.println("alert('탈퇴 실패');");
      out.println("location.href=history.back();");
      out.println("</script>");
    }				
		return forward;
	}

}
