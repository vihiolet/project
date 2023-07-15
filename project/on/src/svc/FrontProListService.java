package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FrontProDAO;
import vo.AdminProBean;

public class FrontProListService {

	public int getListCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<AdminProBean> getArticleList(int page, int limit) {
		ArrayList<AdminProBean> articleList= null;
		Connection con= getConnection();
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		articleList= frontProDAO.selectArticleList(page, limit);
		close(con);		
		
		return articleList;
	}

}
