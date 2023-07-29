package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.UserListService;
import svc.UserModiService;
import vo.ActionForward;
import vo.UserBean;

public class UserModiAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		String id= (String)session.getAttribute("id");
		
		ActionForward forward= null;
		SHA256Util sha256util= null;
		UserModiService userModiService= new UserModiService();
		
		//기존 이름 가져오기 위한 userInfo 객체
		UserBean userInfo= new UserBean();
		UserListService userListService= new UserListService();
		userInfo= userListService.getUserInfo2(id);			
		String oldName= userInfo.getName();
		
		String newName= request.getParameter("name");
		String passwd= request.getParameter("newPasswd");
		String salt= null;
		String passwdSalt= null;

		//비번 input null 체크
		if(passwd == null) passwd= "";

		//비번, 이름 모두 수정
		if(!newName.equals(oldName) && (passwd != "")){
			sha256util= new SHA256Util();
			salt= sha256util.getSalt();
			passwdSalt= sha256util.getEncrypt(passwd, salt);
			
			boolean upateSuccess= userModiService.userNamePassModi(id, newName, passwdSalt, salt);
			
			if(upateSuccess){
				//새로 로그인
				session.removeAttribute("id");
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/login.jsp");
			}else{
				
			}
		}
		//비번만 수정
		if(newName.equals(oldName) && (passwd != "")){
			sha256util= new SHA256Util();
			salt= sha256util.getSalt();
			passwdSalt= sha256util.getEncrypt(passwd, salt);
			
			boolean upateSuccess= userModiService.userPassModi(id, passwdSalt, salt);
			
			if(upateSuccess){
				session= request.getSession();
				session.removeAttribute("id");
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/login.jsp");
			}else{
				
			}
		}
		//이름만 수정
		if(!newName.equals(oldName) && (passwd == "")){
			boolean upateSuccess= userModiService.userNameModi(id, newName);
			if(upateSuccess){
				forward= new ActionForward();
				userListService= new UserListService();
				userInfo= userListService.getUserInfo2(id);
				request.setAttribute("userInfo", userInfo);
				forward.setPath("/front/UserModiForm.jsp");
			}else{
				
			}
		}		
		return forward;
	}

}
