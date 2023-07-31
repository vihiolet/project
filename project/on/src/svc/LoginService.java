package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.LoginDAO;
import vo.UserBean;

public class LoginService {	
	
	public String getSaltById(String id) {
		LoginDAO loginDAO= LoginDAO.getInstance();
		Connection con= getConnection();
		loginDAO.setConnection(con);
		String salt= loginDAO.selecSaltById(id);
		close(con);
		return salt;
	}

	public UserBean getLoginUser(String id, String pass) {
		LoginDAO loginDAO= LoginDAO.getInstance();
		Connection con= getConnection();
		loginDAO.setConnection(con);
		UserBean loginMember= loginDAO.selectLoginMember(id, pass);
		close(con);
		return loginMember;
	}
}
