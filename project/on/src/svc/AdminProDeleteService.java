package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.AdminProDAO;

public class AdminProDeleteService {

	public boolean removePro(int[] intProCodeArr) {
		boolean isRemoveSuccess= false;
		Connection con= getConnection();
		AdminProDAO adminProDAO = AdminProDAO.getInstance();
		adminProDAO.setConnection(con);
		int deleteCount = adminProDAO.deleteProduct(intProCodeArr);
		
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
