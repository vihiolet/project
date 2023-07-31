package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.UserListService;
import vo.ActionForward;
import vo.UserBean;

public class UserIndexAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		
		//loginAction에서 session.setAttribute("id", user.getId());
		String id= (String)session.getAttribute("id");
		
		ActionForward forward= new ActionForward();
		UserListService userListService= new UserListService();
		UserBean userInfo= new UserBean();
		
		if(id != null) {			
			
			userListService= new UserListService();
			userInfo= userListService.getUserInfo2(id);
			request.setAttribute("userInfo", userInfo);
		}
		
		forward.setPath("./index.jsp");
				
		return forward;
	}

}
