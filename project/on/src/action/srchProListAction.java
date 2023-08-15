package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.FrontProListService;
import svc.UserListService;
import vo.ActionForward;
import vo.AdminProBean;
import vo.PageInfo;
import vo.UserBean;

public class srchProListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 검색점으로 제품 검색
		ActionForward forward= new ActionForward();		
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
		int listCount= 0;
		int menu_code = Integer.parseInt(request.getParameter("menu_code"));
		
		String srch_nm= request.getParameter("srch_nm");
		//검색 테이터 null 체크
		if(srch_nm == null) {
			srch_nm = "";
		}
		String pro_nm= request.getParameter("pro_nm");		
		if(pro_nm == null) {
			pro_nm = "";	
		}		
		
		if(srch_nm != "") {
			//검색점으로 검색한 상품 개수
			listCount= frontProListService.selectMainSrchProCount(srch_nm);
			if(menu_code != 0) {		
				//검색점으로 검색한 상품 개수
				listCount= frontProListService.selectSrchProCount(srch_nm, menu_code);
				//(메뉴 코드 있음)총 상품 저장
				articleList= frontProListService.getSrchProList(page, limit, menu_code, srch_nm);		
				
			}else if(menu_code == 0) {
				//검색점으로 검색한 상품 개수
				listCount= frontProListService.selectMainSrchProCount(srch_nm);
				//(메뉴 코드 없음)총 상품 저장
				articleList= frontProListService.getMainSrchProList(page, limit, srch_nm);
			}
		}
		
		if(pro_nm != "") {			
			pro_nm= (String)session.getAttribute("pro_nm");
			if(menu_code != 0) {
				//제품명으로 검색한 상품 개수
				listCount= frontProListService.selectNameProCount(pro_nm, menu_code);
				//(메뉴 코드 있음)총 상품 저장
				articleList= frontProListService.getNameProList(page, limit, menu_code, pro_nm);				
			}else if(menu_code == 0) {
				//제품명으로 검색한 상품 개수
				listCount= frontProListService.selectMainNameProCount(pro_nm);
				//(메뉴 코드 없음)총 상품 저장
				articleList= frontProListService.getMainNameProList(page, limit, pro_nm);
			}
		}	
		
		//총 페이지 수
		int maxPage= (int)((double)listCount/limit + 0.95);
		//현재 페이지의 첫 페이지 수
		int startPage= (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
		//현재 페이지의 마지막 페이지 수
		int endPage= startPage + 5 - 1;
		
		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(page);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);		

		forward.setPath("/front/srchPro_List.jsp");
		
		return forward;
	}

}
