package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.UserDelService;
import svc.UserListService;
import svc.UsersloginService;
import vo.ActionForward;
import vo.UserBean;

public class UserQuitAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		ActionForward forward= null;
		SHA256Util sha256util= new SHA256Util();
		
		//회원 삭제 service
		UserDelService userDelService= new UserDelService();
		//비번 확인 service
		UsersloginService usersJoinService= new UsersloginService();

		String id= (String)session.getAttribute("id");
		String salt= usersJoinService.LoginSetSalt(id);
		String passwd= request.getParameter("passwd");
		String passwdSalt= sha256util.getEncrypt(passwd, salt);	

		boolean deleteRsult= userDelService.deleteUser(id, passwdSalt);		

	    if(deleteRsult){
	    	forward= new ActionForward();
		      forward.setRedirect(true);
		      forward.setPath("home.do");
	      response.setContentType("text/html;charset=utf-8");
	      PrintWriter out= response.getWriter();
	      out.println("<script>");
	      out.println("alert('탈퇴되었습니다');");
	      out.println("</script>");
	      
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
