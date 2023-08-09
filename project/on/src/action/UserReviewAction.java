package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.UserListService;
import svc.UserReviewService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ReviewBean;
import vo.UserBean;

public class UserReviewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward= new ActionForward();		
		HttpSession session= request.getSession();
		String id= (String)session.getAttribute("id");
		UserListService userListService= new UserListService();
		UserBean userInfo= userListService.getUserInfo2(id);
		request.setAttribute("userInfo", userInfo);
		
		ArrayList<Object> myReview = new ArrayList<Object>();
		UserReviewService userReviewService = new UserReviewService();
		
		int page= 1;
		int limit= 4;
		//조회할 페이지로 page 변수 초기화
		if(request.getParameter("page") != null) {
			page= Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount= userReviewService.getReviewCount(id);
		myReview= userReviewService.getMyReview(id, page, limit);
		
		int maxPage=   (int)((double)listCount/limit + 0.95);
		int startPage= (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage= startPage + 10 - 1;
		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo= new PageInfo();
		pageInfo.setPage(page);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("myReview", myReview);
		
		forward.setPath("/front/UserReviewList.jsp");
		
		return forward;
	}

}
