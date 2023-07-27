package src;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UsersDAO;

public class UserModiService {

	//입력한 비밀번호가 맞는지 확인
	public String isPasswd(String id) {
		
		UsersDAO userDAO= UsersDAO.getInstance();
		Connection con= getConnection();
		userDAO.setConnection(con);		
		
		String isPasswd= userDAO.getUserPasswd(id);
		
		return isPasswd;
	}

}
