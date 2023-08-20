package action;

import java.io.PrintWriter;
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
		ActionForward forward= null;
		String id= (String)session.getAttribute("id");
		
		AdminEmpBean empInfo= null;
		AdminInfoService adminInfoService= new AdminInfoService();

		//관리자 계정인지 확인
		ArrayList<String> empIdList = new ArrayList<String>();
		empIdList = adminInfoService.getEmp_idList();		
		
		//전체 후기 목록 저장할 객체
		ArrayList<ReviewBean> articleList= new ArrayList<ReviewBean>();
		int page= 1;		
		//한 페이지에 출력할 상품 최대 개수(페이지 개수 관계 X)
		int limit= 6;
		
		if(request.getParameter("page") != null) {
			page= Integer.parseInt(request.getParameter("page"));
		}
		
		AdminProListService adminProListService= new AdminProListService();
		
		//총 상품 개수
		int listCount= adminProListService.getReviewCount();
		//(한 페이지에 나올)총 상품 저장
		articleList= adminProListService.getReviewList(page, limit);
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
		
		//관리자 계정 확
		if(id == null) {
			forward= new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/adminLoginForm.ur");
		}else if(id != null){			
			for(int i=0; i < empIdList.size(); i++) {				
				if(id.equals(empIdList.get(i).toString())) {
					adminInfoService= new AdminInfoService();
					empInfo= adminInfoService.getUserInfo(id);
					request.setAttribute("empInfo", empInfo);
					forward= new ActionForward();
					forward= new ActionForward();
					forward.setPath("/admin/admin_review.jsp");
				}		
			}
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('관리자 계정으로 로그인하세요.');");
			out.println("location.href='adminLoginForm.ur';");
			out.println("</script>");
		}		
		
		return forward;
	}

}
