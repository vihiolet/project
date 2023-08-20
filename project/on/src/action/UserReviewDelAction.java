package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.UserDelService;
import vo.ActionForward;

public class UserReviewDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		String[] strCodeArr= request.getParameterValues("review_codeArr");
		int[] intCodeArr= null;
		if(strCodeArr != null) {
			intCodeArr= new int[strCodeArr.length];
			for(int i=0; i< strCodeArr.length; i++) {
				intCodeArr[i]= Integer.parseInt(strCodeArr[i]);
			}	
		}
		System.out.println(intCodeArr);
		String nowPage= request.getParameter("page");
		UserDelService userDelService= new UserDelService();
		boolean isDelSuccess= userDelService.removeReview(intCodeArr);
		if(isDelSuccess) {
			forward= new ActionForward();
			forward.setPath("adminProList.pr?page=" + nowPage);
		}
		
		return forward;
	}

}
