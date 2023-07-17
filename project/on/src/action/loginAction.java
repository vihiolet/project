package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.UsersJoinService;
import svc.UsersloginService;
import vo.ActionForward;
import vo.UserBean;

public class loginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();		
		UserBean user= new UserBean();
		SHA256Util sha256util= new SHA256Util();
		UsersloginService usersJoinService= new UsersloginService();
		ActionForward forword= null;
		
		String id= request.getParameter("id");
		String salt= usersJoinService.LoginSetSalt(id);
		String passwd= request.getParameter("passwd");
		String passwdSalt= sha256util.getEncrypt(passwd, salt);
		
		user.setId(id);
		user.setSalt(salt);
		user.setPass(passwdSalt);
		
		boolean loginResult= usersJoinService.login(user);	
		
		if(loginResult) {
			forword= new ActionForward();
			session.setAttribute("id", user.getId());
			forword.setRedirect(true);
			forword.setPath("userIndex.fr");
		}else {
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 실패');");
			out.println("location.href= './login.jsp';");
			out.println("</script>");
		}		
		return forword;
	}
}
