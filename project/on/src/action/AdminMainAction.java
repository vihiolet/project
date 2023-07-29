package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminInfoService;
import vo.ActionForward;
import vo.AdminEmpBean;

public class AdminMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		AdminEmpBean empInfo= null;
		String id= (String)session.getAttribute("id");
		ActionForward forward= new ActionForward();
		
		AdminInfoService adminInfoService= new AdminInfoService();
		empInfo= adminInfoService.getUserInfo(id);
		request.setAttribute("empInfo", empInfo);
		
		forward.setPath("./admin_main.jsp");
		return forward;
	}

}
