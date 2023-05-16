package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AdminProListService;
import vo.ActionForward;
import vo.AdminProBean;

public class AdminProListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//전체 상품 목록 저장할 객체
		ArrayList<AdminProBean> articleList= new ArrayList<AdminProBean>();
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
		articleList= adminProListService.getArticleList(page, limit);
		//총 페이지 수
		int maxPage= (int)((double)listCount/limit+0.95);
		//
		int startPage= (((int)((double)page/10 + 0.9))-1) * 10 + 1;
		//
		int endPage= startPage + 10 - 1;
		return null;
	}

}
