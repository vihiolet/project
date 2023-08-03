package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminModiService;
import svc.AdminInfoService;
import svc.AdminLoginService;
import svc.UsersloginService;
import vo.ActionForward;
import vo.AdminEmpBean;

public class AdminInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		String id= (String)session.getAttribute("id");
		AdminInfoService adminInfoService= null;
		AdminEmpBean empInfo= null;
		
		if(id != null) {			
      adminInfoService= new AdminInfoService();
		  empInfo= adminInfoService.getUserInfo(id);
		  request.setAttribute("empInfo", empInfo);
		}
		
		ActionForward forward= new ActionForward();			
		SHA256Util sha256util= new SHA256Util();

		//내 정보 수정 service
		AdminModiService userModiService= new AdminModiService();
		
		//비번 확인 service
		AdminLoginService adminLoginService= new AdminLoginService();
		
		String salt= adminLoginService.LoginSetSalt(id);
		String passwd= request.getParameter("passwd");
		String passwdSalt= sha256util.getEncrypt(passwd, salt);	
		
		String isPasswd= userModiService.isPasswd(id);
		
		if(passwdSalt.equals(isPasswd)) {			
			//form에 내 정보 입력
			adminInfoService= new AdminInfoService();
			empInfo= adminInfoService.getUserInfo(id);
			request.setAttribute("empInfo", empInfo);
			
			forward.setPath("/front/admin_Info.jsp");
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호를 확인해주세요.');");
			out.println("location.href=history.back();");
			out.println("</script>");
		}
    
		return forward;
	}

}
