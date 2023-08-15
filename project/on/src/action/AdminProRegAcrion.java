package action;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.AdminInfoService;
import svc.AdminProModifyService;
import svc.AdminProRegService;
import vo.ActionForward;
import vo.AdminEmpBean;
import vo.AdminProBean;

public class AdminProRegAcrion implements Action{

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
		
		AdminProBean adminProBean = new AdminProBean();
		String realFolder= "";
		String imageFolder = "/images";
		int fileSize= 5*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder= context.getRealPath(imageFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize,  "UTF-8", new DefaultFileRenamePolicy());
		adminProBean.setCreate_id(multi.getParameter("create_id"));		
		adminProBean.setPro_nm(multi.getParameter("pro_nm"));
		adminProBean.setMenu_code(Integer.parseInt(multi.getParameter("menu_code")));		
		adminProBean.setPro_company(multi.getParameter("pro_company"));
		adminProBean.setPro_img(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		adminProBean.setSrch_code1(Integer.parseInt(multi.getParameter("srch_code1")));
		
		adminProBean.setCreate_id(multi.getParameter("create_id"));
		AdminProRegService adminProRegService = new AdminProRegService();
		boolean isSuccess = adminProRegService.registArticle(adminProBean); //등록 성공하면 true 실패하면 false
		
		if(!isSuccess) {	//상품 등록 실패
			response.setContentType("text/html;charset=UTF-8");		//등록실패 한글 인코딩 (테스트 해보기) - 없으면 ???으로 뜬다
			PrintWriter out = response.getWriter();
			out.println("<script>");		//alert 창이 어디서 뜨는지 확인하기 새 창으로 화전되고 뜨는지 해당 페이지에서 뜨는지 - 새창에서 뜸
			out.println("alert('등록실패')");
			out.println("</script>");
			out.println("history.back();");
		}else {				//상품 등록 성공
			forward = new ActionForward();	//24행에 null로 초기화 했음
			forward.setRedirect(true);		//redirect 방식 주소 변화 있음
			forward.setPath("adminProList.pr");
		}		
		
		return forward;		//ActionForward 인스턴스 즉 ActionForward 객체를 리턴한다
	}

}
