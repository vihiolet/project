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
		String[] strCodeArr= request.getParameterValues("srch_codeArr");
		int[] intCodeArr= null;
		if(strCodeArr != null) {
			intCodeArr= new int[strCodeArr.length];
			for(int i=0; i< strCodeArr.length; i++) {
				intCodeArr[i]= Integer.parseInt(strCodeArr[i]);
			}			
		}
		
		String nowPage= request.getParameter("page");
		AdminKeyDeleteService actionKeyDeleteService = new AdminKeyDeleteService();
		boolean isDelSuccess= actionKeyDeleteService.removeKeyword(intCodeArr);
		if(isDelSuccess) {
			forword= new ActionForward();
			forword.setPath("adminKey.ke?page=" + nowPage);
		}		
		return forword;
	}	
}
