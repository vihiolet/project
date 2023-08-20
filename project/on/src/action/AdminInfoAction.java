package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AdminModiService;
import svc.UserListService;
import svc.AdminInfoService;
import svc.AdminLoginService;
import vo.ActionForward;
import vo.AdminEmpBean;

public class AdminInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		String id= (String)session.getAttribute("id");
		AdminInfoService adminInfoService= null;
		AdminEmpBean empInfo= null;
		
		if(id != null) {			
			adminInfoService= new AdminInfoService();
			empInfo= adminInfoService.getUserInfo(id);
			request.setAttribute("empInfo", empInfo);
		}
		
		ActionForward forward= null;
		SHA256Util sha256util= null;

		//내 정보 수정 service
		AdminModiService adminModiService= new AdminModiService();
		empInfo = new AdminEmpBean();
		String oldName= empInfo.getEmp_name();
		
		String newName= request.getParameter("name");
		String passwd= request.getParameter("newPasswd");
		String salt= null;
		String passwdSalt= null;
		
		//수정된 내용이 입력되었는지 확인
		boolean modiChk= true;
		
		//비번 input null 체크
		if(passwd == null) passwd= "";
				
		//비번, 이름 모두 수정
		if(!newName.equals(oldName) && (passwd != "")){
			sha256util= new SHA256Util();
			salt= sha256util.getSalt();
			passwdSalt= sha256util.getEncrypt(passwd, salt);

			modiChk= false;
			boolean upateSuccess= adminModiService.adminNamePassModi(id, newName, passwdSalt, salt);
			
			if(upateSuccess){
				//새로 로그인
				session.removeAttribute("id");
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/adminLoginForm.ur");
			}else{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out= response.getWriter();
				out.println("<script>");
				out.println("alert('수정 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		}
		//비번만 수정
		if(newName.equals(oldName) && (passwd != "")){
			sha256util= new SHA256Util();
			salt= sha256util.getSalt();
			passwdSalt= sha256util.getEncrypt(passwd, salt);

			modiChk= false;
			boolean upateSuccess= adminModiService.adminPassModi(id, passwdSalt, salt);
			
			if(upateSuccess){
				session= request.getSession();
				session.removeAttribute("id");
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/adminLoginForm.ur");
			}else{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out= response.getWriter();
				out.println("<script>");
				out.println("alert('수정 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		}
		//이름만 수정
		if(!newName.equals(oldName) && (passwd == "")){

			modiChk= false;
			boolean upateSuccess= adminModiService.adminNameModi(id, newName);
			if(upateSuccess){
				forward= new ActionForward();
				adminInfoService= new AdminInfoService();
				empInfo= adminInfoService.getUserInfo(id);
				request.setAttribute("empInfo", empInfo);
				forward.setPath("/admin/admin_Info.jsp");
			}else{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out= response.getWriter();
				out.println("<script>");
				out.println("alert('수정 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		}
		
		//수정할 입력 값이 없을 때
		if(modiChk){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 정보를 입력하세요')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
