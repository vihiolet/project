package action;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminEmpRegService;
import svc.AdminInfoService;

import vo.ActionForward;
import vo.AdminEmpBean;
import vo.PageInfo;

public class AdminEmpRegAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		String session_id= (String)session.getAttribute("id");
		
		SHA256Util sha256util= new SHA256Util();
		boolean regResult= false;		
		AdminEmpBean emp = new AdminEmpBean();
		
		emp.setCreate_id(session_id);
		emp.setEmp_id(request.getParameter("id"));
		//salt		
		String salt= sha256util.getSalt();
		//가져온 salt Bean에 담기
		emp.setSalt(salt);
		
		//passwd
		String passwd= request.getParameter("passwd");
		String passwdSalt= sha256util.getEncrypt(passwd, salt);
		emp.setEmp_pass(passwdSalt);
		emp.setEmp_name(request.getParameter("name"));
		
		AdminEmpRegService adminEmpRegService= new AdminEmpRegService();
		regResult= adminEmpRegService.regEmp(emp);
		
		
		ActionForward forword= null;
		if(regResult == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('관리자 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			forword= new ActionForward();
			forword.setRedirect(true);
			forword.setPath("adminEmp.emp");
		}
		
		return forword;		//ActionForward 인스턴스 즉 ActionForward 객체를 리턴한다
	}

}
