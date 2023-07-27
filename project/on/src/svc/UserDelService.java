package svc;

import static db.JdbcUtil.*;
import dao.UsersDAO;
import vo.UserBean;
import java.sql.Connection;

public class UserDelService {

	public boolean deleteUser(String id, String passwdSalt) {
		
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		
		boolean deleteSuccess= false;		
		int delecteCount= userDAO.delecteUsers(id, passwdSalt);
		
		if(delecteCount > 0) {
			deleteSuccess= true;
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return deleteSuccess;
	}

}
