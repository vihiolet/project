package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UsersDAO;
import vo.AdminEmpBean;

public class AdminInfoService {

	public AdminEmpBean getUserInfo(String id) {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		
		AdminEmpBean empInfo= new AdminEmpBean();
		empInfo= userDAO.selectUserInfo(id);
		
		close(con);		
		return empInfo;
	}

}
