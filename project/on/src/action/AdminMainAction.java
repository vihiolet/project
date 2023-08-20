package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminInfoService;
import svc.AdminKeyListService;
import svc.AdminProListService;
import svc.FrontProListService;
import vo.ActionForward;
import vo.AdminEmpBean;
import vo.PageInfo;

public class AdminMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		ActionForward forward= null;
		String id= (String)session.getAttribute("id");
		AdminEmpBean empInfo= null;
		//empInfo 객체 생성
		AdminInfoService adminInfoService= new AdminInfoService();

		//관리자 계정인지 확인
		ArrayList<String> empIdList = new ArrayList<String>();
		empIdList = adminInfoService.getEmp_idList();
		
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
					forward.setPath("admin_main.jsp");
				}		
			}
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('관리자 계정으로 로그인하세요.');");
			out.println("location.href='adminLoginForm.ur';");
			out.println("</script>");
		}
		
		
		AdminProListService adminProListService= new AdminProListService();
		
		//가입자 수
		adminInfoService= new AdminInfoService();
		int userCount= adminInfoService.getUserCount();	
		request.setAttribute("userCount", userCount);
		
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
				
		return forward;
	}

}
