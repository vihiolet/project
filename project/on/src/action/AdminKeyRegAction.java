package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.KeywordRegService;
import vo.ActionForward;
import vo.KeywordBean;

public class AdminKeyRegAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward= null;
		KeywordBean keywordBean= new KeywordBean();
		
		keywordBean.setSrch_name(request.getParameter("srch_name").trim());
		keywordBean.setCreate_id(request.getParameter("create_id"));
		keywordBean.setRemark(request.getParameter("remark"));			
		
		KeywordRegService keywordRegService= new KeywordRegService();
		boolean isRegSuccess= keywordRegService.registKeyword(keywordBean);		
		
		if(isRegSuccess) {						//commit
			forward= new ActionForward();
			forward.setRedirect(true);
			forward.setPath("adminKey.ke");	
		}else { 								//rollback		input에 이미 빈값이라도 들어있기 때문에 값이 안 넘어가서 rollback은 생기지 않지만 좀 더 분석 필요 	
			response.setContentType("text/html; charset=utf-8");
		     PrintWriter out = response.getWriter();
		     out.println("<script>");
		     out.println("alert('등록실패');");	//등록실패 경고창을 목록페이지에서 뜨게 지금은 빈 페이지로 이동하고 경고창이 뜬 뒤 뒤로 돌아온다 새로고침도 안 됨
		     out.println("history.back()");
		     out.println("</script>");
		}		
		return forward;
	}
}
