package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.UserListService;
import vo.ActionForward;
import vo.AdminProBean;
import vo.ReviewBean;
import vo.UserBean;

public class MyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward= new ActionForward();		
		HttpSession session= request.getSession();
		String id= (String)session.getAttribute("id");
		UserListService userListService= null;
		UserBean userInfo= = new UserBean();;
		
		if(id != null) {			
			userListService= new UserListService();
			userInfo= userListService.getUserInfo2(id);
			request.setAttribute("userInfo", userInfo);	
		}
		
		ReviewBean reviewBean= new ReviewBean();
		int reviewCount= userListService.getReviewCount(id);
		reviewBean.setReviewCount(reviewCount);
		request.setAttribute("reviewBean", reviewBean);
		
		ArrayList<AdminProBean> myProReview= new ArrayList<AdminProBean>();
		myProReview= userListService.getMyReview(id);
		request.setAttribute("myProReview", myProReview);
				
		forward.setPath("/front/Mypage.jsp");
		
		return forward;
	}

}
