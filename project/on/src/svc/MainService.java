package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FrontProDAO;
import vo.AdminProBean;

public class MainService {

	public AdminProBean getPro1(int menu_code1) {
		Connection con= getConnection();
		AdminProBean proBean1= null;
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		proBean1= frontProDAO.selectPro1(menu_code1);
		close(con);	
		return proBean1;
	}
	public AdminProBean getPro3(int menu_code3) {
		Connection con= getConnection();
		AdminProBean proBean3= null;
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		proBean3= frontProDAO.selectPro3(menu_code3);
		close(con);	
		return proBean3;
	}
	public AdminProBean getPro4(int menu_code4) {
		Connection con= getConnection();
		AdminProBean proBean4= null;
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		proBean4= frontProDAO.selectPro4(menu_code4);
		close(con);	
		return proBean4;
	}
	public AdminProBean getPro5(int menu_code5) {
		Connection con= getConnection();
		AdminProBean proBean5= null;
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		proBean5= frontProDAO.selectPro5(menu_code5);
		close(con);	
		return proBean5;
	}
	
	//조건 검색점 
	public AdminProBean getProSrch1(int srch_code) {
		Connection con= getConnection();
		AdminProBean proBean= null;
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		proBean= frontProDAO.selectProSrch1(srch_code);
		close(con);	
		return proBean;
	}
	public AdminProBean getProSrch2(int srch_code) {
		Connection con= getConnection();
		AdminProBean proBean= null;
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		proBean= frontProDAO.selectProSrch2(srch_code);
		close(con);	
		return proBean;
	}
	public AdminProBean getProSrch3(int srch_code) {
		Connection con= getConnection();
		AdminProBean proBean= null;
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		proBean= frontProDAO.selectProSrch3(srch_code);
		close(con);	
		return proBean;
	}	
	public AdminProBean getProSrch4(int srch_code) {
		Connection con= getConnection();
		AdminProBean proBean= null;
		FrontProDAO frontProDAO= FrontProDAO.getInstance();
		frontProDAO.setConnection(con);
		proBean= frontProDAO.selectProSrch4(srch_code);
		close(con);	
		return proBean;
	}

}
