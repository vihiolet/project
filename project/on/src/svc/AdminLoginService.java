package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UsersDAO;
import vo.AdminEmpBean;

public class AdminLoginService {

	public boolean login(AdminEmpBean emp) {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		boolean loginResult= false;
		String loginId= userDAO.selectAdminLoginId(emp);
		if(loginId != null) {
			loginResult= true;
		}
		return loginResult;
	}

}
