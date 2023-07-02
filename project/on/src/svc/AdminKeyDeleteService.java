package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.AdminKeywordDAO;

public class AdminKeyDeleteService {

	public boolean removeKeyword(int[] intCodeArr) {
		
		boolean isRemoveSuccess= false;
		Connection con= getConnection();
		AdminKeywordDAO adminKeywordDAO = AdminKeywordDAO.getInstance();
		adminKeywordDAO.setConnection(con);
		int deleteCount = adminKeywordDAO.deleteKeyword(intCodeArr);
		
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
