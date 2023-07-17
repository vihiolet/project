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
		String[] pro_codeArr= request.getParameterValues("pro_codeArr");
		int[] intProCodeArr= null;
		if(pro_codeArr != null) {
			intProCodeArr= new int[pro_codeArr.length];
			for(int i=0; i< pro_codeArr.length; i++) {
				intProCodeArr[i]= Integer.parseInt(pro_codeArr[i]);
			}			
		}
		
		String nowPage= request.getParameter("page");
		AdminProDeleteService actionProDeleteService = new AdminProDeleteService();
		boolean isDelSuccess= actionProDeleteService.removePro(intProCodeArr);
		if(isDelSuccess) {
			forword= new ActionForward();
			forword.setPath("adminKey.ke?page=" + nowPage);
		}		
		return forword;
	}

}
