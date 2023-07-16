package svc;

import static db.JdbcUtil.*;
import dao.UsersDAO;
import vo.UserBean;
import java.sql.Connection;

public class UsersJoinService {

	public boolean joinUsers(UserBean users) {
		boolean joinSuccess= false;
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		int insertCount= userDAO.insertAdminUsers(users);
		if(insertCount > 0) {
			joinSuccess= true;
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return joinSuccess;
	}

}
