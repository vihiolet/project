package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FrontProDAO;
import vo.AdminProBean;

public class FrontProListService {

	public int selectList1Count() {
		int listCount= 0;
		Connection con = getConnection();
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		listCount= frontProDAO.selectList1Count();
		close(con);
		
		return listCount;
	}

	public ArrayList<AdminProBean> getArticleList(int page, int limit, int menu_code) {
		ArrayList<AdminProBean> articleList= null;
		Connection con= getConnection();
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		articleList= frontProDAO.selectArticleList(page, limit, menu_code);
		close(con);		
		
		return articleList;
	}

	public int selectList2Count() {
		int listCount= 0;
		Connection con = getConnection();
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		listCount= frontProDAO.selectList2Count();
		close(con);
		
		return listCount;
	}
	public int selectList3Count() {
		int listCount= 0;
		Connection con = getConnection();
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		listCount= frontProDAO.selectList3Count();
		close(con);
		
		return listCount;
	}
	public int selectList4Count() {
		int listCount= 0;
		Connection con = getConnection();
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		listCount= frontProDAO.selectList4Count();
		close(con);
		
		return listCount;
	}
	public int selectList5Count() {
		int listCount= 0;
		Connection con = getConnection();
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		listCount= frontProDAO.selectList5Count();
		close(con);
		
		return listCount;
	}

}
