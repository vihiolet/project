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
		String[] strSrch_code= request.getParameterValues("srch_code");
		int[] intSrch_code= null;
		if(intSrch_code != null) {
			intSrch_code= new int[strSrch_code.length];
			for(int i=0; i< strSrch_code.length; i++) {
				intSrch_code[i]= Integer.parseInt(strSrch_code[i]);
			}			
		}
		
		String nowPage= request.getParameter("page");
		AdminKeyDeleteService actionKeyDeleteService = new AdminKeyDeleteService();
		boolean isDelSuccess= actionKeyDeleteService.removeKeyword(intSrch_code);
		if(isDelSuccess) {
			forword= new ActionForward();
			forword.setPath("adminKey.ke?page=" + nowPage);
		}		
		return forword;
	}	
}
