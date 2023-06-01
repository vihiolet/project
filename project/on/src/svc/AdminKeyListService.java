package svc;

import static db.JdbcUtil.*;

import vo.KeywordBean;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminProDAO;
import vo.KeywordBean;

public class AdminKeyListService {

	public int getListCount() throws Exception{
		
		int listCount= 0;
		Connection con = getConnection();
		AdminProDAO adminProDAO= AdminProDAO.getInstance();
		adminProDAO.setConnection(con);
		listCount= adminProDAO.selectKeywordListCount();
		close(con);
		
		return listCount;
	}

	public ArrayList<KeywordBean> getKeywordList(int page, int limit) {
		ArrayList<KeywordBean> KeywordLsit= null;
		Connection con= getConnection();
		AdminProDAO adminProDAO= AdminProDAO.getInstance();
		adminProDAO.setConnection(con);
		KeywordLsit= adminProDAO.selectKeywordList(page, limit);
		close(con);
		
		return KeywordLsit;
	} 

}
