package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminInfoService;
import svc.AdminKeyListService;
import vo.ActionForward;
import vo.AdminEmpBean;
import vo.KeywordBean;
import vo.PageInfo;

public class AdminKeyListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session= request.getSession();
		String id= (String)session.getAttribute("id");
		AdminEmpBean empInfo= null;
		//empInfo 객체 생성
		AdminInfoService adminInfoService= new AdminInfoService();
		empInfo= adminInfoService.getUserInfo(id);
		request.setAttribute("empInfo", empInfo);
		
		ArrayList<KeywordBean> keywordList= new ArrayList<KeywordBean>();
		int page= 1;
		int limit= 14;
		if(request.getParameter("page") != null) {
			page= Integer.parseInt(request.getParameter("page"));
		}
		
		AdminKeyListService adminKeyListService = new AdminKeyListService();
		
		int listCount= adminKeyListService.getListCount();
		keywordList= adminKeyListService.getKeywordList(page, limit);

		int maxPage= (int)((double)listCount/limit + 0.95);
		//int startPage= ((page - 1)/limit) + limit + 1;
		int startPage= (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage= startPage + 10 - 1;
		
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
		forword.setPath("/admin/admin_keyword.jsp");
		
		return forword;
	}

}
