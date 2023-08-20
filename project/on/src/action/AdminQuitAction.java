package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminLoginService;
import svc.AdminDelService;
import vo.ActionForward;

public class AdminQuitAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session= request.getSession();
		ActionForward forward= null;
		SHA256Util sha256util= new SHA256Util();
		
		//회원 삭제 service
		AdminDelService adminDelService= new AdminDelService();
		
		//비번 확인 service
		AdminLoginService adminLoginService= new AdminLoginService();
		
		String id= (String)session.getAttribute("id");
		String salt= adminLoginService.LoginSetSalt(id);
		String passwd= request.getParameter("nowPasswd");
		String passwdSalt= sha256util.getEncrypt(passwd, salt);	
		
		boolean deleteRsult= adminDelService.deleteAdmin(id, passwdSalt);	
		
		if(deleteRsult){
		   forward= new ActionForward();
		   forward.setRedirect(true);
		   forward.setPath("/adminLoginForm.ur");
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
