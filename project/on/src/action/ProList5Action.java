package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminProListService;
import svc.FrontProListService;
import svc.UserListService;
import vo.ActionForward;
import vo.AdminProBean;
import vo.PageInfo;
import vo.UserBean;

public class ProList5Action implements Action {

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
		//전체 상품 목록 저장할 객체
		ArrayList<AdminProBean> articleList= new ArrayList<AdminProBean>();
		int page= 1;		
		//한 페이지에 출력할 상품 최대 개수(페이지 개수 관계 X)
		int limit= 8;
		
		if(request.getParameter("page") != null) {
			page= Integer.parseInt(request.getParameter("page"));
		}
		
		FrontProListService frontProListService= new FrontProListService();
		int menu_code= Integer.parseInt(request.getParameter("menu_code"));
		//총 상품 개수
		int listCount= frontProListService.selectList4Count();		
		//(한 페이지에 나올)총 상품 저장
		articleList= frontProListService.getArticleList(page, limit, menu_code);
		//총 페이지 수
		int maxPage= (int)((double)listCount/limit + 0.95);
		//현재 페이지의 첫 페이지 수
		int startPage= (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
		//현재 페이지의 마지막 페이지 수
		int endPage= startPage + 10 - 1;
		
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
		forward.setPath("/front/Pro_List5.jsp");		
		
		return forward;
	}

}
