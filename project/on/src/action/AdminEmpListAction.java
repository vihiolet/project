package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;
import vo.AdminEmpBean;

public class AdminEmpListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<AdminEmpBean> adminEmpBean= new ArrayList<AdminEmpBean>();
		int page= 1;
		int limit= 10;
		if(request.getParameter("page") != null) {
			page= Integer.parseInt(request.getParameter("page"));
		}
		
		
		
		return null;
	}

}
