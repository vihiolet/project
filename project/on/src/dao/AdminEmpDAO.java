package dao;

import db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import javax.sql.DataSource;

import vo.AdminEmpBean;

public class AdminEmpDAO {
	
	DataSource ds;
	Connection con;
	
	private AdminEmpDAO() {}
	
	private static AdminEmpDAO adminEmpDAO;

	public static AdminEmpDAO getInstance() {
		if(adminEmpDAO == null) {
			adminEmpDAO= new AdminEmpDAO();
		}
		return adminEmpDAO;
	}
	
	public void setConnection(Connection con) {
		this.con= con;
	}

	//글 개수 구하기
	public int selectEmpListCount() {

		return 0;
	}
	//목록 보기
	public ArrayList<AdminEmpBean> selectEmpList(int page, int limit) {

		return null;
	}

}
