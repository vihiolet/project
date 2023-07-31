package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminLoginService;
import vo.ActionForward;
import vo.AdminEmpBean;

public class AdminLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		AdminEmpBean emp= new AdminEmpBean();
		SHA256Util sha256util= new SHA256Util();
		ActionForward forword= null;
		AdminLoginService adminLoginService = new AdminLoginService();
		
		String id= request.getParameter("id");
		String salt= adminLoginService.LoginSetSalt(id);
		String passwd= request.getParameter("passwd");
		String passwdSalt= sha256util.getEncrypt(passwd, salt);
		
		emp.setEmp_id(id);
		emp.setSalt(salt);
		emp.setEmp_pass(passwdSalt);
		
		boolean loginResult= adminLoginService.login(emp);
		
		if(loginResult) {
			forword= new ActionForward();
			session.setAttribute("id", emp.getEmp_id());
			forword.setRedirect(true);
			forword.setPath("adminMain.emp");
		}else {
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('관리자 로그인 실패');");
			out.println("location.href= './admin_login.jsp';");
			out.println("</script>");
		}		
		return forword;
	}
}

