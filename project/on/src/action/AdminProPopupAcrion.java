package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AdminKeyListService;
import vo.ActionForward;
import vo.KeywordBean;
import vo.PageInfo;

public class AdminProPopupAcrion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<KeywordBean> keywordList= new ArrayList<KeywordBean>();
		int page= 1;
		int limit= 5;
		if(request.getParameter("page") != null) {
			page= Integer.parseInt(request.getParameter("page"));
		}
		
		AdminKeyListService adminKeyListService = new AdminKeyListService();
		
		int listCount= adminKeyListService.getListCount();
		keywordList= adminKeyListService.getKeywordList(page, limit);
		
		int maxPage= (int)((double)listCount/limit + 0.9);
		//int startPage= ((page - 1)/limit) + limit + 1;
		int startPage= (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage= startPage + limit - 1;
		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(page);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("keywordList", keywordList);
		ActionForward forword= new ActionForward();
		forword.setPath("/admin/keyword_popup.jsp");
		
		return forword;
	}

}
