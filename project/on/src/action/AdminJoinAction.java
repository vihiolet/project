package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.UsersJoinService;

import vo.ActionForward;
import vo.UserBean;

//회원 가입 요청
public class AdminJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserBean users= new UserBean();
		SHA256Util sha256util= new SHA256Util();
		boolean joinResult= false;
		
		//id
		users.setId(request.getParameter("id"));	
		
		//salt		
		String salt= sha256util.getSalt();
		//가져온 salt Bean에 담기
		users.setSalt(salt);
		
		//passwd
		String passwd= request.getParameter("passwd");
		String passwdSalt= sha256util.getEncrypt(passwd, salt);
		//암호되된 비번을 Bean에 담기
		users.setPass(passwdSalt);
		
		//name
		users.setName(request.getParameter("name"));
		
		UsersJoinService usersJoinService= new UsersJoinService();
		joinResult= usersJoinService.joinUsers(users);
		
		ActionForward forword= null;
		if(joinResult == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('join 실패')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			forword= new ActionForward();
			forword.setRedirect(true);
			forword.setPath("/adminLogin.ur");
		}
		return forword;
	}
	
}
