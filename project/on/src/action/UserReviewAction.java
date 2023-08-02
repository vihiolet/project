package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

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
		ArrayList myReview= new ArrayList();
		myReview= userReviewService.getMyReview(id);
		request.setAttribute("myReview", myReview);
		
		return forward;
	}

}
