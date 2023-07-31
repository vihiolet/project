package action;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewWriteProService;
import svc.UserListService;
import vo.ActionForward;
import vo.ReviewBean;
import vo.UserBean;
import vo.PageInfo;

public class ReviewRegAction implements Action{
	@Override
	   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      
			HttpSession session= request.getSession();
			String id= (String)session.getAttribute("id");
			
		   ActionForward forward= null;
		   ReviewBean reviewBean= null;
		   ServletContext context= request.getServletContext();
		   reviewBean= new ReviewBean();	   
		   
		   reviewBean.setPro_code(Integer.parseInt(request.getParameter("pro_code")));	   	   
		   reviewBean.setTit_fg(Integer.parseInt(request.getParameter("tit_fg")));
		   reviewBean.setSub1_fg(Integer.parseInt(request.getParameter("sub1_fg")));
		   reviewBean.setSub2_fg(Integer.parseInt(request.getParameter("sub2_fg")));
		   reviewBean.setSub3_fg(Integer.parseInt(request.getParameter("sub3_fg")));
		   reviewBean.setCreate_id(request.getParameter("create_id"));
		   ReviewWriteProService reviewWriteProService= new ReviewWriteProService();
		   boolean isWriteSuccess= reviewWriteProService.registArticle(reviewBean);
		   
		   if(!isWriteSuccess) {
		      response.setContentType("text/html; charset=utf-8");
		      PrintWriter out = response.getWriter();
		      out.println("<script>");
		      out.println("alert('등록실패');");
		      out.println("history.back()");
		      out.println("</script>");
		   }else { 
		      response.setContentType("text/html; charset=utf-8");
		      PrintWriter out = response.getWriter();
		      out.println("<script>");
		      out.println("alert('등록되었습니다.');");
		      out.println("opener.location.reload();");		//부모창 새로고침
		      out.println("self.close();");					//팝업 닫기
		      out.println("</script>");
	
		   }
	
		   return forward;
	   
	}
}
