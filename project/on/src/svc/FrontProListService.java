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

	//검색점으로 검색한 제품 개수(메뉴코드 없음)
	public int selectMainSrchProCount(String srch_nm) {
		int listCount= 0;
		Connection con = getConnection();
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		listCount= frontProDAO.selectMainSrchProCount(srch_nm);
		close(con);
		
		return listCount;
	}

	//제품명으로 검색한 제품 개수(메뉴코드 없음)
	public int selectMainNameProCount(String pro_nm) {
		int listCount= 0;
		Connection con = getConnection();
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		listCount= frontProDAO.selectMainNameProCount(pro_nm);
		close(con);
		
		return listCount;
	}
	
	//검색점으로 검색한 제품 개수(메뉴코드 없음)
		public int selectSrchProCount(String srch_nm, int menu_code) {
			int listCount= 0;
			Connection con = getConnection();
			FrontProDAO frontProDAO= FrontProDAO.getInstance();
			frontProDAO.setConnection(con);
			listCount= frontProDAO.selectSrchProCount(srch_nm, menu_code);
			close(con);
			
			return listCount;
		}

		//제품명으로 검색한 제품 개수(메뉴코드 없음)
		public int selectNameProCount(String pro_nm, int menu_code) {
			int listCount= 0;
			Connection con = getConnection();
			FrontProDAO frontProDAO= FrontProDAO.getInstance();
			frontProDAO.setConnection(con);
			listCount= frontProDAO.selectNameProCount(pro_nm, menu_code);
			close(con);
			
			return listCount;
		}

	public ArrayList<AdminProBean> getSrchProList(int page, int limit, int menu_code, String srch_nm) {
		ArrayList<AdminProBean> articleList= null;
		Connection con= getConnection();
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		articleList= frontProDAO.selectSrchProList(page, limit, menu_code, srch_nm);
		close(con);		
		
		return articleList;
	}

	public ArrayList<AdminProBean> getMainSrchProList(int page, int limit, String srch_nm) {
		ArrayList<AdminProBean> articleList= null;
		Connection con= getConnection();
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		articleList= frontProDAO.selectMainSrchProList(page, limit, srch_nm);
		close(con);		
		
		return articleList;
	}

	public ArrayList<AdminProBean> getNameProList(int page, int limit, int menu_code, String pro_nm) {
		ArrayList<AdminProBean> articleList= null;
		Connection con= getConnection();
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		articleList= frontProDAO.selectNameProList(page, limit, menu_code, pro_nm);
		close(con);		
		
		return articleList;
	}

	public ArrayList<AdminProBean> getMainNameProList(int page, int limit, String pro_nm) {
		ArrayList<AdminProBean> articleList= null;
		Connection con= getConnection();
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		articleList= frontProDAO.selectMainNameProList(page, limit, pro_nm);
		close(con);		
		
		return articleList;
	}

}
