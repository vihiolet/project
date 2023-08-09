package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminInfoService;
import svc.AdminKeyListService;
import svc.AdminProListService;
import svc.FrontProListService;
import vo.ActionForward;
import vo.AdminEmpBean;

public class AdminMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		
		String id= (String)session.getAttribute("id");
		
		ActionForward forward= new ActionForward();		
		AdminInfoService adminInfoService= new AdminInfoService();
		AdminEmpBean empInfo= adminInfoService.getUserInfo(id);
		
		request.setAttribute("empInfo", empInfo);
		
		//제품 개수
		AdminProListService adminProListService= new AdminProListService();
		int proCount= adminProListService.getListCount();	
		request.setAttribute("proCount", proCount);
		
		//후기 개수
		int reviewCount= adminProListService.getReviewCount();
		request.setAttribute("reviewCount", reviewCount);
		
		//제품(음식) 개수
		FrontProListService frontProListService= new FrontProListService();
		int menu1Count= frontProListService.selectList1Count();
		request.setAttribute("menu1Count", menu1Count);
		
		//제품(옷) 개수
		int menu2Count= frontProListService.selectList2Count();
		request.setAttribute("menu2Count", menu2Count);
		
		//제품(옷) 개수
		int menu3Count= frontProListService.selectList3Count();
		request.setAttribute("menu3Count", menu3Count);
		
		//제품(옷) 개수
		int menu4Count= frontProListService.selectList4Count();
		request.setAttribute("menu4Count", menu4Count);
		
		//제품(옷) 개수
		int menu5Count= frontProListService.selectList5Count();
		request.setAttribute("menu5Count", menu5Count);
		
		//검색점 개수
		AdminKeyListService adminKeyListService = new AdminKeyListService();
		int keyCount= adminKeyListService.getListCount();
		request.setAttribute("keyCount", keyCount);
		
		
		
		//empInfo 객체 생성
		
		
		//System.out.println(empInfo.getEmp_name());
		
		forward.setPath("./admin_main.jsp");
		return forward;
	}

}
