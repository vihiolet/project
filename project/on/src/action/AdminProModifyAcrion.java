package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminProModifyService;
import vo.ActionForward;
import vo.AdminProBean;

public class AdminProModifyAcrion implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		HttpSession session= request.getSession();
		String id= (String)session.getAttribute("id");
		boolean isModifySuccess= false;
		int pro_code= Integer.parseInt(request.getParameter("pro_code"));
		AdminProBean proBean= new AdminProBean();
		AdminProModifyService adminProModifyService = new AdminProModifyService();
		
		//관리자 계정인지 확인
		boolean isAdminUser= adminProModifyService.isKeyWriter(id);
		if(!isAdminUser) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('관리자 계정으로 로그인하세요');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			proBean.setPro_nm(request.getParameter("pro_nm"));
			isModifySuccess= adminProModifyService.modifyPro(proBean);
			
			if(isModifySuccess) {
				forward= new ActionForward();
				forward.setPath("");
				forward.setRedirect(true);
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out= response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		return forward;		
	}

}
