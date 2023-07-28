package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.UserListService;
import vo.ActionForward;
import vo.UserBean;

public class UserModiAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		String id= (String)session.getAttribute("id");
		
		ActionForward forward= null;
		UserModiService userModiService= new UserModiService();
		
		//기존 이름 가져오기 위한 userInfo 객체
		UserBean userInfo= new UserBean;
		userListService= new UserListService();
		userInfo= userListService.getUserInfo2(id);			
		String oldName= userInfo.getName();
		
		String newName= request.getParamenter("name");
		String passwd= request.getParameter("passwd");
		String salt= null;
		String passwdSalt= null;

		//비번 input null 체크
		if(passwd == null) passwd= "";

		//비번, 이름 모두 수정
		if(!newName.equals(oldName) && (passwd != "")){
			salt= sha256util.getSalt();
			passwdSalt= sha256util.getEncrypt(passwd, salt);
			
			int upateSuccess= userModiService.userNamePassModi(id, newName, passwdSalt, salt);
			
			if(upateSuccess > 0){
				forward= new ActionForward();
				userListService= new UserListService();
				userInfo= userListService.getUserInfo2(id);
				request.setAttribute("userInfo", userInfo);
				forward.setPath("userModiForm.ur");
			}else{
				
			}
		}
		//비번만 수정
		if(newName.equals(oldName) && (passwd != "")){
			salt= sha256util.getSalt();
			passwdSalt= sha256util.getEncrypt(passwd, salt);
			int upateSuccess= userModiService.userNamePassModi(id, passwdSalt, salt);
			
			if(upateSuccess > 0){
				forward= new ActionForward();
				userListService= new UserListService();
				userInfo= userListService.getUserInfo2(id);
				request.setAttribute("userInfo", userInfo);
				forward.setPath("userModiForm.ur");
			}else{
				
			}
		}
		//이름만 수정
		if(!newName.equals(oldName) && (passwd == "")){
			int upateSuccess= userModiService.userNameModi(id, newName);
			if(upateSuccess > 0){
				forward= new ActionForward();
				userListService= new UserListService();
				userInfo= userListService.getUserInfo2(id);
				request.setAttribute("userInfo", userInfo);
				forward.setPath("userModiForm.ur");
			}else{
				
			}
		}		
		return forward;
	}

}
