package action;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ReviewWriteProService;

//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import vo.ActionForward;
import vo.ReviewBean;
import vo.PageInfo;

public class ReviewWriteProAction implements Action{
	@Override
	   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      
	   ActionForward forward = null;
	   ReviewBean reviewBean = null;
	   ServletContext context = request.getServletContext();
	   reviewBean = new ReviewBean();
	   
//	   reviewBean.setTest(request.getParameter("test"));
//	   System.out.println(request.getParameter("test"));
	   
	   reviewBean.setPro_code(Integer.parseInt(request.getParameter("pro_code")));	   	   
	   reviewBean.setTit_fg(Integer.parseInt(request.getParameter("tit_fg")));
	   System.out.println(Integer.parseInt(request.getParameter("tit_fg")));
	   reviewBean.setSub1_fg(Integer.parseInt(request.getParameter("sub1_fg")));
	   System.out.println(Integer.parseInt(request.getParameter("sub1_fg")));
	   reviewBean.setSub2_fg(Integer.parseInt(request.getParameter("sub2_fg")));
	   System.out.println(Integer.parseInt(request.getParameter("sub2_fg")));
	   reviewBean.setSub3_fg(Integer.parseInt(request.getParameter("sub3_fg")));
	   ReviewWriteProService reviewWriteProService = new ReviewWriteProService();
	   boolean isWriteSuccess = reviewWriteProService.registArticle(reviewBean);
	   
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
	      out.println("location.href='boardWritePro.on'");
	      out.println("</script>");

	   }
	   PageInfo pageInfo = new PageInfo();


	   return forward;
	   
	}
}
