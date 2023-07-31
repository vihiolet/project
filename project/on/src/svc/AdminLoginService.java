package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.AdminEmpDAO;
import vo.AdminEmpBean;

public class AdminLoginService {

	public boolean login(AdminEmpBean emp) {
		AdminEmpDAO empDAO= AdminEmpDAO.getInstance();
		Connection con= getConnection();
		empDAO.setConnection(con);
		boolean loginResult= false;
		String loginId= empDAO.selectAdminLoginId(emp);
		if(loginId != null) {
			loginResult= true;
		}
		close(con);
		return loginResult;
	}

	public String LoginSetSalt(String id) {
		AdminEmpDAO empDAO= AdminEmpDAO.getInstance();
		Connection con= getConnection();
		empDAO.setConnection(con);
		String LoginSalt= empDAO.selectEmpSalt(id);
		close(con);
		return LoginSalt;
	}

}
