package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.UsersDAO;
import vo.UserBean;

public class UserListService {

	public ArrayList<UserBean> getUserInfo(String id) {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		
		ArrayList<UserBean> userInfo= userDAO.selectUserInfo(id);
		if(userInfo.isEmpty()) {
			userInfo= null;
		}
		close(con);		
		return userInfo;
	}

	public UserBean getUserInfo2(String id) {
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);
		
		UserBean userInfo= userDAO.selectUserInfo2(id);
		if(userInfo.isEmpty()) {
			userInfo= null;
		}
		close(con);		
		return userInfo;
	}

}
