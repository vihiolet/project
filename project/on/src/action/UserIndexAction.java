package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.Session;

import svc.UserListService;
import vo.ActionForward;
import vo.UserBean;

public class UserIndexAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		String id= (String)session.getAttribute("id");
		ActionForward forward= null;
		UserListService userListService= null;
		ArrayList<UserBean> userInfo= null;
		
		if(id == null) {
			forward= new ActionForward();
			forward.setPath("./login.ur");
			forward.setRedirect(true);
		}else {
			forward= new ActionForward();
			userListService= new UserListService();
			userInfo= userListService.getUserInfo(id);
			request.setAttribute("userInfo", userInfo);
			forward.setPath("./index.jsp");
		}		
		return forward;
	}

}
