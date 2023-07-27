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
		ActionForward forward= new ActionForward();		
		UserListService userListService= null;
		UserBean userInfo= null;
		
		String id= (String)session.getAttribute("id");
		
		userListService= new UserListService();
		userInfo= userListService.getUserInfo2(id);
		request.setAttribute("userInfo", userInfo);
		
		forward.setPath("");
		
		return forward;
	}

}
