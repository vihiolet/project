package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminInfoService;
import svc.KeywordModifyService;
import vo.ActionForward;
import vo.AdminEmpBean;
import vo.KeywordBean;

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
					forward= new ActionForward();
					forward.setPath("/admin/admin_key_modi.jsp");
					forward.setRedirect(false);
					break;
				}else {
					response.setContentType("text/html;charset=euc-kr");
					PrintWriter out= response.getWriter();
					out.println("<script>");
					out.println("alert('관리자 계정으로 로그인하세요.');");
					out.println("location.href='adminLoginForm.ur';");
					out.println("</script>");
				}				
			}
		}
		return forward;		
	}

}
