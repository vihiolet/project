package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.KeywordModifyService;
import vo.ActionForward;
import vo.KeywordBean;

public class AdminKeyModifyAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		HttpSession session= request.getSession();
		String id= (String)session.getAttribute("id");
		boolean isModifySuccess= false;
		
		
		KeywordBean keyword= new KeywordBean();
		
		KeywordModifyService keywordModifyService = new KeywordModifyService();
		
		//관리자 계정인지 확인
		/*boolean isAdminUser= keywordModifyService.isKeyWriter(id);
		if(!isAdminUser) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('관리자 계정으로 로그인하세요');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			keyword.setSrch_name(request.getParameter("srch_name"));
			
			isModifySuccess= keywordModifyService.modifyKeyword(keyword);
			
			if(isModifySuccess) {
				forward= new ActionForward();
				forward.setPath("adminKey.ke");
				forward.setRedirect(true);
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out= response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back();");
				out.println("</script>");
			}
		}*/
		
		int srch_code = Integer.parseInt(request.getParameter("srch_code"));
		String srch_name = request.getParameter("srch_name");
		String remark = request.getParameter("remark");
		keyword.setSrch_code(srch_code);
		keyword.setSrch_name(srch_name);
		keyword.setRemark(remark);

		isModifySuccess= keywordModifyService.modifyKeyword(keyword);
		
		if(isModifySuccess) {
			forward= new ActionForward();
			forward.setPath("adminKey.ke");
			forward.setRedirect(true);
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('수정실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;		
	}

}
