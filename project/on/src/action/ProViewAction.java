package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ProViewService;
import svc.UserListService;
import vo.ActionForward;
import vo.AdminProBean;
import vo.ReviewBean;
import vo.UserBean;

public class ProViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session= request.getSession();
		String id= (String)session.getAttribute("id");
		UserListService userListService= null;
		UserBean userInfo= new UserBean();
		
		if(id != null) {			
			
			userListService= new UserListService();
			userInfo= userListService.getUserInfo2(id);
			request.setAttribute("userInfo", userInfo);

		}
		
		ProViewService proViewService= new ProViewService();
		
		int pro_code= Integer.parseInt(request.getParameter("pro_code"));
		AdminProBean pro= proViewService.getProView(pro_code); 	
		request.setAttribute("pro", pro);	

		int reviewCount= proViewService.getReviewCount();
		request.setAttribute("reviewCount", reviewCount);
		
		ReviewBean MaxReviCount= proViewService.getReviewCount(pro_code);
		request.setAttribute("MaxReviCount", MaxReviCount);

		ReviewBean allReview= proViewService.getselectReview(pro_code);
		request.setAttribute("allReview", allReview);
		
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
