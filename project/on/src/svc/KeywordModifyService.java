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
	//검색점명 수정
	public boolean modifyKeyword(KeywordBean keyword) {
		boolean isModifySuccess= false;
		Connection con= getConnection();
		AdminKeywordDAO dao= AdminKeywordDAO.getInstance();
		dao.setConnection(con);
		int updateCount= dao.updateKekword(keyword);
		if(updateCount > 0) {
			commit(con);
			isModifySuccess= true;
		}else {
			rollback(con);
		}
		close(con);
		return isModifySuccess;
	}
	//검색점명 또는 비고 수정
	public boolean modifyAllKeyword(KeywordBean keyword) {
		boolean isModifySuccess= false;
		Connection con= getConnection();
		AdminKeywordDAO dao= AdminKeywordDAO.getInstance();
		dao.setConnection(con);
		int updateCount= dao.updateAllKeyword(keyword);
		if(updateCount > 0) {
			commit(con);
			isModifySuccess= true;
		}else {
			rollback(con);
		}
		close(con);
		return isModifySuccess;
	}
	//키워드 수정 form에 값 가져오기
	public KeywordBean getKeyword(int srch_code) {
		Connection con= getConnection();
		AdminKeywordDAO dao= AdminKeywordDAO.getInstance();
		dao.setConnection(con);
		KeywordBean keyword= dao.getkeyword(srch_code);
		close(con);
		return keyword;
	}

}
