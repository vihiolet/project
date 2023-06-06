package svc;

import java.sql.Connection;

import dao.AdminProDAO;
import vo.KeywordBean;
import static db.JdbcUtil.*;

public class KeywordRegService {

	public boolean registKeyword(KeywordBean keywordBean) {
		
		boolean isSuccess= false;
		Connection con= getConnection();
		AdminProDAO dao= AdminProDAO.getInstance();
		dao.setConnection(con);
		int insertCount= dao.insertKey(keywordBean);
		
		if(insertCount > 0) {
			commit(con);
			isSuccess= true;
		}else {
			rollback(con);
		}
		close(con);
		
		return isSuccess;
	}

}
