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
		ServletContext context= request.getServletContext();
		
		keywordBean.setSrch_name(request.getParameter("srch_name"));
		keywordBean.setCreate_id(Integer.parseInt(request.getParameter("create_id")));
		
		KeywordRegService keywordRegService= new KeywordRegService();
		boolean isRegSuccess= keywordRegService.registKeyword(keywordBean);
		
		if(isRegSuccess) {
			forward= new ActionForward();
			forward.setPath("/admin/admin_keyword.jsp");	
		}				
		
		return forward;
	}

}