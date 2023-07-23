package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProViewService;
import vo.ActionForward;
import vo.AdminProBean;
import vo.ReviewBean;

public class ProViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProViewService proViewService= new ProViewService();
		
		int pro_code= Integer.parseInt(request.getParameter("pro_code"));
		AdminProBean pro= proViewService.getProView(pro_code); 	
		request.setAttribute("pro", pro);	
		
		ReviewBean MaxReviCount= proViewService.getReviewCount(pro_code);
		request.setAttribute("MaxReviCount", MaxReviCount);
		
		int tit1Count= proViewService.getTit1Count(pro_code);
		request.setAttribute("tit1Count", tit1Count);
		
		int tit2Count= proViewService.getTit2Count(pro_code);
		request.setAttribute("tit2Count", tit2Count);
		
		int tit3Count= proViewService.getTit3Count(pro_code);
		request.setAttribute("tit3Count", tit3Count);
		
		ActionForward forward= new ActionForward();	
		forward.setPath("/front/Pro_view.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
