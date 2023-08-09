package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminEmpDAO;

public class AdminDelService {

	public boolean deleteAdmin(String id, String passwdSalt) {
		Connection con= getConnection();
		AdminEmpDAO empDAO= AdminEmpDAO.getInstance();
		empDAO.setConnection(con);
		
		boolean deleteSuccess= false;	
		int delecteCount= empDAO.delecteUsers(id, passwdSalt);
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
