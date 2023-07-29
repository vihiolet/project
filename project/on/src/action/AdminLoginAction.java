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
		ActionForward forword= new ActionForward();
		AdminLoginService adminLoginService = new AdminLoginService();
		
		String id= request.getParameter("id");
		String passwd= request.getParameter("passwd");
		emp.setEmp_id(id);
		emp.setEmp_pass(passwd);
		
		boolean loginResult= adminLoginService.login(emp);
		
		if(loginResult) {
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

