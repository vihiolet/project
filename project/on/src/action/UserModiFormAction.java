package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.UserModiService;
import svc.UserListService;
import svc.UsersloginService;
import vo.ActionForward;
import vo.UserBean;

public class UserModiFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		String id= (String)session.getAttribute("id");
		UserListService userListService= null;
		UserBean userInfo= new UserBean();
		
		if(id != null) {			
			
			userListService= new UserListService();
			userInfo= userListService.getUserInfo2(id);
			request.setAttribute("userInfo", userInfo);
			
			System.out.println(userInfo);
		}
		
		ActionForward forward= new ActionForward();			
		SHA256Util sha256util= new SHA256Util();

		//내 정보 수정 service
		UserModiService userModiService= new UserModiService();
		
		//비번 확인 service
		UsersloginService userslogininService= new UsersloginService();
		
		String salt= userslogininService.LoginSetSalt(id);
		String passwd= request.getParameter("passwd");
		String passwdSalt= sha256util.getEncrypt(passwd, salt);	
		
		String isPasswd= userModiService.isPasswd(id);
		
		if(passwdSalt.equals(isPasswd)) {			
			//form에 내 정보 입력
			userListService= new UserListService();
			userInfo= userListService.getUserInfo2(id);
			request.setAttribute("userInfo", userInfo);
			
			forward.setPath("/front/UserModiForm.jsp");
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
