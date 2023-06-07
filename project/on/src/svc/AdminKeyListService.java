package svc;

import static db.JdbcUtil.*;

import vo.KeywordBean;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminKeywordDAO;
import vo.KeywordBean;

public class AdminKeyListService {

	public int getListCount() throws Exception{
		
		int listCount= 0;
		Connection con = getConnection();
		AdminKeywordDAO adminKeywordDAO= AdminKeywordDAO.getInstance();
		adminKeywordDAO.setConnection(con);
		listCount= adminKeywordDAO.selectKeywordListCount();
		close(con);
		
		return listCount;
	}

	public ArrayList<KeywordBean> getKeywordList(int page, int limit) {
		ArrayList<KeywordBean> KeywordLsit= null;
		Connection con= getConnection();
		AdminKeywordDAO adminKeywordDAO= AdminKeywordDAO.getInstance();
		adminKeywordDAO.setConnection(con);
		KeywordLsit= adminKeywordDAO.selectKeywordList(page, limit);
		close(con);
		
		return KeywordLsit;
	} 

}
