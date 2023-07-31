package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.AdminInfoService;
import svc.AdminProModifyService;
import svc.ProViewService;
import svc.UserListService;
import vo.ActionForward;
import vo.AdminEmpBean;
import vo.AdminProBean;
import vo.UserBean;

public class AdminProModiAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminProModifyService adminProModifyService = new AdminProModifyService();
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
		
		AdminProBean pro= new AdminProBean();			
		ServletContext context = request.getServletContext();
		String imageFolder = "/images";
		String realFolder= context.getRealPath(imageFolder);
		int fileSize= 5*1024*1024;
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize,  "UTF-8", new DefaultFileRenamePolicy());		
		
		int pro_code= Integer.parseInt(multi.getParameter("pro_code"));	
		
		pro.setPro_nm(multi.getParameter("pro_nm"));
		pro.setMenu_code(Integer.parseInt(multi.getParameter("menu_code")));		
		pro.setPro_company(multi.getParameter("pro_company"));
		pro.setPro_img(multi.getParameter("pro_img"));
		pro.setSrch_code1(Integer.parseInt(multi.getParameter("srch_code1")));
		pro.setSrch_nm1(multi.getParameter("srch_nm1"));	//테스트하고 ~3까지 추가하기
		request.setAttribute("pro", pro);
		
		boolean isModifySuccess= false;
		isModifySuccess= adminProModifyService.modifyPro(pro, pro_code);
		
		if(isModifySuccess) {
			forward= new ActionForward();
			forward.setPath("adminProList.pr");
			forward.setRedirect(false);
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
