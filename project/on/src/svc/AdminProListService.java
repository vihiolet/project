package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminProDAO;
import vo.AdminProBean;

public class AdminProListService {

	public int getListCount() throws Exception {
		
		int listCount= 0;
		Connection con= getConnection();
		AdminProDAO adminProDAO= AdminProDAO.getInstance();
		adminProDAO.setConnection(con);
		listCount= adminProDAO.selectListCount();
		close(con);
		
		return listCount;
	}
	
	//글 목록 보기
	public ArrayList<AdminProBean> getArticleList(int page, int limit) throws Exception {
		ArrayList<AdminProBean> articleList= null;
		Connection con= getConnection();
		AdminProDAO adminProDAO= AdminProDAO.getInstance();
		adminProDAO.setConnection(con);
		articleList= adminProDAO.selectArticleList(page, limit);
		close(con);
		
		return articleList;
	}

}
