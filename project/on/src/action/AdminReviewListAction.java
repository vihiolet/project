package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminInfoService;
import svc.AdminProListService;
import vo.ActionForward;
import vo.AdminEmpBean;
import vo.ReviewBean;
import vo.PageInfo;

public class AdminReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session= request.getSession();	
		String id= (String)session.getAttribute("id");
		
		AdminInfoService adminInfoService= null;
		AdminEmpBean empInfo= null;
		
		if(id != null) {					
			adminInfoService= new AdminInfoService();
			empInfo= adminInfoService.getUserInfo(id);
			request.setAttribute("empInfo", empInfo);
		}
		//전체 상품 목록 저장할 객체
		ArrayList<ReviewBean> articleList= new ArrayList<ReviewBean>();
		int page= 1;		
		//한 페이지에 출력할 상품 최대 개수(페이지 개수 관계 X)
		int limit= 10;
		
		if(request.getParameter("page") != null) {
			page= Integer.parseInt(request.getParameter("page"));
		}
		
		AdminProListService adminProListService= new AdminProListService();
		
		//총 상품 개수
		int listCount= adminProListService.getListCount();		
		//(한 페이지에 나올)총 상품 저장
		articleList= adminProListService.getReviewList(page, limit);
		//총 페이지 수
		int maxPage= (int)((double)listCount/limit + 0.95);
		//현재 페이지의 첫 페이지 수
		int startPage= ((page - 1)/limit) * limit + 1;
		//현재 페이지의 마지막 페이지 수
		int endPage= startPage + limit - 1;
		
		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(page);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
		ActionForward forward= new ActionForward();
		forward.setPath("/admin/admin_review.jsp");		
		
		return forward;
	}

}
