package dao;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import vo.UserBean;

public class AdminUsersDAO {
	
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static AdminUsersDAO adminUsersDAO;
	private AdminUsersDAO(){ }

	public static AdminUsersDAO getInstance() {
		if(adminUsersDAO == null) {
			adminUsersDAO= new AdminUsersDAO();
		}
		return adminUsersDAO;
	}

	public void setConnection(Connection con) {
		this.con= con;
		
	}

	public int insertAdminUsers(UserBean users) {
		String sql= "insert into admin_users values(?, ?, ?, ?)";
		int insertCount= 0;
		
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, users.getId());
			pstmt.setString(2, users.getPass());
			pstmt.setString(3, users.getSalt());
			pstmt.setString(4, users.getName());
			insertCount= pstmt.executeUpdate();			
		}catch(Exception e) {
			System.out.println("회원가입 에러 " + e);
		}finally {
			close(pstmt);
		}
		return insertCount;
	}

}
