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
		
		//int reviewCount= proViewService.getReviewCount(pro_code);
		ReviewBean review= proViewService.getReviewInfo(pro_code);
				
		request.setAttribute("review", review);
		
		ActionForward forward= new ActionForward();	
		forward.setPath("/front/Pro_view.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
