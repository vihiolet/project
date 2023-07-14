package svc;

import static db.JdbcUtil.*;
import dao.AdminUsersDAO;
import vo.UserBean;
import java.sql.Connection;

public class UsersJoinService {

	public boolean joinUsers(UserBean users) {
		boolean joinSuccess= false;
		AdminUsersDAO adminUserDAO= AdminUsersDAO.getInstance();
		Connection con= getConnection();
		adminUserDAO.setConnection(con);
		int insertCount= adminUserDAO.insertAdminUsers(users);
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
