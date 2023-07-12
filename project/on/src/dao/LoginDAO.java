package dao;
import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.UserBean;

public class LoginDAO {
	private static LoginDAO loginDAO;
	private Connection con;

	public static LoginDAO getInstance() {
		if(loginDAO == null) {
			loginDAO= new LoginDAO();
		}
		return loginDAO;
	}

	public void setConnection(Connection con) {
		this.con= con;
		
	}

	public UserBean selectLoginMember(String id, String pass) {
		UserBean loginMember= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try {
			pstmt= con.prepareStatement("select * from users where id= ? and pass= ?");
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				loginMember= new UserBean();
				loginMember.setId(rs.getString("id"));
				loginMember.setPass(rs.getString("pass"));
			}
		}catch(Exception e) {
				e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return loginMember;
	}
	
	//id에 맞는 Salt값 가져오기
	public String selecSaltById(String id) {
		UserBean loginMember= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String result= "";
		try {
			pstmt= con.prepareStatement("select salt from users where id= ?");
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				result= rs.getString("id");
			}
		}catch(Exception e) {
				e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
