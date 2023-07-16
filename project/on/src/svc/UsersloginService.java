package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UsersDAO;
import vo.UserBean;

public class UsersloginService {

	public String LoginSetSalt(String id) {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		String LoginSalt= userDAO.selectLoginSalt(id);
		return LoginSalt;
	}

	public boolean login(UserBean user) {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		boolean loginResult= false;
		String loginId= userDAO.selectLoginId(user);
		if(loginId != null) {
			loginResult= true;
		}
		return loginResult;
	}

}
