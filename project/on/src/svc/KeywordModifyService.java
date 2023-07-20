package svc;

import vo.KeywordBean;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminKeywordDAO;

public class KeywordModifyService {

	//권한 확인
	public boolean isKeyWriter(String id) {
		boolean isAdminUser= false;
		Connection con= getConnection();
		AdminKeywordDAO dao= AdminKeywordDAO.getInstance();
		dao.setConnection(con);
		isAdminUser= dao.isAdminUser(id);
		close(con);
		return isAdminUser;
	}
	//글 수정
	public boolean modifyKeyword(KeywordBean keyword) {
		boolean isModifySuccess= false;
		Connection con= getConnection();
		AdminKeywordDAO dao= AdminKeywordDAO.getInstance();
		dao.setConnection(con);
		int updateCount= dao.updateKetword(keyword);
		if(updateCount > 0) {
			commit(con);
			isModifySuccess= true;
		}else {
			rollback(con);
		}
		close(con);
		return isModifySuccess;
	}

}
