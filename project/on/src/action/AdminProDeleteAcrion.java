package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AdminKeyDeleteService;
import svc.AdminProDeleteService;
import vo.ActionForward;

public class AdminProDeleteAcrion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forword = null;
		String[] strCodeArr= request.getParameterValues("menu_codeArr");
		int[] intCodeArr= null;
		if(strCodeArr != null) {
			intCodeArr= new int[strCodeArr.length];
			for(int i=0; i< strCodeArr.length; i++) {
				intCodeArr[i]= Integer.parseInt(strCodeArr[i]);
			}			
		}
		
		String nowPage= request.getParameter("page");
		AdminProDeleteService actionProDeleteService = new AdminProDeleteService();
		boolean isDelSuccess= actionProDeleteService.removePro(intCodeArr);
		if(isDelSuccess) {
			forword= new ActionForward();
			forword.setPath("adminKey.ke?page=" + nowPage);
		}		
		return forword;
	}

}
