package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.AdminEmpDAO;

public class AdminModiService {
	//입력한 비밀번호가 맞는지 확인
	public String isPasswd(String id) {
		
		AdminEmpDAO empDAO= AdminEmpDAO.getInstance();
		Connection con= getConnection();
		empDAO.setConnection(con);		
		
		String isPasswd= empDAO.getUserPasswd(id);
		close(con); 
		return isPasswd;
	}
}
