package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminEmpListService;
import svc.AdminInfoService;
import svc.AdminKeyListService;
import vo.ActionForward;
import vo.AdminEmpBean;
import vo.PageInfo;

public class AdminEmpListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		String id= (String)session.getAttribute("id");
		AdminEmpBean empInfo= null;
		//empInfo 객체 생성
		AdminInfoService adminInfoService= new AdminInfoService();
		empInfo= adminInfoService.getUserInfo(id);
		request.setAttribute("empInfo", empInfo);
		
		//출력될 관리자 목록을 담을 객체 EmpList
		ArrayList<AdminEmpBean> EmpList= new ArrayList<AdminEmpBean>();
		
		//시작 페이지 34행으로 이어진다
		int page= 1;
		int limit= 14;
		//조회할 페이지로 page 변수 초기화
		if(request.getParameter("page") != null) {
			page= Integer.parseInt(request.getParameter("page"));
		}
		
		AdminEmpListService adminEmpListService = new AdminEmpListService();
		
		//출력될 목록 총 개수 가져오기
		int listCount= adminEmpListService.getListCount();
		//지정한 페이지에 출력될 관리자 목록 가져오기
		EmpList= adminEmpListService.getEmpList(page, limit);
		
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
		request.setAttribute("EmpList", EmpList);
		
		ActionForward forward= new ActionForward();
		forward.setPath("/admin/admin_emp.jsp");		
		
		return forward;
	}

}
