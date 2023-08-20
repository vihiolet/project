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
			userDAO.delUserContext(id);
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return deleteSuccess;
	}

	public boolean removeReview(int[] intCodeArr) {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		
		boolean isRemoveSuccess= false;
		int deleteCount = userDAO.deleteReview(intCodeArr);
		if(deleteCount > 0) {
			commit(con);
			isRemoveSuccess= true;
		}else {
			rollback(con);
		}
		
		close(con);				
		return isRemoveSuccess;
	}

}
