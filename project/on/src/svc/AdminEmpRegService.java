package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminEmpDAO;
import vo.AdminEmpBean;

public class AdminEmpRegService {

	public boolean regEmp(AdminEmpBean emp) {
		boolean empRegSuccess= false;
		AdminEmpDAO empDAO= AdminEmpDAO.getInstance();
		Connection con= getConnection();
		empDAO.setConnection(con);
		int insertCount= empDAO.insertAdminEmp(emp);
		if(insertCount > 0) {
			empRegSuccess= true;
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return empRegSuccess;
	}

}
