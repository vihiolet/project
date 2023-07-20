package svc;

import static db.JdbcUtil.*;
import dao.UsersDAO;
import vo.UserBean;
import java.sql.Connection;

public class UserDelService {

	public boolean deleteUser(String id) {
		boolean deleteSuccess= false;
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		int delecteCount= userDAO.delecteUsers(id);
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