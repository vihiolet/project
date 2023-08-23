package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MainService;
import svc.UserListService;
import vo.ActionForward;
import vo.AdminProBean;
import vo.UserBean;

public class UserIndexAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session= request.getSession();
		
		//loginAction에서 session.setAttribute("id", user.getId());
		String id= (String)session.getAttribute("id");
		
		ActionForward forward= new ActionForward();
		UserListService userListService= new UserListService();
		UserBean userInfo= new UserBean();
		
		if(id != null) {			
			
			userListService= new UserListService();
			userInfo= userListService.getUserInfo2(id);
			request.setAttribute("userInfo", userInfo);
		}
		
		MainService mainService = new MainService();	
		AdminProBean mainpro1= new AdminProBean();
		AdminProBean mainpro3= new AdminProBean();
		AdminProBean mainpro4= new AdminProBean();
		AdminProBean mainpro5= new AdminProBean();
		
		int menu_code1= 1, menu_code3= 3, menu_code4= 4, menu_code5= 5;
		
		mainpro1= mainService.getPro1(menu_code1);
		mainpro3= mainService.getPro3(menu_code3);
		mainpro4= mainService.getPro4(menu_code4);
		mainpro5= mainService.getPro5(menu_code5);
		
		request.setAttribute("mainpro1", mainpro1);
		request.setAttribute("mainpro3", mainpro3);
		request.setAttribute("mainpro4", mainpro4);
		request.setAttribute("mainpro5", mainpro5);
		
		//조건 검색점
		AdminProBean mainProSrch1= new AdminProBean();
		AdminProBean mainProSrch2= new AdminProBean();
		AdminProBean mainProSrch3= new AdminProBean();
		AdminProBean mainProSrch4= new AdminProBean();
		int srch_code= 288;

		mainProSrch1= mainService.getProSrch1(srch_code);
		mainProSrch2= mainService.getProSrch2(srch_code);
		mainProSrch3= mainService.getProSrch3(srch_code);
		mainProSrch4= mainService.getProSrch4(srch_code);
		
		request.setAttribute("mainProSrch1", mainProSrch1);
		request.setAttribute("mainProSrch2", mainProSrch2);
		request.setAttribute("mainProSrch3", mainProSrch3);
		request.setAttribute("mainProSrch4", mainProSrch4);
		
		forward.setPath("./index.jsp");
				
		return forward;
	}

}
