package action;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AdminKeyDeleteService;
import vo.ActionForward;

public class AdminKeyDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forword = null;
		int srch_code= Integer.parseInt(request.getParameter("srch_code"));
		String nowPage= request.getParameter("page");
		AdminKeyDeleteService actionKeyDeleteService = new AdminKeyDeleteService();
		boolean isDelSuccess= actionKeyDeleteService.removeKeyword(srch_code);
		if(isDelSuccess) {
			forword= new ActionForward();
			forword.setRedirect(true);
			//forword.setPath("adminKey.ke?page=" + nowPage);
			forword.setPath("adminKey.ke");
		}		
		return forword;
	}	
}
