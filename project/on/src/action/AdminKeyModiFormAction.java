package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminInfoService;
import svc.AdminProModifyService;
import svc.ProViewService;
import svc.UserListService;
import vo.ActionForward;
import vo.AdminEmpBean;
import vo.AdminProBean;
import vo.UserBean;

public class AdminKeyModiFormAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session= request.getSession();
		ActionForward forward= null;	
		String id= (String)session.getAttribute("id");
		
		AdminInfoService adminInfoService= null;
		AdminEmpBean empInfo= null;
		
		if(id != null) {					
			adminInfoService= new AdminInfoService();
			empInfo= adminInfoService.getUserInfo(id);
			request.setAttribute("empInfo", empInfo);
		}

		int srch_code= Integer.parseInt(request.getParameter("srch_code"));
		KeywordBean keyword= new KeywordBean();		
		KeywordModifyService keywordModifyService = new KeywordModifyService();
		
		keyword= keywordModifyService.getKeyword(srch_code); 	
		request.setAttribute("keyword", keyword);
		
		forward= new ActionForward();
		forward.setPath("/admin/admin_key_modi.jsp");
		forward.setRedirect(false);

		
		//관리자 계정인지 확인
		/*boolean isAdminUser= adminProModifyService.isKeyWriter(id);
		if(!isAdminUser) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('관리자 계정으로 로그인하세요');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			//25행 ~
		}*/
		return forward;		
	}

}
