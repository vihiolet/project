package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.UsersDAO;
import vo.AdminEmpBean;

public class AdminInfoService {

	public AdminEmpBean getUserInfo(String id) {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		
		AdminEmpBean empInfo= new AdminEmpBean();
		empInfo= userDAO.selectUserInfo(id);
		
		close(con);		
		return empInfo;
	}

	public ArrayList<String> getEmp_idList() {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		
		ArrayList<String> empIdList= null;
		empIdList= userDAO.selectEmpId();
		
		close(con);		
		return empIdList;
	}

	public int getUserCount() {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		int userCount= userDAO.selectUserCount();
		close(con);
		return 0;
	}

}
