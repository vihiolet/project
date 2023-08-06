package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.UserListService;
import svc.UserReviewService;
import vo.ActionForward;
import vo.ReviewBean;
import vo.UserBean;

public class UserReviewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward= new ActionForward();		
		HttpSession session= request.getSession();
		String id= (String)session.getAttribute("id");
		UserListService userListService= null;
		UserBean userInfo= null;		
		userListService= new UserListService();
		userInfo= userListService.getUserInfo2(id);
		request.setAttribute("userInfo", userInfo);

		UserReviewService userReviewService = new UserReviewService();
		ArrayList<Object> myReview = new ArrayList<Object>();
		myReview= userReviewService.getMyReview(id);
		request.setAttribute("myReview", myReview);
		
		forward.setPath("/front/UserReviewList.jsp");
		
		return forward;
	}

}
